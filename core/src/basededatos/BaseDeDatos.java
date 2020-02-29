package basededatos;

public interface BaseDeDatos {
    public int cargar();
    public void guardar(String nombre);
    public void completar(String completar);

}
