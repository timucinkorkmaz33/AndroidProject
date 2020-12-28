package com.example.kanbankasi;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Mainactivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xxx);
        
        Button btn1=(Button)findViewById(R.id.button1);
        Button btn=(Button)findViewById(R.id.button2);
        Button btn2=(Button)findViewById(R.id.Button01);
       
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				  startActivity(new Intent(Mainactivity.this,kayit.class));
			}
		});
        btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 startActivity(new Intent(Mainactivity.this,Bagisgor.class));
				
			}
		});
      
 btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 startActivity(new Intent(Mainactivity.this,sil.class));
				
			}
		});
      
    }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
