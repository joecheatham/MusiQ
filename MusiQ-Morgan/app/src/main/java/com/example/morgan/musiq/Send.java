package com.example.morgan.musiq;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
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
import com.parse.ProgressCallback;
import com.parse.SaveCallback;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


public class Send extends ActionBarActivity {

    protected Uri mMediaUri;
    protected String fullFilePath;
    protected File songFile;
    Firebase myFirebaseRef;

    public void sendSong() {

        //Code to have user select a song:
        Intent intent;
        intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);



    }
    private static byte[] readContentIntoByteArray(File file)
    {
        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];
        try
        {
            //convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            for (int i = 0; i < bFile.length; i++)
            {
                System.out.print((char) bFile[i]);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bFile;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Firebase.setAndroidContext(this);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "GYHVvVUSZJktwunaYuNLHOY1mkyyJUUorp8PuGyd", "CQ9Q515w6I32qPxBRb8ymIVaY5NYNvcFz7uJfsGd");

        myFirebaseRef = new Firebase("https://musicq.firebaseio.com/tested");
        final Button button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                sendSong();

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                mMediaUri = data.getData();

                fullFilePath = getPath(mMediaUri);
                songFile = new File(fullFilePath);

                byte[] pData = readContentIntoByteArray(songFile);
                ParseFile pFile = new ParseFile("song.mp3", pData);
                pFile.saveInBackground();

                ParseObject pObject = new ParseObject("audioFile");
                pObject.put("songFile", pFile);
                pObject.put("requesterName", "Joe");
                pObject.saveInBackground(new SaveCallback() {
                    public void done(ParseException e) {
                        Firebase songRef = myFirebaseRef.child("song");
                        Map<String, String> songMap = new HashMap<String, String>();
                        songMap.put("title", getTrack(songFile));
                        songMap.put("artist", getArtist(songFile));
                        songMap.put("user", "user");
                        songRef.setValue(songMap);
                    }
                });
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    private String getArtist(File file) {

        try {
            AudioFile f = AudioFileIO.read(file);
            Tag tag = f.getTag();
            return tag.getFirst("TPE1");
        } catch (Exception e){}
        return "";
    }

    private String getTrack(File file) {
        try {
            AudioFile f = AudioFileIO.read(file);
            Tag tag = f.getTag();
            return tag.getFirst("TIT2");
        } catch (Exception e) {}
        return  "";
    }

}
