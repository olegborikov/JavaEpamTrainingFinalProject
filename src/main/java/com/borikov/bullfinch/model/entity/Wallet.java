package com.borikov.bullfinch.model.entity;

/**
 * The {@code Wallet} class represents wallet entity.
 *
 * @author Oleg Borikov
 * @version 1.0
 */
public class Wallet {
    /**
     * The value is used for wallet id storage.
     */
    private Long walletId;

    /**
     * The value is used for balance storage.
     */
    private double balance;

    /**
     * Instantiates a new Wallet.
     *
     * @param walletId the wallet id
     * @param balance  the balance
     */
    public Wallet(Long walletId, double balance) {
        this.walletId = walletId;
        this.balance = balance;
    }

    /**
     * Gets wallet id.
     *
     * @return the wallet id
     */
    public Long getWalletId() {
        return walletId;
    }

    /**
     * Sets wallet id.
     *
     * @param walletId the wallet id
     */
    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Wallet wallet = (Wallet) o;
        if (Double.compare(wallet.balance, balance) != 0) {
            return false;
        }
        return walletId != null ? walletId.equals(wallet.walletId) : wallet.walletId == null;
    }

    @Override
    public int hashCode() {
        int hashCode;
        long temp;
        hashCode = walletId != null ? walletId.hashCode() : 0;
        temp = Double.doubleToLongBits(balance);
        hashCode = 31 * hashCode + (int) (temp ^ (temp >>> 32));
        return hashCode;
    }

    @Override
    public String toString() {
        final StringBuilder stringRepresentation = new StringBuilder("Wallet{");
        stringRepresentation.append("walletId=").append(walletId);
        stringRepresentation.append(", balance=").append(balance);
        stringRepresentation.append('}');
        return stringRepresentation.toString();
    }
}
