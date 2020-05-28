package CONTROLADOR;

import java.time.LocalDate;

/**
 *
 * @author Sergio
 */
public class Alumno {

    int id = -1;
    private String nombre;
    private String apellido;
    private String mail;
    private LocalDate fecNac;
    private boolean cursando;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, String mail, LocalDate fecNac, boolean cursando) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.fecNac = fecNac;
        this.cursando = cursando;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getFecNac() {
        return fecNac;
    }

    public void setFecNac(LocalDate fecNac) {
        this.fecNac = fecNac;
    }

    public boolean getCursando() {
        return cursando;
    }

    public void setCursando(boolean cursando) {
        this.cursando = cursando;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cursando=" + cursando + '}';
    }

}
