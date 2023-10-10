package com.einvoicing.applicaiton.data;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class InvoiceLine extends AbstractEntity {

    private String runNumber;
    private String idType;
    private String idNumber;
    private String customerName;
    private String billNumber;
    private Integer lineNo;
    private LocalDate supplyDate;
    private LocalDate supplyEndDate;
    private String itemName;
    private String quantity;
    private String unitPrice;
    private String totalPrice;
    private String discount;
    private String taxableAmount;
    private String taxExemptionReason;
    private String taxCategory;
    private Integer taxRate;
    private String taxAmount;
    private String taxInclusiveAmount;
    private String invoiceNote;
    private String invoiceType;
    private String currency;
    private String fxRate;
    private String invoiceNoteReason;
    private String seller;
    private String paymentDetails;
    private String customerAccount;
    private String previousInvoice;
    private String previousIssueDate;

    public String getRunNumber() {
        return runNumber;
    }
    public void setRunNumber(String runNumber) {
        this.runNumber = runNumber;
    }
    public String getIdType() {
        return idType;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }
    public String getIdNumber() {
        return idNumber;
    }
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getBillNumber() {
        return billNumber;
    }
    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }
    public Integer getLineNo() {
        return lineNo;
    }
    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }
    public LocalDate getSupplyDate() {
        return supplyDate;
    }
    public void setSupplyDate(LocalDate supplyDate) {
        this.supplyDate = supplyDate;
    }
    public LocalDate getSupplyEndDate() {
        return supplyEndDate;
    }
    public void setSupplyEndDate(LocalDate supplyEndDate) {
        this.supplyEndDate = supplyEndDate;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
    public String getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getDiscount() {
        return discount;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }
    public String getTaxableAmount() {
        return taxableAmount;
    }
    public void setTaxableAmount(String taxableAmount) {
        this.taxableAmount = taxableAmount;
    }
    public String getTaxExemptionReason() {
        return taxExemptionReason;
    }
    public void setTaxExemptionReason(String taxExemptionReason) {
        this.taxExemptionReason = taxExemptionReason;
    }
    public String getTaxCategory() {
        return taxCategory;
    }
    public void setTaxCategory(String taxCategory) {
        this.taxCategory = taxCategory;
    }
    public Integer getTaxRate() {
        return taxRate;
    }
    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }
    public String getTaxAmount() {
        return taxAmount;
    }
    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }
    public String getTaxInclusiveAmount() {
        return taxInclusiveAmount;
    }
    public void setTaxInclusiveAmount(String taxInclusiveAmount) {
        this.taxInclusiveAmount = taxInclusiveAmount;
    }
    public String getInvoiceNote() {
        return invoiceNote;
    }
    public void setInvoiceNote(String invoiceNote) {
        this.invoiceNote = invoiceNote;
    }
    public String getInvoiceType() {
        return invoiceType;
    }
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getFxRate() {
        return fxRate;
    }
    public void setFxRate(String fxRate) {
        this.fxRate = fxRate;
    }
    public String getInvoiceNoteReason() {
        return invoiceNoteReason;
    }
    public void setInvoiceNoteReason(String invoiceNoteReason) {
        this.invoiceNoteReason = invoiceNoteReason;
    }
    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }
    public String getPaymentDetails() {
        return paymentDetails;
    }
    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
    public String getCustomerAccount() {
        return customerAccount;
    }
    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }
    public String getPreviousInvoice() {
        return previousInvoice;
    }
    public void setPreviousInvoice(String previousInvoice) {
        this.previousInvoice = previousInvoice;
    }
    public String getPreviousIssueDate() {
        return previousIssueDate;
    }
    public void setPreviousIssueDate(String previousIssueDate) {
        this.previousIssueDate = previousIssueDate;
    }

}
