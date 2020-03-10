package constantes;

/**
 * Clase de Constantes
 */
public class Constantes {
    /**
     * La velocidad en x
     */
    public static int fuerzaLanzamientoX;
    /**
     * La velocidad en y
     */
    public static int fuerzaLanzamientoY;
    /**
     * La fuerza del salto
     */
    public static int fuerzaSalto;

    /**
     * Funcion Init
     */
    public static void init(){

        fuerzaLanzamientoX=10;
        fuerzaLanzamientoY=0;
        fuerzaSalto=500;

    }

    /**
     * No se usa
     * Funcion para cambiar la velocidad
     * @param v la velocidad
     */
    public static void cambiarVelocidad(int v){fuerzaLanzamientoX = v;}
}
