package basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Clase de la bbdd SQLite
 */
public class BDOpenHelper extends SQLiteOpenHelper {
    /**
     * Nombre de la bbdd
     */
    public static final String NOMBRE_BBDD = "logros";//Nombre de la bbdd
    /**
     * Nombre de la columna 0
     */
    public static final String DATO_NOMBRE = "nombre";//Nombre de la columna 0
    /**
     * Nombre de la columna 1
     */
    public static final String DATO_COMPLETADO = "completado";//Nombre de la columna 1

    /**
     * Constructor de la clase
     * @param c EL contexto de donde viene
     * @param version La version de la bbdd
     */
    public BDOpenHelper(Context c, int version){
        super(c,"logros",null,version);
    }

    /**
     * FUncion onCreate de la bbdd
     * @param db La bbdd que creamos
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+NOMBRE_BBDD+"("+DATO_NOMBRE+" text not null primary key, "+DATO_COMPLETADO+" boolean default 0)");

        ArrayList<String> nombres = new ArrayList<String>(){{
            add("Envidia de Indiana Jones");
            add("Vago Verdadero");
            add("Ninja Cumpliendo su Cometido");
            add("Gran Dominador de Mal");
            add("Estaba viendo el lago y me entro sueño...");
        }};

        for(String nombre:nombres){
            ContentValues valores = new ContentValues();
            valores.put(DATO_NOMBRE,nombre);
            db.insert(NOMBRE_BBDD,null,valores);
        }
    }

    /**
     * Update de la bbdd
     * @param db La bbdd
     * @param oldVersion La ultima version que tenia la bbdd antes
     * @param newVersion La version que tiene actualmente
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
