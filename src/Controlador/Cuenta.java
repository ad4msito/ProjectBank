package Controlador;

import java.lang.String;

public class Cuenta {
    private String accountID;
    private String accountType;
    private float saldo;
    private long userID;
    //constructor:

    public Cuenta(String accountID, String accountType, float saldo, long userID) {
        this.accountID = accountID;
        this.accountType = accountType;
        this.saldo = saldo;
        this.userID = userID;
    }

    public Cuenta() {
    }
    //getters y setters:

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    @Override
    public java.lang.String toString() {
        return "Cuenta{" +
                "accountID=" + accountID +
                ", accountType=" + accountType +
                ", saldo=" + saldo +
                ", u=" + userID +
                '}';
    }
}
