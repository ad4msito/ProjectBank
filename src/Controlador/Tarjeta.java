package Controlador;

public class Tarjeta {
    private Long id;
    private int numero;
    private Double limite;
    private Long usuarioID;
    private Double saldoAdeudado;
    public Tarjeta(int numero, Double limite, Long usuario) {

        this.numero = numero;
        this.limite = limite;
        this.usuarioID = usuario;
    }

    public Tarjeta(int numero, Double limite, Long usuarioID, Double saldoAdeudado) {
        this.numero = numero;
        this.limite = limite;
        this.usuarioID = usuarioID;
        this.saldoAdeudado = saldoAdeudado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Double getSaldoAdeudado() {
        return saldoAdeudado;
    }

    public void setSaldoAdeudado(Double saldoAdeudado) {
        this.saldoAdeudado = saldoAdeudado;
    }

    @Override
    public String toString() {
        return "n°=" + numero +
                ", limite=" + limite +
                ", deuda:" + saldoAdeudado;
    }
}
