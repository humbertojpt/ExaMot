package com.humbertojpt.moteling;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {

    ListView lista;
    ArrayAdapter adaptador;
    HttpURLConnection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        lista= (ListView) findViewById(R.id.lista);

        /*
        Comprobar la disponibilidad de la Red
         */
        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                new JsonTask().
                        execute(
                                new URL("http://moteling.azurewebsites.net/api/motels"));
            } else {
                Toast.makeText(this, "Error de conexión", Toast.LENGTH_LONG).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public class JsonTask extends AsyncTask<URL, Void, List<Motel>>{

        @Override
        protected List<Motel> doInBackground(URL... urls) {
            List<Motel> motel = null;

            try {

                // Establecer la conexión
                con = (HttpURLConnection)urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);

                // Obtener el estado del recurso
                int statusCode = con.getResponseCode();

                if(statusCode!=200) {
                    motel = new ArrayList<>();
                    motel.add(new Motel("Error","Error"));

                } else {

                    // Parsear el flujo con formato JSON
                    InputStream in = new BufferedInputStream(con.getInputStream());

                    // JsonMotelParser parser = new JsonMotelParser();
                    GsonMotelParser parser = new GsonMotelParser();

                    motel = parser.leerFlujoJson(in);


                }

            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                con.disconnect();
            }
            return motel;
        }

        @Override
        protected void onPostExecute(List<Motel> moteles) {
            /*
            Asignar los objetos de Json parseados al adaptador
             */
            if(moteles!=null) {
                adaptador = new AdaptadorDeMoteles(getBaseContext(), moteles);
                lista.setAdapter(adaptador);
            }else{
                Toast.makeText(
                        getBaseContext(),
                        "Ocurrió un error de Parsing Json",
                        Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }
}
