package controlador;


public class Materia {

    private int id = -1;
    private String nombre;

    public Materia() {
        this.id = -1;
    }
 
    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return id + "" + nombre;
    }

}
