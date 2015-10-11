package edu.phambdvcu.hacknc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.api.services.youtube.YouTube;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    static private final String DEVELOPER_KEY = "AIzaSyBtTsxC0FM6u5kzQWRikQdBh0IZ2MkisVY";
    TextView nowPlaying;
    Button searchBt;
//    TextView searchBox;
    ListView playList;
    PlaylistUpdates findVid;


    static private final String VIDEO = "ZItRY-14ZBA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nowPlaying = (TextView) findViewById(R.id.nowPlayingTitle);
        searchBt = (Button) findViewById(R.id.searchButton );
//        nowPlaying = (TextView) findViewById(R.id.nowPlayingLabel);
//        searchBox = (TextView) findViewById(R.id.searchButton);
//        playList = (ListView) findViewById(R.id.listViewBox);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void search(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
