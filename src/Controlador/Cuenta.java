package Controlador;

public class Cuenta {
    private Long id;
    private String alias;
    private Double saldo;
    private int tipoCuenta;
    private UsuarioCuenta usuarioCuenta;
    private Double tasaInteres;


    public Cuenta() {
    }

    public Cuenta(Long id, String alias, Double saldo, int tipoCuenta, UsuarioCuenta usuarioCuenta) {
        this.id = id;
        this.alias = alias;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.usuarioCuenta = usuarioCuenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public UsuarioCuenta getUsuarioCuenta() {
        return usuarioCuenta;
    }

    public void setUsuarioCuenta(UsuarioCuenta usuarioCuenta) {
        this.usuarioCuenta = usuarioCuenta;
    }

    public int getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(int tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(Double tasaInteres) {
            this.tasaInteres = tasaInteres;
    }
}
