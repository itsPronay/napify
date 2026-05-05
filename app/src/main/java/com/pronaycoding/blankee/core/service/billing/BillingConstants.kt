package com.pronaycoding.blankee.core.service.billing

/**
 * In-app product ID for the Blankee Premium subscription/product.
 *
 * This managed product ID is used to identify the premium offering in Google Play Billing.
 * Premium unlocks features including:
 * - Custom sound uploads
 * - Additional preset slots
 * - Ad-free experience (if applicable)
 *
 * **Configuration**: Create a **managed product** (one-time purchase) in Play Console with this exact ID:
 * - Path: Monetize → In-app Products → Create Product
 * - Product ID: "blankee_premium"
 * - Product Type: Managed product (purchased once, owned forever)
 *
 * @see PlayBillingManager for billing implementation
 * @see HomeViewmodel for checking premium status
 * @see SettingsViewModel for premium purchase UI
 */
const val PREMIUM_INAPP_PRODUCT_ID: String = "blankee_premium"
