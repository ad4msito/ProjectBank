package Controlador.Transacciones;

import Controlador.Cuenta;
import Controlador.Tarjeta;
import Controlador.Transaccion;

import java.util.Date;

public class TransaccionTarjeta extends Transaccion {
    private Tarjeta tarjetaOrigen;
    private Cuenta cuentaDestino;
    public TransaccionTarjeta(Long id, Date fecha, Double monto, Tarjeta origen, Cuenta destino){
        super(id,fecha,monto);
        super.tipoTransaccion = 2;
        this.tarjetaOrigen = origen;
        this.cuentaDestino = destino;
    }
}
