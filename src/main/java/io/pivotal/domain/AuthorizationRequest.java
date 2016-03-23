package io.pivotal.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthorizationRequest {
    private Long issuerId;
    private Long acquirerId;
    private Long merchantId;
    private Long cardNumber;
    private Double amount;
    private String name;
    private Date transactionDate;

    @JsonCreator
    public AuthorizationRequest() {
    }

    public AuthorizationRequest(Long issuerId, Long acquirerId, Long merchantId, Long cardNumber, Double amount, String name, Date transactionDate) {
        this.issuerId = issuerId;
        this.acquirerId = acquirerId;
        this.merchantId = merchantId;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.name = name;
        this.transactionDate = transactionDate;
    }

    public Long getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(Long issuerId) {
        this.issuerId = issuerId;
    }

    public Long getAcquirerId() {
        return acquirerId;
    }

    public void setAcquirerId(Long acquirerId) {
        this.acquirerId = acquirerId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy, hh:mm aaa");
        return "AuthorizationRequest{" +
                "issuerId=" + issuerId +
                ", acquirerId=" + acquirerId +
                ", merchantId=" + merchantId +
                ", cardNumber=" + cardNumber +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                ", transactionDate=" + sdf.format(transactionDate) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorizationRequest that = (AuthorizationRequest) o;

        if (issuerId != null ? !issuerId.equals(that.issuerId) : that.issuerId != null) return false;
        if (acquirerId != null ? !acquirerId.equals(that.acquirerId) : that.acquirerId != null) return false;
        if (merchantId != null ? !merchantId.equals(that.merchantId) : that.merchantId != null) return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        return transactionDate != null ? transactionDate.equals(that.transactionDate) : that.transactionDate == null;

    }

    @Override
    public int hashCode() {
        int result = issuerId != null ? issuerId.hashCode() : 0;
        result = 31 * result + (acquirerId != null ? acquirerId.hashCode() : 0);
        result = 31 * result + (merchantId != null ? merchantId.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (transactionDate != null ? transactionDate.hashCode() : 0);
        return result;
    }
}
