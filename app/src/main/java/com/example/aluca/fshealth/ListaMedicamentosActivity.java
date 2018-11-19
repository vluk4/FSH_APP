package com.example.aluca.fshealth;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aluca.fshealth.DAO.RemedioDAO;
import com.example.aluca.fshealth.modelo.Remedio;

import java.util.List;

public class ListaMedicamentosActivity extends AppCompatActivity {

    private ListView listaMedicamentos;
    BluetoothAdapter mybluetooth;
    int Bluetoothstatus = 1;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sync_button:
                enableDisableBluetooth(mybluetooth);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicamentos);

        mybluetooth = BluetoothAdapter.getDefaultAdapter();

        listaMedicamentos = findViewById(R.id.lista_medicamentos);

        listaMedicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {

                Remedio remedio = (Remedio) listaMedicamentos.getItemAtPosition(position);
                Intent intentVaiProAlarme = new Intent(ListaMedicamentosActivity.this, AlarmeActivity.class);
                intentVaiProAlarme.putExtra("remedio", remedio);
                startActivity(intentVaiProAlarme);
            }
        });

        Button novoMedicamento = findViewById(R.id.novo_medicamento);
        novoMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProAlarme = new Intent(ListaMedicamentosActivity.this, AlarmeActivity.class);
                startActivity(intentVaiProAlarme);
            }
        });

        registerForContextMenu(listaMedicamentos);
    }

    public void enableDisableBluetooth(BluetoothAdapter mybluetooth){

        if(mybluetooth==null){
            Toast.makeText(getApplicationContext(),"O dispositivo não possui Bluetooth",Toast.LENGTH_LONG).show();
        }
        else if(!mybluetooth.isEnabled()){
            Intent enableBTintent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBTintent,Bluetoothstatus);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sync_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Remedio remedio = (Remedio) listaMedicamentos.getItemAtPosition(info.position);

                RemedioDAO dao = new RemedioDAO(ListaMedicamentosActivity.this);
                dao.deleta(remedio);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    private void carregaLista() {
        RemedioDAO dao = new RemedioDAO(this);
        List<Remedio> remedios = dao.buscaRemedios();
        dao.close();

        ArrayAdapter<Remedio> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, remedios);
        listaMedicamentos.setAdapter(adapter);
    }

}
