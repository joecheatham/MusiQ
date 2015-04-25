package com.example.morgan.musiq;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

        String songString = pickSongIntent.getDataString();
        //songMap.put("song", songUri);
        Log.i("test", songString);
        songMap.put("song", songString);
//        encodeFileToBase64Binary(songString);
        File songFile = new File(songString);

    }

//    private String encodeFileToBase64Binary(String fileName)
//            throws IOException {
//
//        File songFile = new File(fileName);
//        byte[] bytes = loadFile(songFile);
//        byte[] encoded = Base6
//        String encodedString = new String(encoded);
//
//        return encodedString;
//    }


//    private static byte[] loadFile(File songFile) {
//        InputStream is = new FileInputStream(songFile);
//
//        long length = songFile.length();
//        if (length > Integer.MAX_VALUE) {
//            // File is too large
//            System.err.println("File too large");
//        }
//        byte[] bytes = new byte[(int)length];
//
//        int offset = 0;
//        int numRead = 0;
//        while (offset < bytes.length
//                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
//            offset += numRead;
//        }
//
//        if (offset < bytes.length) {
//            throw new IOException("Could not completely read file "+file.getName());
//        }
//
//        is.close();
//        return bytes;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Firebase.setAndroidContext(this);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "GYHVvVUSZJktwunaYuNLHOY1mkyyJUUorp8PuGyd", "CQ9Q515w6I32qPxBRb8ymIVaY5NYNvcFz7uJfsGd");

        final Firebase myFirebaseRef = new Firebase("https://musicq.firebaseio.com/tested");
        final Button button = (Button) findViewById(R.id.button);

        
        ParseFile testFile = new ParseFile("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
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
