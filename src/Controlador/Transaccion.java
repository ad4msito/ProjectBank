package Controlador;

import java.sql.Date;

public abstract class Transaccion {
    protected Long id;
    protected Date fecha;
    protected Double monto;
    protected int tipoTransaccion;

    public Transaccion() {
    }

    public Transaccion(Long id, Date fecha, Double monto) {
        this.id = id;
        this.fecha = fecha;
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
