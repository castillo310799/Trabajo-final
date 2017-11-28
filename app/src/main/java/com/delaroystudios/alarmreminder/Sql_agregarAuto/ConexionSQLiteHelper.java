package com.delaroystudios.alarmreminder.Sql_agregarAuto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.delaroystudios.alarmreminder.Utilidades.utilidades;


/**
 * Created by Bayardo Moraga on 22/11/2017.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    //genera las tablas
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(utilidades.CREAR_TABLA_VEHICULO);//crear la tabla vehiculos
    }

    @Override
    //Verifica si hay una base de datos existente
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        //si se encuentra una version antigua la elimine y la vuelva a crear
        db.execSQL("DROP TABLE IF EXISTS vehiculos");
        onCreate(db);
    }
}
