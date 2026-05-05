package com.pronaycoding.blankee.core.service.billing

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.PendingPurchasesParams
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.pronaycoding.blankee.R
import com.pronaycoding.blankee.core.datastore.PreferenceManagerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PlayBillingManager(
    private val appContext: Context,
    private val prefs: PreferenceManagerRepository,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var billingClient: BillingClient

    @Volatile
    private var productDetails: ProductDetails? = null

    private val purchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                purchases.forEach { handlePurchase(it) }
            } else if (billingResult.responseCode != BillingClient.BillingResponseCode.USER_CANCELED) {
                Log.w(TAG, "Purchase update: ${billingResult.debugMessage}")
            }
        }

    fun start() {
        billingClient =
            BillingClient
                .newBuilder(appContext)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases(
                    PendingPurchasesParams
                        .newBuilder()
                        .enableOneTimeProducts()
                        .build(),
                ).build()

        billingClient.startConnection(
            object : BillingClientStateListener {
                override fun onBillingSetupFinished(billingResult: BillingResult) {
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        scope.launch {
                            prefetchPremiumProduct()
                            syncPremiumFromPlay()
                        }
                    } else {
                        Log.w(TAG, "Billing setup failed: ${billingResult.debugMessage}")
                    }
                }

                override fun onBillingServiceDisconnected() {
                    Log.w(TAG, "Billing service disconnected")
                }
            },
        )
    }

    suspend fun syncPremiumFromPlay(): Boolean {
        if (!billingClient.isReady) return false
        val purchases = queryInAppPurchases()
        val hasPremium = purchases.any { isPremiumPurchase(it) }
        prefs.setPremiumUnlocked(hasPremium)
        purchases.filter { isPremiumPurchase(it) && !it.isAcknowledged }.forEach { acknowledge(it) }
        return hasPremium
    }

    fun launchPremiumPurchase(activity: Activity) {
        if (!billingClient.isReady) {
            Toast.makeText(activity, appContext.getString(R.string.billing_not_ready), Toast.LENGTH_SHORT).show()
            return
        }
        val details = productDetails
        if (details == null) {
            Toast.makeText(activity, appContext.getString(R.string.billing_product_not_loaded), Toast.LENGTH_SHORT).show()
            scope.launch { prefetchPremiumProduct() }
            return
        }
        val productDetailsParamsList =
            listOf(
                BillingFlowParams.ProductDetailsParams
                    .newBuilder()
                    .setProductDetails(details)
                    .build(),
            )
        val billingFlowParams =
            BillingFlowParams
                .newBuilder()
                .setProductDetailsParamsList(productDetailsParamsList)
                .build()
        val result = billingClient.launchBillingFlow(activity, billingFlowParams)
        if (result.responseCode != BillingClient.BillingResponseCode.OK) {
            Toast.makeText(activity, appContext.getString(R.string.billing_launch_failed), Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun prefetchPremiumProduct() {
        if (!billingClient.isReady) return
        val product =
            QueryProductDetailsParams.Product
                .newBuilder()
                .setProductId(PREMIUM_INAPP_PRODUCT_ID)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        val params =
            QueryProductDetailsParams
                .newBuilder()
                .setProductList(listOf(product))
                .build()
        val detailsList = queryProductDetails(params)
        productDetails = detailsList.firstOrNull()
    }

    private suspend fun queryProductDetails(params: QueryProductDetailsParams): List<ProductDetails> =
        suspendCancellableCoroutine { cont ->
            billingClient.queryProductDetailsAsync(params) { billingResult, detailsResult ->
                if (!cont.isActive) return@queryProductDetailsAsync
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    cont.resume(detailsResult.productDetailsList)
                } else {
                    Log.w(TAG, "queryProductDetails: ${billingResult.debugMessage}")
                    cont.resume(emptyList())
                }
            }
        }

    private suspend fun queryInAppPurchases(): List<Purchase> =
        suspendCancellableCoroutine { cont ->
            billingClient.queryPurchasesAsync(
                QueryPurchasesParams
                    .newBuilder()
                    .setProductType(BillingClient.ProductType.INAPP)
                    .build(),
            ) { billingResult, purchases ->
                if (!cont.isActive) return@queryPurchasesAsync
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    cont.resume(purchases ?: emptyList())
                } else {
                    cont.resume(emptyList())
                }
            }
        }

    private fun isPremiumPurchase(purchase: Purchase): Boolean =
        purchase.purchaseState == Purchase.PurchaseState.PURCHASED &&
            purchase.products.any { it == PREMIUM_INAPP_PRODUCT_ID }

    private fun handlePurchase(purchase: Purchase) {
        if (!isPremiumPurchase(purchase)) return
        if (purchase.isAcknowledged) {
            scope.launch { prefs.setPremiumUnlocked(true) }
        } else {
            acknowledge(purchase)
        }
    }

    private fun acknowledge(purchase: Purchase) {
        val params =
            AcknowledgePurchaseParams
                .newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()
        billingClient.acknowledgePurchase(params) { result ->
            if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                scope.launch { prefs.setPremiumUnlocked(true) }
            } else {
                Log.w(TAG, "Acknowledge failed: ${result.debugMessage}")
            }
        }
    }

    companion object {
        private const val TAG = "PlayBilling"
    }
}
