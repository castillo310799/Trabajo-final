package com.delaroystudios.alarmreminder.Sql_agregarAuto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.delaroystudios.alarmreminder.R;

public class Main_Menu_Vehiculo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu__vehiculo);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"vehiculos", null, 1);

    }
    public void onClick(View view) {
        Intent miIntent = null;

        switch (view.getId()) {
            case R.id.btncall:
                miIntent = new Intent(this,AgregarVehiculo.class);
                break;
            case R.id.btnmostrar:
                miIntent = new Intent(this,ConsultarLista.class);
                break;
            case R.id.btnconsulta:
                miIntent = new Intent(this,ConsultaVehiculo.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }
}
