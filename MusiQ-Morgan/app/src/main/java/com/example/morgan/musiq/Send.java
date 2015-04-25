package com.example.morgan.musiq;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;


public class Send extends ActionBarActivity {

    public void sendSong(Firebase myFirebaseRef) {
        //Code here in response to sending a string
//        myFirebaseRef.child("song/title").setValue("Hello Airmattress");
//        myFirebaseRef.child("song/artist").setValue("Hello World");
//        myFirebaseRef.child("song/user").setValue("Joe");
       Firebase songRef = myFirebaseRef.child("song");
        Map<String, String> songMap = new HashMap<String, String>();
        songMap.put("title", "title");
        songMap.put("artist", "artist");
        songMap.put("user", "user");
        songRef.setValue(songMap);
        //myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");

        //Code to have user select a song:
        Intent pickSongIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickSongIntent, 1);

        //Uri songUri = pickSongIntent.getData();
        //songMap.put("song", songUri);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Firebase.setAndroidContext(this);
        final Firebase myFirebaseRef = new Firebase("https://musicq.firebaseio.com/tested");
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendSong(myFirebaseRef);
        }
      });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send, menu);
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
}
