package com.example.morgan.musiq;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
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
import java.io.FileNotFoundException;
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

        //Sending audio file to parse cloud
        Uri songPathUri = pickSongIntent.getData();
        String songPath = getRealPathFromURI(getApplicationContext(), songPathUri);
        //String songPath = songPathUri.getPath();
        File songFile = new File(songPath);
        byte[] byteArray = new byte[(int) songFile.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(songFile);
            fileInputStream.read(byteArray);
            for (int i = 0; i < byteArray.length; i++) {
                System.out.print((char)byteArray[i]);
            }
        } catch (FileNotFoundException e) {
        }
        catch (IOException e1) {
        }
        //byte[] byteArray = pickSongIntent.getByteArrayExtra(songPath);


        String songFileString = songFile.toString();
        byte[] data = songFileString.getBytes();


        ParseFile testFile = new ParseFile(songPath, data);
        testFile.saveInBackground();


        ParseObject audioFile = new ParseObject("audioFile");
        audioFile.put("requesterName", "Morgan");
        audioFile.put("songFile", testFile);
        audioFile.saveInBackground();
        //File songFile = new File(songPath);

        //ParseObject audioFile = new ParseObject("audioFile");

        Log.i("test", songPath);
        songMap.put("song", songPath);

    }
    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
//    private String getRealPathFromURI(Uri contentUri) {
//        String[] proj = { MediaStore.Images.Media.DATA };
//        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
//        Cursor cursor = loader.loadInBackground();
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
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
