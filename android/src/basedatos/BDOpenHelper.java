package basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BDOpenHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_BBDD = "logros";
    public static final String DATO_NOMBRE = "nombre";
    public static final String DATO_COMPLETADO = "completado";

    public BDOpenHelper(Context c, int version){
        super(c,"logros",null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+NOMBRE_BBDD+"("+DATO_NOMBRE+" text not null primary key, "+DATO_COMPLETADO+" boolean default 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
