package controlador;

import com.sun.org.apache.xpath.internal.operations.String;

public class Cuenta {
    private String accountID;
    private String accountType;
    private float saldo;
    private Usuario u;
    //constructor:
    public Cuenta(String accountID, String accountType, float saldo, Usuario u) {
        this.accountID = accountID;
        this.accountType = accountType;
        this.saldo = saldo;
        this.u = u;
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
        return u;
    }
    public Long getUserID() {
        return u.getUserID();
    }
    public void setU(Usuario u) {
        this.u = u;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "saldo=" + saldo +
                ", u=" + u +
                '}';
    }
}
