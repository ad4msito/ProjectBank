package controlador;

public class Movimiento {
    private long transaccionID;
    private float monto;
    private int fecha;
    private Cuenta origen;
    private Cuenta destino;

    //constructores:
    public Movimiento() {
    }

    public Movimiento(long transaccionID, float monto, int fecha, Cuenta origen, Cuenta destino) {
        this.transaccionID = transaccionID;
        this.monto = monto;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
    }
    //getters y setters
    public long getTransaccionID() {
        return transaccionID;
    }

    public void setTransaccionID(long transaccionID) {
        this.transaccionID = transaccionID;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public Cuenta getOrigen() {
        return origen;
    }

    public void setOrigen(Cuenta origen) {
        this.origen = origen;
    }

    public Cuenta getDestino() {
        return destino;
    }

    public void setDestino(Cuenta destino) {
        this.destino = destino;
    }
    public void setDebitar(float monto, Cuenta origen, Cuenta destino){
        float a = origen.getSaldo();
        float b = destino.getSaldo();
        origen.setSaldo(a-monto);
        destino.setSaldo(b+monto);
    }
}
