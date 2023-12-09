package Controlador.Transacciones;

import Controlador.Cuenta;
import Controlador.Transaccion;

import java.util.Date;

public class TransaccionCuenta extends Transaccion {
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    public TransaccionCuenta(Long id, Date fecha, Double monto, Cuenta origen, Cuenta destino){
        super(id,fecha,monto);
        this.cuentaOrigen = origen;
        this.cuentaDestino = destino;
        super.tipoTransaccion = 1;
    }

    public TransaccionCuenta() {
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}
