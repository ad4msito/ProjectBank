package Controlador.Transacciones;

import Controlador.Cuenta;
import Controlador.Tarjeta;
import Controlador.Transaccion;

import java.sql.Date;

public class TransaccionTarjeta extends Transaccion {
    private Long tarjetaOrigen;
    private Long cuentaDestino;
    public TransaccionTarjeta( Date fecha, Double monto, Long origen, Long destino){
        super(fecha,monto);
        super.tipoTransaccion = 2;
        this.tarjetaOrigen = origen;
        this.cuentaDestino = destino;
    }

    public Long getTarjetaOrigen() {
        return tarjetaOrigen;
    }

    public void setTarjetaOrigen(Long tarjetaOrigen) {
        this.tarjetaOrigen = tarjetaOrigen;
    }

    public Long getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Long cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public String toString() {
        return "TransaccionTarjeta{" +
                "tarjetaOrigen=" + tarjetaOrigen +
                ", cuentaDestino=" + cuentaDestino +
                ", id=" + id +
                ", fecha=" + fecha +
                ", monto=" + monto +
                ", tipoTransaccion=" + tipoTransaccion +
                '}';
    }
}
