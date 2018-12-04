package com.example.aluca.fshealth;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectedThread extends Thread {
    private static final int MESSAGE_READ = 3;
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    StringBuilder dadosBluetooth = new StringBuilder();

    public ConnectedThread(BluetoothSocket socket) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) { }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run(Handler mHandler) {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
        /*
        while (true) {
            try {
                // Read from the InputStream
                bytes = mmInStream.read(buffer);
                String dadosbt = new String(buffer,0,bytes);
                // Send the obtained bytes to the UI activity
                mHandler.obtainMessage(MESSAGE_READ, bytes, -1, dadosbt).sendToTarget();
            } catch (IOException e) {
                break;
            }
        }*/
    }

    /* Call this from the main activity to send data to the remote device */
    public void write(String input) {
        byte[] myBuffer = input.getBytes();
        try {
            mmOutStream.write(myBuffer);
        } catch (IOException e) { }
    }

    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}
