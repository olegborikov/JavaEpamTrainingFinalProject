package com.borikov.bullfinch.entity;

/**
 * The {@code Wallet} class represents wallet entity
 *
 * @author Oleg Borikov
 * @version 1.0
 * @since 2020-10-16
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
        int result;
        long temp;
        result = walletId != null ? walletId.hashCode() : 0;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wallet{");
        sb.append("walletId=").append(walletId);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
