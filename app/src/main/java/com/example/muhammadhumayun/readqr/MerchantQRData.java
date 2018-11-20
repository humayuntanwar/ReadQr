package com.example.muhammadhumayun.readqr;

import android.support.annotation.IntRange;

import com.example.muhammadhumayun.readqr.Validation.EmvSpecificationAttribute;
import com.example.muhammadhumayun.readqr.Validation.Required;

public class MerchantQRData {
     /*
    var globalUniqueIdentifier = Guid.NewGuid().ToString().Replace("-", string.Empty);
var merchantPayload = MerchantPayload.CreateStatic(
    merchantGlobalUniqueIdentifier: globalUniqueIdentifier,
    merchantCategoryCode: 4111,
    transactionCurrencyNumericCode: Iso4217Currency.MexicoPeso.Value.NumericCode,
    countryCode: Iso3166Countries.Mexico,
    merchantName: "My Super Shop",
    merchantCity: "Mexico City");*/


    /// <summary>
    /// Defines the version of the QR Code template and hence the conventions on the identifiers, lengths, and values.
    /// </summary>
    /// <remarks>
    /// The Payload Format Indicator shall contain a value of "01". All other values are RFU.
    ///
    @EmvSpecificationAttribute(id=0,maxLength = 2,isParent = false)
    @Required
    public int payloadFormatIndicator;
    public int getPayloadFormatIndicator() {
        return payloadFormatIndicator; }
    public void setPayloadFormatIndicator(int payloadFormatIndicator) {
        this.payloadFormatIndicator = payloadFormatIndicator; }

    /// <summary>
    /// Identifies the communication technology (here QR Code) and whether the data is static or dynamic
    /// </summary>
    /// <remarks>
    /// The Point of Initiation Method has a value of "11" for static QR Codes anda value of "12" for dynamic QR Codes.
    /// <para>The value of "11" is used when the same QR Code is shown for more than one transaction.</para>
    /// <para>The value of "12" is used when a new QR Code is shown for each transaction</para>
    /// </remarks>
    @EmvSpecificationAttribute(id=1,maxLength = 2,isParent = false)
    @Required
    @IntRange(from = 11,to = 12)
    public int pointOfInitializationMethod;
    public int getPointOfInitializationMethod() {
        return pointOfInitializationMethod; }
    public void setPointOfInitializationMethod(int pointOfInitializationMethod) {
        this.pointOfInitializationMethod = pointOfInitializationMethod; }


    /// <summary>
    /// As defined by [ISO 18245] and assigned by the Acquirer
    /// </summary>
    @EmvSpecificationAttribute(id=52,maxLength = 4,isParent = false)
    @Required
    public int merchantCategoryCode;
    public int getMerchantCategoryCode() {
        return merchantCategoryCode; }
    public void setMerchantCategoryCode(int merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode; }

    /// <summary>
    /// Indicates the currency code of the transaction. <see cref="Iso4217Currency"/>
    /// </summary>
    /// <remarks>
    /// A 3-digit numeric value, as defined by [ISO 4217]. This value will be used by the mobile application to display a recognizable currency to the consumer whenever an amount is being displayed or whenever the consumer is prompted to enter an amount.
    /// </remarks>
    @EmvSpecificationAttribute(id=53,maxLength = 3,isParent = false)
    @Required
    public int transactionCurrency;
    public int getTransactionCurrency() {
        return transactionCurrency; }
    public void setTransactionCurrency(int transactionCurrency) {
        this.transactionCurrency = transactionCurrency; }


    /// <summary>
    /// Indicates the country of the merchant acceptance device. <see cref="Iso3166"/>
    /// </summary>
    /// <remarks>
    /// A 2-character alpha value, as defined by [ISO 3166-1 alpha 2] and assigned by the Acquirer.The country may be displayed to the consumer by the mobile application when processing the transaction
    /// </remarks>
    @EmvSpecificationAttribute(id=58,maxLength = 3,isParent = false)
    @Required
    public String countryCode;
    public String getCountryCode() {
        return countryCode; }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode; }

    /// <summary>
    /// The “doing business as” name for the merchant, recognizable to the consumer.This name may be displayed to the consumer by the mobile application when processing the transaction.
    /// </summary>
    @EmvSpecificationAttribute(id=59,maxLength = 25,isParent = false)
    @Required
    public String merchantName;
    public String getMerchantName() {
        return merchantName; }
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName; }




    /// <summary>
    /// Creates the static merchant-presented QR with mandatory fields.
    /// </summary>
    /// <param name="merchantCategoryCode">The merchant category code.</param>
    /// <param name="transactionCurrencyNumericCode">The transaction currency numeric code.</param>
    /// <param name="countryCode">The country code.</param>
    /// <param name="merchantName">Name of the merchant.</param>
    /// <param name="merchantCity">The merchant city.</param>
    /// <returns>
    /// A new instance of the <see cref="MerchantPayload"/> class.
    /// </returns>
    public MerchantQRData(int payloadFormatIndicator, int pointOfInitializationMethod, int merchantCategoryCode, int transactionCurrency, String countryCode, String merchantName) {
        this.payloadFormatIndicator = payloadFormatIndicator;
        this.pointOfInitializationMethod = pointOfInitializationMethod;
        this.merchantCategoryCode = merchantCategoryCode;
        this.transactionCurrency = transactionCurrency;
        this.countryCode = countryCode;
        this.merchantName = merchantName;
    }
}
