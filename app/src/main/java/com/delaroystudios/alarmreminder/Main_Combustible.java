package com.delaroystudios.alarmreminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main_Combustible extends AppCompatActivity {
    EditText distancia;
    TextView acum;
    float acumulador = 0;
    int kilometros= 4;
    int litro= 1;
    Button conf;
    Button ace;
    TextView ag;
    float total_gas = 0;
    double acum_gas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__combustible);
        acum = (TextView) findViewById(R.id.acumuladorkm);
        distancia = (EditText) findViewById(R.id.EtxtKm);

        ace = (Button) findViewById(R.id.Aceptado);
        ag = (TextView) findViewById(R.id.Consum_gas);

        final Context context = this;
        SharedPreferences sp = getSharedPreferences("MiConsumo", context.MODE_PRIVATE);

        ace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                acum_gas = 0.25;

                Toast.makeText(getApplicationContext(), String.valueOf(acum_gas), Toast.LENGTH_LONG).show();

                    SharedPreferences sp = getPreferences(context.MODE_PRIVATE);


                int validar = distancia.getText().toString().length();

                if (validar!=0)
                {
                    float dist_rec = Float.parseFloat(distancia.getText().toString());
                    if (dist_rec >0) {


                        acumulador = sp.getFloat("MiConsumo", 0);
                        total_gas = sp.getFloat("ConsumoGas", 0);

                        total_gas += (acum_gas * dist_rec);


                        acumulador += dist_rec;

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putFloat("MiConsumo", acumulador);
                        editor.putFloat("ConsumoGas", total_gas);
                        editor.commit();

                        String mostrar_con_gas = Float.toString(total_gas);
                        ag.setText(mostrar_con_gas);


                        String mostrar = Float.toString(acumulador);
                        acum.setText(mostrar);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error, verifique los datos antes de guardar", Toast.LENGTH_LONG).show();


                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Error, Llene los datos antes de guardar", Toast.LENGTH_SHORT).show();
                }



            }
            // else
            //{
            ///Toast.makeText(getApplicationContext(), "Primero debe ir a configuracion para registrar Km x Lt", Toast.LENGTH_LONG).show();
            // }


            //}
        });

    }
}




