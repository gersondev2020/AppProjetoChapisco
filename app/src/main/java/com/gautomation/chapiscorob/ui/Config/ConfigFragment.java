package com.gautomation.chapiscorob.ui.Config;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gautomation.chapiscorob.MainActivity;
import com.gautomation.chapiscorob.R;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



import static android.view.View.*;

public class ConfigFragment extends Fragment {

    EditText editQtdFio, editLarguraDoFio, editRPM, editPontoInicial, editPassoFriso, editRecuoAposTeminarFriso;

    private ConfigViewModel configViewModel;
    private ConfigViewModel configViewModel1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        configViewModel =
                new ViewModelProvider(this).get(ConfigViewModel.class);

        View root = inflater.inflate(R.layout.fragment_config, container, false);

        editQtdFio = root.findViewById(R.id.editQtdFio);
        editLarguraDoFio = root.findViewById(R.id.editLarguraDoFio);
        editRPM = root.findViewById(R.id.editRPM);
        editPontoInicial = root.findViewById(R.id.editPontoInicial);
        editPassoFriso = root.findViewById(R.id.editPassoFriso);
        editRecuoAposTeminarFriso = root.findViewById(R.id.editRecuoAposTeminarFriso);

//        final Button btnSalvar = root.findViewById(R.id.btnSalvar);
//        btnSalvar.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });



        //final TextView textView = root.findViewById(R.id.text_notifications);
        configViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    // =================== Menus =================================
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.salvar, menu);
       super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sairesalvar) {
            postRequest();
            Toast.makeText(getActivity(), "Dados Enviados",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    // ===========================================================

    private void postRequest() {
        configViewModel1 = configViewModel;
        RequestQueue requestQueue=Volley.newRequestQueue(getActivity().getApplicationContext());
        String url="http://192.168.4.1/config";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //let's parse json data
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    editQtdFio.setText(jsonObject.getString("nm"));
                    editLarguraDoFio.setText(jsonObject.getString("signalStrengh"));
//                    post_response_text.append("Data 2 : " + jsonObject.getString("data_2_post")+"\n");
//                    post_response_text.append("Data 3 : " + jsonObject.getString("data_3_post")+"\n");
//                    post_response_text.append("Data 4 : " + jsonObject.getString("data_4_post")+"\n");
                }
                catch (Exception e){
                    e.printStackTrace();
                    //post_response_text.setText("POST DATA : unable to Parse Json");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // post_response_text.setText("Post Data : Response Failed");
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params=new HashMap<String, String>();
                params.put("QtdFio",editQtdFio.getText().toString());
                params.put("LarguraDoFio",editLarguraDoFio.getText().toString());
                params.put("RPM",editRPM.getText().toString());
                params.put("PontoInicial",editPontoInicial.getText().toString());
                params.put("PassoFriso",editPassoFriso.getText().toString());
                params.put("RecuoAposTeminarFriso",editRecuoAposTeminarFriso.getText().toString());
                //params.put("signalStrength","true");

                return params;
            }
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError{
                Map<String,String> params=new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void sendGetRequest() {
        //get working now
        //let's try post and send some data to server
        RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        String url="http://192.168.0.4/volley_sample/get_data.php";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //get_response_text.setText("Data : "+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
//                    get_response_text.setText("Data 1 :"+jsonObject.getString("data_1")+"\n");
//                    get_response_text.append("Data 2 :"+jsonObject.getString("data_2")+"\n");
//                    get_response_text.append("Data 3 :"+jsonObject.getString("data_3")+"\n");
                }
                catch (Exception e){
                    e.printStackTrace();
                    //get_response_text.setText("Failed to Parse Json");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //get_response_text.setText("Data : Response Failed");
            }
        });

        queue.add(stringRequest);
    }

}