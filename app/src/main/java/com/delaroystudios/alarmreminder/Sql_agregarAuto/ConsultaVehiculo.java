package com.delaroystudios.alarmreminder.Sql_agregarAuto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.delaroystudios.alarmreminder.R;
import com.delaroystudios.alarmreminder.Utilidades.utilidades;

/**
 * Created by Bayardo Moraga on 25/11/2017.
 */

public class ConsultaVehiculo extends AppCompatActivity implements View.OnClickListener {

    EditText etmarca,etmodelo,etchasis,etmotor,etanio,etcolor,etid;
    Button btndel,btnid,btnmod;
    Spinner sp;
    String valor;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_vehiculo);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"vehiculos",null,1);

        etid = (EditText) findViewById(R.id.etid);
        etmarca = (EditText) findViewById(R.id.etmarca);
        etmodelo = (EditText) findViewById(R.id.etmodelo);
        etchasis = (EditText) findViewById(R.id.etchasis);
        etmotor = (EditText) findViewById(R.id.etmotor);
        etanio = (EditText) findViewById(R.id.etanio);
        sp = (Spinner) findViewById(R.id.spinner);
        btndel = (Button) findViewById(R.id.btndelete);
        btnid = (Button) findViewById(R.id.btnid);
        btnmod = (Button) findViewById(R.id.btnmod);
        btnid.setOnClickListener(this);
        btndel.setOnClickListener(this);
        btnmod.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Tipo, android.R.layout.simple_spinner_item);
        sp.setAdapter(adapter);


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                valor = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnid:
                buscarval();
                break;
            case R.id.btnmod:
                validacion();
                break;
            case R.id.btndelete:
                eliminarvehiculo();
                break;
        }
    }

    private void eliminarvehiculo(){

        //Obtener Valores para validad los campos vacios
        int anio = etanio.getText().toString().length();
        int marca=etmarca.getText().toString().length();
        int modelo=etmodelo.getText().toString().length();
        int chasis=etchasis.getText().toString().length();
        int motor=etmotor.getText().toString().length();
        int id=etid.getText().toString().length();

        if(anio==0 || marca==0 || modelo==0 || chasis==0 || motor==0 || id==0) {
            Toast.makeText(this,"Primero debe Buscar un Vehiculo",Toast.LENGTH_LONG).show();
        }else{

            SQLiteDatabase db=conn.getWritableDatabase();
            String[] parametros={etid.getText().toString()};

            db.delete(utilidades.TABLA_VEHICULO,utilidades.CAMPO_ID+"=?",parametros);
            Toast.makeText(getApplicationContext(),"Auto Elimado",Toast.LENGTH_LONG).show();
            limpiar();
            db.close();
        }
    }
    private void validacion(){
        //Obtener Valores para validad los campos vacios
        int an = etanio.getText().toString().length();
        int marca=etmarca.getText().toString().length();
        int modelo=etmodelo.getText().toString().length();
        int chasis=etchasis.getText().toString().length();
        int motor=etmotor.getText().toString().length();
        int id=etid.getText().toString().length();

        if(an==0 || marca==0 || modelo==0 || chasis==0 || motor==0 || id==0) {
            Toast.makeText(this,"Primero debe Buscar un Vehiculo",Toast.LENGTH_LONG).show();
        }else{
            modificarvehiculo();
        }

    }
    private void buscarval(){
        int id=etid.getText().toString().length();

        if(id==0) {
            Toast.makeText(this,"Primero debe Buscar un Vehiculo",Toast.LENGTH_LONG).show();
        }else{
            consultarSql();
        }

    }

    private void modificarvehiculo() {
        String anio=etanio.getText().toString();
        int re;
        re = Integer.parseInt(anio);
        if(re>1950 && re<2017){

            SQLiteDatabase db=conn.getWritableDatabase();
            String[] parametros={etid.getText().toString()};
            ContentValues values=new ContentValues();
            values.put(utilidades.CAMPO_ID,etid.getText().toString());
            values.put(utilidades.CAMPO_MARCA,etmarca.getText().toString());
            values.put(utilidades.CAMPO_MODELO,etmodelo.getText().toString());
            values.put(utilidades.CAMPO_CHASIS,etchasis.getText().toString());
            values.put(utilidades.CAMPO_MOTOR,etmotor.getText().toString());
            values.put(utilidades.CAMPO_ANIO,etanio.getText().toString());
            values.put(utilidades.CAMPO_COMBUSTIBLE,valor);

            db.update(utilidades.TABLA_VEHICULO,values,utilidades.CAMPO_ID+"=?",parametros);
            Toast.makeText(getApplicationContext(),"Datos Vehiculo Actualizado",Toast.LENGTH_LONG).show();
            limpiar();
            db.close();
        }
        else{
            Toast.makeText(this,"Verifique el Año",Toast.LENGTH_LONG).show();
            etanio.setText("");
        }
    }


    private void consultarSql() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={etid.getText().toString()};

        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor=db.rawQuery("SELECT "+utilidades.CAMPO_ID+","+utilidades.CAMPO_MARCA+","+
                    utilidades.CAMPO_MODELO+","+utilidades.CAMPO_CHASIS+","+utilidades.CAMPO_MOTOR+
                    ","+utilidades.CAMPO_ANIO+
                    " FROM "+utilidades.TABLA_VEHICULO+" WHERE "+utilidades.CAMPO_ID+"=? ",parametros);

            cursor.moveToFirst();
            etid.setText(cursor.getString(0));
            etmarca.setText(cursor.getString(1));
            etmodelo.setText(cursor.getString(2));
            etchasis.setText(cursor.getString(3));
            etmotor.setText(cursor.getString(4));
            etanio.setText(cursor.getString(5));

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El ID es Incorrecto ",Toast.LENGTH_LONG).show();
            limpiar();
        }
    }


    private void limpiar(){
        etid.setText("");
        etmarca.setText("");
        etmodelo.setText("");
        etchasis.setText("");
        etmotor.setText("");
        etanio.setText("");
    }

}