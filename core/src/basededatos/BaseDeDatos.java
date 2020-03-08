package basededatos;

import java.util.ArrayList;

public interface BaseDeDatos {
    public void guardar(String nombre);
    public void completar(String completar);
    public ArrayList cargarLogros();

}
