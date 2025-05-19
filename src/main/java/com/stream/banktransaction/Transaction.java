package com.stream.banktransaction;

public class Transaction {

    private String transactionId;
    private String accountHolder;
    private double amount;
    private String type;
    private boolean isSuccessful;

    public Transaction(String transactionId, String accountHolder, double amount, String type, boolean isSuccessful) {
        this.transactionId = transactionId;
        this.accountHolder = accountHolder;
        this.amount = amount;
        this.type = type;
        this.isSuccessful = isSuccessful;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    @Override
    public String toString() {
        return "Transaction [transactionId=" + transactionId + ", accountHolder=" + accountHolder + ", amount=" + amount
                + ", type=" + type + ", isSuccessful=" + isSuccessful + "]";
    }
}
