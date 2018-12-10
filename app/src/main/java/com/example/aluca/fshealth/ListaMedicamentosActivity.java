package com.example.aluca.fshealth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static java.lang.Thread.sleep;

public class ListaMedicamentosActivity extends AppCompatActivity {

    List<Remedio> remedios;
    private ListView listaMedicamentos;
    BluetoothAdapter mybluetooth = null;
    BluetoothDevice mydDevice=null;
    BluetoothSocket mySocket = null;
    private static final int Bluetoothstatus = 1; // 0 = desligado - >0 = ligado solicita_ativação
    private static final int newConection = 2; //solicita_Conexão
    boolean conection = false;
    boolean aux=false;
    long teste = 1000;

    ConnectedThread connectedThread;
    UUID MYUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.bluetooth_button:
                if(conection){
                    try{
                        mySocket.close();
                        conection=false;
                        Toast.makeText(getApplicationContext(),"Dispositivo desconectado",Toast.LENGTH_LONG).show();
                    }catch (IOException erro){
                        Toast.makeText(getApplicationContext(),"Erro: " + erro,Toast.LENGTH_LONG).show();

                    }
                }
                else if(!conection){
                    enableDisableBluetooth(mybluetooth);
                    if(BluetoothAdapter.STATE_CONNECTED >0){
                        lista_de_dispositivos();
                    }
                }
                break;
            case R.id.sync_button:
                if(conection){
                    String horario = "R1200";
                    String posicao ;
                    connectedThread.write(horario);
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(remedios.get(0).getIntervalo()!=null){
                        testaIntervalo(0);
                        posicao = "0" + remedios.get(0).getIntervalo() + "00";
                        connectedThread.write(posicao);
                        Toast.makeText(getApplicationContext(),"Dados enviados " + posicao ,Toast.LENGTH_SHORT).show();

                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(remedios.get(1).getIntervalo()!=null){
                        testaIntervalo(1);
                        posicao = "1" + remedios.get(1).getIntervalo() + "00";
                        connectedThread.write(posicao);
                        Toast.makeText(getApplicationContext(),"Dados enviados " + posicao ,Toast.LENGTH_SHORT).show();



                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }if(remedios.get(2).getIntervalo()!=null){
                        testaIntervalo(2);
                        posicao = "2" + remedios.get(2).getIntervalo() + "00";
                        connectedThread.write(posicao);
                        Toast.makeText(getApplicationContext(),"Dados enviados " + posicao ,Toast.LENGTH_SHORT).show();


                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }if(remedios.get(3).getIntervalo()!=null){
                        testaIntervalo(3);
                        posicao = "3" + remedios.get(3).getIntervalo() + "00";
                        connectedThread.write(posicao);
                        Toast.makeText(getApplicationContext(),"Dados enviados " + posicao ,Toast.LENGTH_SHORT).show();


                    }

                    Toast.makeText(getApplicationContext(),"Dados enviados " ,Toast.LENGTH_LONG).show();

                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    public void testaIntervalo( int i){
        if(Integer.parseInt(remedios.get(i).getIntervalo())<10){
            remedios.get(i).setIntervalo("0"+remedios.get(i).getIntervalo());
        }
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
            aux=true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Bluetoothstatus:
                if(resultCode == Activity.RESULT_OK){
                    Toast.makeText(getApplicationContext(),"O bluetooth foi ativado, clique novamente para syncronizar",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "O bluetooth não foi ativado, tente novamente", Toast.LENGTH_LONG).show();
                break;

            case newConection:
                if(resultCode==Activity.RESULT_OK){
                    String MAC = data.getExtras().getString(ListadeDispositivosActivity.Mac_Adress);

                    mydDevice = mybluetooth.getRemoteDevice(MAC);

                    //tentando efetuar uma conexão
                    try {
                        mySocket = mydDevice.createRfcommSocketToServiceRecord(MYUUID);
                        mybluetooth.cancelDiscovery(); // cancelando qualquer tipo de busca para evitar erros
                        connectedThread = new ConnectedThread(mySocket);
                        connectedThread.start();
                        mySocket.connect();
                        conection = true;
                        Toast.makeText(getApplicationContext(),"Você foi conectado com: " + MAC,Toast.LENGTH_LONG).show();
                    }catch (IOException erro){
                        conection=false;
                        Toast.makeText(getApplicationContext(),"erro: " + erro,Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Falha ao conectar, tente novamente",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void lista_de_dispositivos(){
        Intent lista = new Intent(ListaMedicamentosActivity.this,ListadeDispositivosActivity.class);
        startActivityForResult(lista,newConection);
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
        remedios = dao.buscaRemedios();
        dao.close();

        ArrayAdapter<Remedio> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, remedios);
        listaMedicamentos.setAdapter(adapter);
    }

}
