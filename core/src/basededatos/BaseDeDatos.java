package basededatos;

import java.util.ArrayList;

/**
 * Interfaz de la BaseDeDatos
 */
public interface BaseDeDatos {
    /**
     * Modifica la fila que tiene el nombre que le pasamos, Columna completado a true
     * @param nombre El nombre de la fila que vamos a modificar
     */
    public void guardar(String nombre);// funcion guardar

    /**
     * Funcion sin uso
     * Funcion que te devuelve el porcentaje de cuantos logros hay completados
     * @param completar No se usa
     */
    public void completar(String completar);//Funcion conpletar

    /**
     * Funcion que carga todos los logros de la bbdd
     * @return Devuelve un arrayList de Logro con todos los logros de la bbdd
     */
    public ArrayList cargarLogros();// Funcion Cargar

}
