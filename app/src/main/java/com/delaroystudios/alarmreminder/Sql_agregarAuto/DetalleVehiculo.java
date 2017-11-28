package com.delaroystudios.alarmreminder.Sql_agregarAuto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.delaroystudios.alarmreminder.Entidades.Cars;
import com.delaroystudios.alarmreminder.R;


/**
 * Created by Bayardo Moraga on 25/11/2017.
 */

public class DetalleVehiculo extends AppCompatActivity {

    TextView id,marca,modelo,anio,chasis,combustible,motor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_vehiculo);

        id=(TextView)findViewById(R.id.campoId);
        marca=(TextView) findViewById(R.id.campomarca);
        modelo=(TextView) findViewById(R.id.campomodelo);
        anio=(TextView) findViewById(R.id.campoanio);
        chasis=(TextView) findViewById(R.id.campochasis);
        combustible=(TextView) findViewById(R.id.campocombustible);
        motor=(TextView) findViewById(R.id.campomotor);

        Bundle objetoEnviado=getIntent().getExtras();
        Cars car=null;

        if(objetoEnviado!=null){
            car= (Cars) objetoEnviado.getSerializable("carro");
            id.setText(car.getId().toString());
            marca.setText(car.getMarca().toString());
            modelo.setText(car.getModelo().toString());
            chasis.setText(car.getChasis().toString());
            motor.setText(car.getMotor().toString());
            anio.setText(car.getAnio().toString());
            combustible.setText(car.getCombustible().toString());
        }

    }

}


