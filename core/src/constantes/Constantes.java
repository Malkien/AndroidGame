package constantes;

public class Constantes {
    public static int fuerzaLanzamientoX;
    public static int fuerzaLanzamientoY;
    public static int fuerzaSalto;

    public static void init(){

        fuerzaLanzamientoX=10;
        fuerzaLanzamientoY=0;
        fuerzaSalto=500;

    }
    public static void cambiarVelocidad(int v){fuerzaLanzamientoX = v;}
}
