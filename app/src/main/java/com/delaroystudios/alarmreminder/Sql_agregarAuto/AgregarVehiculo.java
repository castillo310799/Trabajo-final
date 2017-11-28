package com.delaroystudios.alarmreminder.Sql_agregarAuto;

import android.content.ContentValues;
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
 * Created by Bayardo Moraga on 23/11/2017.
 */


public class AgregarVehiculo extends AppCompatActivity implements View.OnClickListener {


    EditText etmarca,etmodelo,etchasis,etmotor,etanio,etcolor,etid;
    Button btnagregar;
    Spinner sp;
    String valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_vehiculo);

        etid = (EditText) findViewById(R.id.etid);
        etmarca = (EditText) findViewById(R.id.etmarca);
        etmodelo = (EditText) findViewById(R.id.etmodelo);
        etchasis = (EditText) findViewById(R.id.etchasis);
        etmotor = (EditText) findViewById(R.id.etmotor);
        etanio = (EditText) findViewById(R.id.etanio);
        sp = (Spinner) findViewById(R.id.spinner);
        btnagregar = (Button) findViewById(R.id.btnagregar);

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
        registrarVehiculo();
    }

    private void registrarVehiculo() {

        //Obtener Valores para validad los campos vacios
        int anio = etanio.getText().toString().length();
        int marca=etmarca.getText().toString().length();
        int modelo=etmodelo.getText().toString().length();
        int chasis=etchasis.getText().toString().length();
        int motor=etmotor.getText().toString().length();
        int id=etid.getText().toString().length();

        if(anio==0 || marca==0 || modelo==0 || chasis==0 || motor==0 || id==0) {
            Toast.makeText(this,"No Deje los Campos Vacios",Toast.LENGTH_LONG).show();
        }
        else{
            agregar();
        }
    }

    private void agregar(){
        String anio = etanio.getText().toString();
        int re;
        re = Integer.parseInt(anio);

        if (re > 1950 && re < 2017) {
            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "vehiculos", null, 1);
            SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(utilidades.CAMPO_ID, etid.getText().toString());
            values.put(utilidades.CAMPO_MARCA, etmarca.getText().toString());
            values.put(utilidades.CAMPO_MODELO, etmodelo.getText().toString());
            values.put(utilidades.CAMPO_CHASIS, etchasis.getText().toString());
            values.put(utilidades.CAMPO_MOTOR, etmotor.getText().toString());
            values.put(utilidades.CAMPO_ANIO, etanio.getText().toString());
            values.put(utilidades.CAMPO_COMBUSTIBLE, valor);

            Long idResultante = db.insert(utilidades.TABLA_VEHICULO, utilidades.CAMPO_MARCA, values);
            Toast.makeText(getApplicationContext(), "El Auto " + etmarca.getText().toString() + " Se ha guardado Correctamente", Toast.LENGTH_SHORT).show();
            limpiar();
            db.close();
        }
        else{
            etanio.setText("");
            Toast.makeText(this,"Año Incorrecto",Toast.LENGTH_LONG).show();
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
