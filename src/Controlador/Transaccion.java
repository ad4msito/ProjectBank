package Controlador;

import java.sql.Date;

public abstract class Transaccion {
    protected Long id;
    protected Date fecha;
    protected Double monto;
    protected int tipoTransaccion;

    public Transaccion() {
    }

    public Transaccion( Date fecha, Double monto) {

        this.fecha = fecha;
        this.monto = monto;
    }

    public Transaccion(Double monto) {
        this.monto = monto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
