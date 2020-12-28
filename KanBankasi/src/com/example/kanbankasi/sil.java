package com.example.kanbankasi;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class sil extends Activity implements OnClickListener {

	private final String NAMESPACE = "http://tempuri.org/";
	private final String URL = "http://10.37.32.153:80/Service1.asmx?wsdl";
	private final String SOAP_ACTION = "http://tempuri.org/sil";
	private final String METHOD_NAME = "sil";

	private String TAG = "PGGURU";
	private static String k_grubu,name,l_name;
	
	private static String sonuc;
	
	String[] satir={" "};
	String ekran="";
	String[] sutun={" "};
	

	  TextView tv,tv1;
	  EditText et1,et2;
	  Spinner spinner;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sil);

		tv=(TextView)findViewById(R.id.nn);
		tv1=(TextView)findViewById(R.id.vv);
		et1=(EditText)findViewById(R.id.ed1);
		et2=(EditText)findViewById(R.id.soy);
		
	


		Button btn =(Button)findViewById(R.id.btx);

		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				name = et1.getText().toString();
				l_name=et2.getText().toString();
				
                AsyncCallWS task = new AsyncCallWS();
          
                task.execute();
               
			}
		});
		
	}
	
	  private class AsyncCallWS extends AsyncTask<String, Void, Void> {
	    	 protected Void doInBackground(String... params) {
	             Log.i(TAG, "doInBackground");
	             sil(name,l_name);
	             return null;
	         }
	    	      @Override
	          protected void onPostExecute(Void result) {
	              Log.i(TAG, "onPostExecute");
	              tv.setText("sonuc(1=silindi,0=silinemedi)="+sonuc );
	             
	          }
	   
	      @Override
	          protected void onPreExecute() {
	              Log.i(TAG, "onPreExecute");
	              tv.setText("Ýþleminiz Gerçekleþtiriliyor,Lütfen Bekleyiniz...");
	          }

	          @Override
	          protected void onProgressUpdate(Void... values) {
	              Log.i(TAG, "onProgressUpdate");
	          }

	    }

	    public void sil(String name,String l_name) {
	        //Create request
	        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);	
	        
	        PropertyInfo namepi = new PropertyInfo();
	        namepi.setName("name");
	        namepi.setValue(name);
	        namepi.setType(double.class);
	        request.addProperty(namepi);  
	        
	        PropertyInfo snamepi = new PropertyInfo();
	        snamepi.setName("l_name");
	        snamepi.setValue(l_name);
	        snamepi.setType(double.class);
	        request.addProperty(snamepi);
	        
	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(request);
	        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

	        try {
	           
	            androidHttpTransport.call(SOAP_ACTION, envelope);
	          
	            SoapPrimitive response =(SoapPrimitive)envelope.getResponse();
	          sonuc="";
	            sonuc = response.toString();
	   
	     
	        }  catch (Exception e) {
	            e.printStackTrace();
	        }

	    }
	  
@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}