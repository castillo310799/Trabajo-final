package com.delaroystudios.alarmreminder.Sql_agregarAuto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.delaroystudios.alarmreminder.Entidades.Cars;
import com.delaroystudios.alarmreminder.R;
import com.delaroystudios.alarmreminder.Utilidades.utilidades;

import java.util.ArrayList;


/**
 * Created by Bayardo Moraga on 23/11/2017.
 */


public class ConsultarLista extends AppCompatActivity {

    ListView lv;
    Button regresar;
    ArrayList<Cars> listcar;
    ArrayList<String> listainformacion;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        regresar = (Button)findViewById(R.id.regresar_menu);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conf = new Intent(ConsultarLista.this, Main_Menu_Vehiculo.class);
                startActivity(conf);
            }
        });
        lv=(ListView)findViewById(R.id.lv);

        conn= new ConexionSQLiteHelper(getApplicationContext(),"vehiculos",null,1);

        ConsultarLista();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listainformacion);
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="id: "+listcar.get(pos).getId()+"\n";
                informacion+="Marca: "+listcar.get(pos).getMarca()+"\n";
                informacion+="Modelo: "+listcar.get(pos).getModelo()+"\n";
                informacion+="Chasis: "+listcar.get(pos).getChasis()+"\n";
                informacion+="Motor: "+listcar.get(pos).getMotor()+"\n";
                informacion+="Año: "+listcar.get(pos).getAnio()+"\n";
                informacion+="Tipo de Gasolina: "+listcar.get(pos).getCombustible()+"\n";

                Toast.makeText(getApplicationContext(),informacion, Toast.LENGTH_SHORT).show();

                Cars car=listcar.get(pos);

                Intent intent=new Intent(ConsultarLista.this,DetalleVehiculo.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("carro",car);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    private void ConsultarLista() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Cars car=null;
        listcar= new ArrayList<Cars>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ utilidades.TABLA_VEHICULO,null);

        while (cursor.moveToNext()){
            car=new Cars();
            car.setId(cursor.getString(0));
            car.setMarca(cursor.getString(1));
            car.setModelo(cursor.getString(2));
            car.setChasis(cursor.getString(3));
            car.setMotor(cursor.getString(4));
            car.setAnio(cursor.getString(5));
            car.setGasolina(cursor.getString(6));
            listcar.add(car);
        }
        obtenerlista();

    }

    private void obtenerlista(){
        listainformacion =new ArrayList<String>();
        for(int i=0;i<listcar.size();i++){
            listainformacion.add(listcar.get(i).getMarca()+" "+listcar.get(i).getModelo());
        }

    }

}

