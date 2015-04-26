package com.mjs.musiq_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by morgan on 4/25/15.
 */

public class QrActivity extends ActionBarActivity {
    public String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ActionBar actionbar = getActionBar();
        //actionbar.hide();
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, IntentIntegrator.class);
        //setContentView(R.layout.activity_qr);
        onActivityResult(5,5,intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_qr, menu);
        return true;
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

    public String getQrContents(){
        return temp;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            temp = scanResult.getContents();
            Log.i("test", getQrContents());
            Intent intent1 = new Intent(QrActivity.this, Send.class);
            intent1.putExtra("qrResults", temp);
            startActivity(intent1);
        }
    }

    public String getResults(){
        return temp;
    }




}

