package Clases;

public class Logro {
    private String nombre;
    private Boolean completado;

    public Logro(String nombre, Boolean completado){
        this.nombre = nombre;
        this.completado = completado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }
}
