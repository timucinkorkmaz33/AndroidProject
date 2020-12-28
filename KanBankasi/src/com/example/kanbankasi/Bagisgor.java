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

public class Bagisgor extends Activity implements OnClickListener {

	private final String NAMESPACE = "http://tempuri.org/";
	private final String URL = "http://10.37.32.153:80/Service1.asmx?wsdl";
	private final String SOAP_ACTION = "http://tempuri.org/satiral";
	private final String METHOD_NAME = "satiral";

	private String TAG = "PGGURU";
	private static String id;
	
	private static String sonuc;
	
	String[] satir={" "};
	String ekran="";
	String[] sutun={" "};
	

	  TextView tv,aa;
	  Spinner spinner;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bagisgor);

		tv=(TextView)findViewById(R.id.aa);
		aa=(TextView)findViewById(R.id.cc);
	
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

		Button btn =(Button)findViewById(R.id.btx);

		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				id = String.valueOf(spinner.getSelectedItem());
				
                AsyncCallWS task = new AsyncCallWS();
          
                task.execute();
               
			}
		});
		
	}
	
	  private class AsyncCallWS extends AsyncTask<String, Void, Void> {
	    	 protected Void doInBackground(String... params) {
	             Log.i(TAG, "doInBackground");
	             sorgula(id);
	             return null;
	         }
	    	      @Override
	          protected void onPostExecute(Void result) {
	              Log.i(TAG, "onPostExecute");
	              //tv.setText("sonuc="+sonuc );
	              tv.setText(ekran);
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

	    public void sorgula(String id) {
	        //Create request
	        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);	
	        
	        PropertyInfo namepi = new PropertyInfo();
	        namepi.setName("id");
	        namepi.setValue(id);
	        namepi.setType(double.class);
	        request.addProperty(namepi);
	    
	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.dotNet=true;
	        envelope.setOutputSoapObject(request);
	        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

	        try {
	           
	            androidHttpTransport.call(SOAP_ACTION, envelope);
	          
	            SoapPrimitive response =(SoapPrimitive)envelope.getResponse();
	          sonuc="";
	            sonuc = response.toString();
	            satir = sonuc.split("//");
	            ekran="";
	            for (int k=0;k<satir.length;k++){
	            	
	            	sutun = satir[k].split(";");
	            	ekran += sutun[0] + " " + sutun[1]+" "+sutun[2] + " " +sutun[3] + " " +sutun[4] + "\n";
	            	}
	         
	   
	        }  catch (Exception e) {
	            e.printStackTrace();
	        }

	    }
	  
@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}