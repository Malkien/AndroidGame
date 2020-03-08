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
public class BaseDeDatosAndroid implements BaseDeDatos {
    private BDOpenHelper openHelper;
    private Context context;

    public BaseDeDatosAndroid(Context c){
        this.context = c;
        openHelper=new BDOpenHelper(c,1);
        cargarLogros();
    }


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

    @Override
    public void completar(String completar) {
        SQLiteDatabase db=openHelper.getWritableDatabase();
        Cursor c = db.query(NOMBRE_BBDD,null,null,null,null,null,null);
        int completado = c.getColumnCount();
        c = db.rawQuery("SELECT "+DATO_COMPLETADO+" FROM "+NOMBRE_BBDD+" WHERE "+DATO_COMPLETADO+"='"+1+"'",null);
        float totalCompletado = (completado/c.getColumnCount())*100;
    }

}
