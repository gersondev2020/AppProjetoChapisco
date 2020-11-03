package com.gautomation.chapiscorob;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }
    public void solicita(String comando){

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        String urle = "http://192.168.0.10/" + comando;
        String urli = "http://177.101.9.56/" + comando;

        Boolean iternet = true;


        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute(urli);

        } else {
            // txtResultado.setText("Nenhuma Conexão Encontrada");
            Toast.makeText(getApplicationContext(), " Sem Conexão -;)", Toast.LENGTH_LONG).show();

        }
    }

    //============Metodo que atualiza status da tela======================
    private Runnable atualizaStatus = new Runnable() {
        @Override
        public void run() {
            solicita("");
            handler.postDelayed(this, 2000);
        }
    };
    //=============================================================
    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            Conexao conexao = new Conexao();

            return conexao.GetArduino(urls[0]);

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            if(result != null){
                //txtResultado.setText(result);
                if(result.contains("Led 1 - Ligado")){
                    //btnLed1.setBackgroundResource(R.drawable.btn_on);
                }
                if(result.contains("Led 1 - Desligado")){
                   // btnLed1.setBackgroundResource(R.drawable.btn_off);
                }
                if(result.contains("Led 2 - Ligado")){
                   // btnLed2.setBackgroundResource(R.drawable.btn_on);
                }
                if(result.contains("Led 2 - Desligado")){
                  //  btnLed2.setBackgroundResource(R.drawable.btn_off);
                }
                if(result.contains("Led 3 - Ligado")){
                  //  btnLed3.setBackgroundResource(R.drawable.btn_on);
                }
                if(result.contains("Led 3 - Desligado")){
                  //  btnLed3.setBackgroundResource(R.drawable.btn_off);
                }
            }else{
              //  txtResultado.setText("ocorrel um erro");
            }

        }
    }


}