package basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Clases.Logro;
import basededatos.BaseDeDatos;

import static basedatos.BDOpenHelper.DATO_COMPLETADO;
import static basedatos.BDOpenHelper.DATO_NOMBRE;
import static basedatos.BDOpenHelper.NOMBRE_BBDD;

/**
 * La clase que administra la bbdd en el juego
 */
public class BaseDeDatosAndroid implements BaseDeDatos {
    /**
     * El openHElper
     */
    private BDOpenHelper openHelper;//EL Open Helper
    /**
     * El contexto
     */
    private Context context;//El contexto

    /**
     * El constructor de la clase
     * @param c El contexto
     */
    public BaseDeDatosAndroid(Context c){
        this.context = c;
        openHelper=new BDOpenHelper(c,1);
        cargarLogros();
    }

    /**
     * Funcion que carga todos los logros de la bbdd
     * @return Devuelve un arrayList de Logro con todos los logros de la bbdd
     */
    public ArrayList<Logro> cargarLogros(){
        SQLiteDatabase db=openHelper.getWritableDatabase();
        ArrayList<Logro> logros = new ArrayList<Logro>();
        Cursor c = db.rawQuery("SELECT * FROM "+NOMBRE_BBDD,null);
        c.moveToFirst();
        String nombre = c.getString(0);
        int completado = c.getInt(1);
        if(c.getInt(1) == 0 ){ logros.add(new Logro(nombre,true)); }else{ logros.add(new Logro(nombre,false)); };
        while (c.moveToNext()){
            nombre = c.getString(0);
            completado = c.getInt(1);
            if(c.getInt(1) == 0 ){ logros.add(new Logro(nombre,true)); }else{ logros.add(new Logro(nombre,false)); };
        }
        return logros;
    }

    /**
     * Modifica la fila que tiene el nombre que le pasamos, Columna completado a true
     * @param nombre El nombre de la fila que vamos a modificar
     */
    @Override
    public void guardar(String nombre) {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT "+DATO_COMPLETADO+" FROM "+NOMBRE_BBDD+" WHERE "+DATO_NOMBRE+"='"+nombre+"'",null);
        c.moveToFirst();
        if(c.getInt(0) == 0){
            ContentValues cv = new ContentValues();
            cv.put(DATO_COMPLETADO,1);
            db.update(NOMBRE_BBDD,cv,DATO_NOMBRE+"=?",new String[]{nombre});
        }

        c.close();
        db.close();
    }

    /**
     * Funcion sin uso
     * Funcion que te devuelve el porcentaje de cuantos logros hay completados
     * @param completar No se usa
     */
    @Override
    public void completar(String completar) {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor c = db.query(NOMBRE_BBDD,null,null,null,null,null,null);
        int completado = c.getColumnCount();
        c = db.rawQuery("SELECT "+DATO_COMPLETADO+" FROM "+NOMBRE_BBDD+" WHERE "+DATO_COMPLETADO+"='"+1+"'",null);
        float totalCompletado = (completado/c.getColumnCount())*100;
    }

}
