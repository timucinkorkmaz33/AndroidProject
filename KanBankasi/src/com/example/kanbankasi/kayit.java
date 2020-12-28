package com.example.kanbankasi;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class kayit extends Activity implements OnClickListener {
	

	
	private final String NAMESPACE = "http://tempuri.org/";
	private final String URL = "http://10.37.32.153:80/Service1.asmx?wsdl";
	private final String SOAP_ACTION = "http://tempuri.org/Kankayit";
	private final String METHOD_NAME = "Kankayit";

	private String TAG = "PGGURU";
	private static String name;
	private static String l_name;
	private static String sehir;
	private static String tel;
	private static String k_grubu;
	private static String sonuc;

	  TextView tv;
	  Spinner spinner;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kayit);
		final EditText edt1=(EditText)findViewById(R.id.editText1);
		final EditText edt2=(EditText)findViewById(R.id.editText2);
		final EditText edt3=(EditText)findViewById(R.id.editText3);
		final EditText edt4=(EditText)findViewById(R.id.editText4);
		tv=(TextView)findViewById(R.id.textView6);
		spinner=(Spinner)findViewById(R.id.spinner1);
	    
        List<String> list = new ArrayList<String>();
        list.add("0Rh(-)");
        list.add("0Rh(+)");
        list.add("ARh(-)");
        list.add("ARh(+)");
        list.add("BRh(-)");
        list.add("BRh(+)");
        list.add("ABRh(-)");
        list.add("ABRh(+)");
         
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                     (this, android.R.layout.simple_spinner_item,list);
                      
        dataAdapter.setDropDownViewResource
                     (android.R.layout.simple_spinner_dropdown_item);
                      
        spinner.setAdapter(dataAdapter);
     
		

		Button btn =(Button)findViewById(R.id.btn1);

		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				name = edt1.getText().toString();
				l_name = edt2.getText().toString();
				sehir = edt3.getText().toString();
				tel = edt4.getText().toString();
				k_grubu = String.valueOf(spinner.getSelectedItem());

			
                
                AsyncCallWS task = new AsyncCallWS();
          
               
                task.execute();
               
			}
		});
		
	}
	
	  private class AsyncCallWS extends AsyncTask<String, Void, Void> {
	    	 protected Void doInBackground(String... params) {
	             Log.i(TAG, "doInBackground");
	             ekle(name,l_name,sehir,tel,k_grubu);
	             return null;
	         }
	    	      @Override
	          protected void onPostExecute(Void result) {
	              Log.i(TAG, "onPostExecute");
	              tv.setText("Kayýt durumu(baþarýlý=1,baþarýsýz=0)="+sonuc );
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



	    public void ekle(String name,String l_name,String sehir,String tel,String k_grubu) {
	        //Create request
	        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);	
	        
	        PropertyInfo namepi = new PropertyInfo();
	        namepi.setName("name");
	        namepi.setValue(name);
	        namepi.setType(double.class);
	        request.addProperty(namepi);
	        
	        PropertyInfo lnamepi = new PropertyInfo();
	        lnamepi.setName("l_name");
	        lnamepi.setValue(l_name);
	        lnamepi.setType(double.class);
	        request.addProperty(lnamepi);
	        
	        PropertyInfo sehirpi = new PropertyInfo();
	        sehirpi.setName("sehir");
	        sehirpi.setValue(sehir);
	        sehirpi.setType(double.class);
	        request.addProperty(sehirpi);
	        
	        PropertyInfo telpi = new PropertyInfo();
	        telpi.setName("tel");
	        telpi.setValue(tel);
	        telpi.setType(double.class);
	        request.addProperty(telpi);
	        
	        PropertyInfo kgruppi = new PropertyInfo();
	        kgruppi.setName("k_grubu");
	        kgruppi.setValue(k_grubu);
	        kgruppi.setType(double.class);
	        request.addProperty(kgruppi);
	        
	        
	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(request);
	        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

	     
	     
	        try {
	           
	            androidHttpTransport.call(SOAP_ACTION, envelope);
	          
	            SoapPrimitive response =(SoapPrimitive)envelope.getResponse();
	            sonuc = response.toString();
	         
	      
	      
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	       
	    }

	  
@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}

