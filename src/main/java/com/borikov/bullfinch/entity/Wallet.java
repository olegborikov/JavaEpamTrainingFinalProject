package com.borikov.bullfinch.entity;

public class Wallet {
    private Long walletId;
    private double balance;

    public Wallet(Long walletId, double balance) {
        this.walletId = walletId;
        this.balance = balance;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public double getBalance() {
        return balance;
    }

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
        return walletId != null ? walletId.equals(wallet.walletId)
                : wallet.walletId == null;
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
