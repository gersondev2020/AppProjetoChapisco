package com.gautomation.chapiscorob;

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

import java.util.Set;

public class ListaDispositivos extends ListActivity {

    private BluetoothAdapter meuBluetoothAdapter = null;

    static String ENDERECO_MAC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        meuBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> dispositivosPareados = meuBluetoothAdapter.getBondedDevices();

        if(dispositivosPareados.size() > 0){
            for(BluetoothDevice dispositivo : dispositivosPareados){
                String nomeBt = dispositivo.getName();
                String macBt = dispositivo.getAddress();
                ArrayBluetooth.add(nomeBt + "\n" + macBt);
            }
        }else{
            Toast.makeText(getApplicationContext(), "!..Não a Dispositivo Pareado..!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(com.gautomation.chapiscorob.ListaDispositivos.this, MainActivity.class);
            startActivity(intent);

        }
        setListAdapter(ArrayBluetooth);
    }

    @Override
    protected void onListItemClick (ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

            String informacoegeral = ((TextView) v).getText().toString();

        //Toast.makeText(getApplicationContext(), "Info:" + informacoegeral, Toast.LENGTH_LONG).show();

        String enderecoMac = informacoegeral.substring(informacoegeral.length() - 17);

        //Toast.makeText(getApplicationContext(), "Mac" + Endereco_Mac, Toast.LENGTH_LONG).show();]

        Intent retornaMac = new Intent(com.gautomation.chapiscorob.ListaDispositivos.this, MainActivity.class);
        retornaMac.putExtra(ENDERECO_MAC, enderecoMac);
        setResult(RESULT_OK, retornaMac);
        finish();



    }

}
