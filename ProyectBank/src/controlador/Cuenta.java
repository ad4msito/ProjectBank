package controlador;

public class Cuenta {
    private long accountID;
    private String accountType;
    private float saldo;
    private Usuario u;
    //constructor:
    public Cuenta(long accountID, String accountType, float saldo, Usuario u) {
        this.accountID = accountID;
        this.accountType = accountType;
        this.saldo = saldo;
        this.u = u;
    }

    public Cuenta(String accountType, float saldo, Usuario u) {
        this.accountType = accountType;
        this.saldo = saldo;
        this.u = u;
    }

    public Cuenta() {
    }
    //getters y setters:

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
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
