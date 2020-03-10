package Clases;

/**
 * Clase Logro
 */
public class Logro {
    /**
     * El nombre del logro
     */
    private String nombre;//El nombre del logro
    /**
     * Si esta completado el logro o no
     */
    private Boolean completado;//Si esta completado el logro o no

    /**
     * Constructor
     * @param nombre El nombre
     * @param completado Si esta completado
     */
    public Logro(String nombre, Boolean completado){
        this.nombre = nombre;
        this.completado = completado;
    }

    /**
     * Getter de nombre
     * @return El nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del nombre
     * @param nombre Nuevo valor del nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de completado
     * @return Si esta completado
     */
    public Boolean getCompletado() {
        return completado;
    }

    /**
     * Setter de completado
     * @param completado Cambia si esta completado
     */
    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }
}
