package Controlador;

public class Tarjeta {
    private long tarjetaID;
    private float limiteCredito;
    private float saldoDeudor;
    private Usuario u;
    //constructores:

    public Tarjeta() {
    }

    public Tarjeta(long tarjetaID, float limiteCredito, float saldoDeudor, Usuario u) {
        this.tarjetaID = tarjetaID;
        this.limiteCredito = limiteCredito;
        this.saldoDeudor = saldoDeudor;
        this.u = u;
    }
    //getters y setters:

    public long getTarjetaID() {
        return tarjetaID;
    }

    public void setTarjetaID(long tarjetaID) {
        this.tarjetaID = tarjetaID;
    }

    public float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public float getSaldoDeudor() {
        return saldoDeudor;
    }

    public void setSaldoDeudor(float saldoDeudor) {
        this.saldoDeudor = saldoDeudor;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }
}
