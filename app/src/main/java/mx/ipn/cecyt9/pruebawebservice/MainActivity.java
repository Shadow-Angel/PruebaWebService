package mx.ipn.cecyt9.pruebawebservice;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.math.*;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends ActionBarActivity {

    TextView tvres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvres = (TextView) findViewById(R.id.textViewNumero) ;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    String numero1,numero2;
    String operador,resultadoimpre;

    public void onClickButtonUno(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText(tv.getText() + "1");
    }
    public void onClickButtonDos(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText(tv.getText() + "2");
    }
    public void onClickButtonTres(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText(tv.getText() + "3");
    }
    public void onClickButtonCuatro(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText(tv.getText() + "4");
    }
    public void onClickButtonCinco(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText(tv.getText() + "5");
    }
    public void onClickButtonSeis(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText(tv.getText() + "6");
    }
    public void onClickButtonSiete(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText(tv.getText() + "7");
    }
    public void onClickButtonOcho(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText(tv.getText() + "8");
    }
    public void onClickButtonNueve(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText(tv.getText() + "9");
    }
    public void onClickButtonCero(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText(tv.getText() + "0");
    }

    public void onClickButtonPunto(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText(tv.getText() + ".");
    }

    public void onClickOperacionCapturaNumero1(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        numero1 = tv.getText().toString();
        tv.setText("");
    }

    public void onClickSuma(View miView)
    {
        operador="Suma";
        onClickOperacionCapturaNumero1(miView);
    }
    public void onClickResta(View miView)
    {
        operador="Resta";
        onClickOperacionCapturaNumero1(miView);
    }
    public void onClickMultiplicacion(View miView)
    {
        operador="Mult";
        onClickOperacionCapturaNumero1(miView);
    }
    public void onClickDivision(View miView)
    {
        operador="Div";
        onClickOperacionCapturaNumero1(miView);
    }
    public void onClickSen(View miView)
    {
        operador="Sen";
        onClickOperacionCapturaNumero1(miView);
    }

    public void onClickCos(View miView)
    {
        operador="Cos";
        onClickOperacionCapturaNumero1(miView);
    }

    public void onClickRaiz(View miView)
    {
        operador="Raiz";
        onClickOperacionCapturaNumero1(miView);
    }

    public void onClickPotencia(View miView)
    {
        operador="Potencia";
        onClickOperacionCapturaNumero1(miView);
    }

    public void onClickResi(View miView)
    {
        operador="Resi";
        onClickOperacionCapturaNumero1(miView);
    }

    public void onClickIgual(View miView)
    {
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        numero2 = tv.getText().toString();
        try {
            llamaws Servicio = new llamaws();
            Servicio.execute();
        }catch(NumberFormatException nfe){
            Toast.makeText(this,"Numero Incorrecto", LENGTH_SHORT).show();
        }
    }

    public void onClickLimpia(View miView)
    {
        numero1="";
        numero2="";
        TextView tv = (TextView) findViewById(R.id.textViewNumero) ;
        tv.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class llamaws extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            String NAMESPACE = "http://Android.AGC.com/";
            String URL = "http://192.168.1.69:8080/CalculadoraService/Calculadora?WSDL";
            String METHOD_NAME = "CalculadoraBasica";
            String SOAP_ACTION = "http://Android.AGC.com/CalculadoraBasica";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("Operacion",operador);
            request.addProperty("Numero1",numero1);
            request.addProperty("Numero2",numero2);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);


            HttpTransportSE ht = new HttpTransportSE(URL);

            try {
                ht.call(SOAP_ACTION, envelope);
                SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                resultadoimpre=response.toString();

                Log.i("Resultado: ",resultadoimpre);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if(success==false){
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(getApplicationContext(), "El resultado es: "+resultadoimpre, Toast.LENGTH_LONG).show();
                tvres.setText(resultadoimpre.toString());

            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
    }
}