package constantes;

public class Constantes {
    public static int fuerzaLanzamientoX;
    public static int fuerzaLanzamientoY;

    public static void init(boolean esAndroid){
        if(esAndroid){
            fuerzaLanzamientoX=200;
            fuerzaLanzamientoY=0;
        }else{
            fuerzaLanzamientoX=200;
            fuerzaLanzamientoY=0;
        }
    }
}
