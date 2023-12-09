package Controlador;

import java.util.Date;

public class Tarjeta {
    private Long id;
    private String numero;
    private Date fechaExpiracion;
    private UsuarioCuenta usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public UsuarioCuenta getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioCuenta usuario) {
        this.usuario = usuario;
    }
}
