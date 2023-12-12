package Controlador;

public class Tarjeta {
    private Long id;
    private int numero;
    private Double limite;
    private Long usuarioID;

    public Tarjeta(int numero, Double limite, Long usuario) {

        this.numero = numero;
        this.limite = limite;
        this.usuarioID = usuario;
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

    @Override
    public String toString() {
        return "Tarjeta{" +
                "id=" + id +
                ", numero=" + numero +
                ", limite=" + limite +
                ", usuarioID=" + usuarioID +
                '}';
    }
}
