package controlador;

import java.lang.String;

public class Cuenta {
    private String accountID;
    private String accountType;
    private float saldo;
    private Usuario user;
    private long userID;
    //constructor:

    public Cuenta(String accountID, String accountType, float saldo, long userID) {
        this.accountID = accountID;
        this.accountType = accountType;
        this.saldo = saldo;
        this.userID = userID;
    }

    public Cuenta(String accountID, String accountType, float saldo, Usuario user) {
        this.accountID = accountID;
        this.accountType = accountType;
        this.saldo = saldo;
        this.user = user;
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

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public long getUserID() {
        return user.getUserID();
    }

    public void setUserID() {
        this.userID = user.getUserID();
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
