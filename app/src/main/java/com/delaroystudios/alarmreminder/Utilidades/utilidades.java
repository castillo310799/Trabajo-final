package com.delaroystudios.alarmreminder.Utilidades;

/**
 * Created by Bayardo Moraga on 23/11/2017.
 */

public class utilidades {


    //Constantes campos tabla Usuario

    public static final String TABLA_VEHICULO="vehiculos";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_MARCA="marca";
    public static final String CAMPO_MODELO="modelo";
    public static final String CAMPO_CHASIS="chasis";
    public static final String CAMPO_MOTOR="motor";
    public static final String CAMPO_ANIO="anio";
    public static final String CAMPO_COMBUSTIBLE="combustible";



   public static final String CREAR_TABLA_VEHICULO="CREATE TABLE "+TABLA_VEHICULO+" ("+CAMPO_ID+" TEXT,"+CAMPO_MARCA+" TEXT,"+CAMPO_MODELO+" TEXT,"+CAMPO_CHASIS+" TEXT, "+CAMPO_MOTOR+" TEXT, "+CAMPO_ANIO+" TEXT,"+CAMPO_COMBUSTIBLE+" TEXT)";


}
