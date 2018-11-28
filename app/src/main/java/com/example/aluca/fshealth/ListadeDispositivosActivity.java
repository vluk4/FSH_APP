package com.example.aluca.fshealth;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Set;

public class ListadeDispositivosActivity extends ListActivity {
    private BluetoothAdapter mybluetooth = null;
    static String Mac_Adress = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> Bluetooth_List = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        mybluetooth = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pared_Devices = mybluetooth.getBondedDevices();

        if(pared_Devices.size() >0) {
            for (BluetoothDevice dispositivo : pared_Devices) {
                    String name = dispositivo.getName();
                    String mac = dispositivo.getAddress();
                    Bluetooth_List.add(name + "\n" +mac);
            }
        }
        setListAdapter(Bluetooth_List);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String Information = ((TextView)v).getText().toString();
        String mac_Adress = Information.substring(Information.length() - 17);
        //Toast.makeText(getApplicationContext(),mac_Adress,Toast.LENGTH_LONG).show();

        Intent returnMac = new Intent();
        returnMac.putExtra(Mac_Adress,mac_Adress);
        setResult(RESULT_OK,returnMac);
        finish();
    }
}
