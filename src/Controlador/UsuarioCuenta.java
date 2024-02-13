package Controlador;
public class UsuarioCuenta {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private Boolean esAdmin;


    public UsuarioCuenta() {
    }

    public UsuarioCuenta(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public UsuarioCuenta(String nombre, String email, String password, Boolean esAdmin) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.esAdmin = esAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    @Override
    public String toString() {
        return "UsuarioCuenta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
