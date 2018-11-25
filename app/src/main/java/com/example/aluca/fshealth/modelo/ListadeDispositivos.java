package com.example.aluca.fshealth.modelo;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Set;

public class ListadeDispositivos extends ListActivity {
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


    }
}
