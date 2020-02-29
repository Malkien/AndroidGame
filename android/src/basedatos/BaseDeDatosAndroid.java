package basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.badlogic.gdx.math.Interpolation;

import java.util.ArrayList;

import basededatos.BaseDeDatos;

import static basedatos.BDOpenHelper.DATO_COMPLETADO;
import static basedatos.BDOpenHelper.DATO_NOMBRE;
import static basedatos.BDOpenHelper.NOMBRE_BBDD;

public class BaseDeDatosAndroid implements BaseDeDatos {
    private BDOpenHelper openHelper;

    public BaseDeDatosAndroid(Context c){
        openHelper=new BDOpenHelper(c,1);
        cargarLogros();
    }

    @Override
    public int cargar() {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor c=db.query("polloPuntos",
                null,null,null,
                null,null,null);
        if(c.moveToFirst()){//False si no hay ninguna fila, true si hay una
            //Caso en que ya haya una fila
            return c.getInt(c.getColumnIndex("puntos"));
        }else{
            //Si no hay puntuaciones guardadas, empiezo desde 0 puntos
            return 0;
        }
    }

    private void cargarLogros(){
        SQLiteDatabase db=openHelper.getWritableDatabase();
        ArrayList<String> nombres = new ArrayList<String>(){{
            add("Envidia de Indiana Jones");
            add("Vago Verdadero");
            add("Ninja Cumpliendo su Cometido");
            add("Gran Dominador de Mal");
        }};

        for(String nombre:nombres){
            ContentValues valores = new ContentValues();
            valores.put(DATO_NOMBRE,nombre);
            db.insert(NOMBRE_BBDD,null,valores);
        }
    }
    @Override
    public void guardar(String nombre) {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT "+DATO_COMPLETADO+" FROM "+NOMBRE_BBDD+" WHERE "+DATO_NOMBRE+"='"+nombre+"'",null);
        c.moveToFirst();
        if(c.getInt(1) == 0){
            ContentValues cv = new ContentValues();
            cv.put("",1);
            db.update(NOMBRE_BBDD,cv,DATO_NOMBRE+"="+nombre,null);
        }
        c.close();
        db.close();
    }

    @Override
    public void completar(String completar) {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor c = db.query(NOMBRE_BBDD,null,null,null,null,null,null);
        int completado = c.getColumnCount();
        c = db.rawQuery("SELECT "+DATO_COMPLETADO+" FROM "+NOMBRE_BBDD+" WHERE "+DATO_COMPLETADO+"='"+1+"'",null);
        float totalCompletado = (completado/c.getColumnCount())*100;
    }

}
