package Controlador;

public class Cuenta {
    private Long id;
    private String alias;
    private Double saldo;
    private int tipoCuenta;
    private Long usuarioCuenta;
    private Double tasaInteres;


    public Cuenta() {
    }

    public Cuenta(String alias, Double saldo, int tipoCuenta, Long usuarioCuenta) {
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

    public Long getUsuarioCuenta() {
        return usuarioCuenta;
    }

    public void setUsuarioCuenta(Long usuarioCuenta) {
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

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", saldo=" + saldo +
                ", tipoCuenta=" + tipoCuenta +
                ", usuarioCuenta=" + usuarioCuenta +
                ", tasaInteres=" + tasaInteres +
                '}';
    }
}
