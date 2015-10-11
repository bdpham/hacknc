package edu.phambdvcu.hacknc;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class Client {

    public Client(){
    }

    public void post(VideoItem v, Responder R){
        PostFetcher fetcher = new PostFetcher(v,R);
        fetcher.execute();
    }
    public void get(Responder R){
        GetFetcher fetcher = new GetFetcher(R);
        fetcher.execute();
    }


    private class PostFetcher extends AsyncTask<Void, Void, String> {
        private static final String TAG = "PostFetcher";
        public static final String SERVER_URL = "https://castbox-1094.appspot.com/Video";

        Responder R;
        VideoItem v;

        public PostFetcher(VideoItem v, Responder R){
            this.R = R;
            this.v = v;


        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                //Create an HTTP client
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(SERVER_URL);
                post.setEntity(new UrlEncodedFormEntity(v.toMap()));

                //Perform the request and check the status code
                HttpResponse response = client.execute(post);
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();

                    try {
                        //Read the server response and attempt to parse it as JSON
                        Reader reader = new InputStreamReader(content);

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        List<VideoItem> videos = new ArrayList<VideoItem>();
                        videos = Arrays.asList(gson.fromJson(reader, VideoItem[].class));
                        content.close();

                        R.respond(videos);
                    } catch (Exception ex) {
                        Log.e(TAG, "Failed to parse JSON due to: " + ex);

                    }
                } else {
                    Log.e(TAG, "Server responded with status code: " + statusLine.getStatusCode());

                }
            } catch(Exception ex) {
                Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);

            }
            return null;
        }
    }
    private class GetFetcher extends AsyncTask<Void, Void, String> {
        private static final String TAG = "PostFetcher";
        public static final String SERVER_URL = "https://castbox-1094.appspot.com/Video";

        Responder R;
        VideoItem v;

        public GetFetcher(Responder R){
            this.R = R;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                //Create an HTTP client
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(SERVER_URL);

                get.setParams(VideoItem.getKey());

                //Perform the request and check the status code
                HttpResponse response = client.execute(get);
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();

                    try {
                        //Read the server response and attempt to parse it as JSON
                        Reader reader = new InputStreamReader(content);

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        List<VideoItem> videos = new ArrayList<VideoItem>();
                        videos = Arrays.asList(gson.fromJson(reader, VideoItem[].class));
                        content.close();

                        R.respond(videos);
                    } catch (Exception ex) {
                        Log.e(TAG, "Failed to parse JSON due to: " + ex);

                    }
                } else {
                    Log.e(TAG, "Server responded with status code: " + statusLine.getStatusCode());
                }
            } catch(Exception ex) {
                Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);

            }
            return null;
        }
    }

    public static void main(String []args){

        VideoItem v = new VideoItem();
        v.setCurrent(true);
        v.setId("butt");
        v.setFinished(true);
        v.setRating(5);
        v.setThumbnailURL("pic");
        v.setTitle("butthole");
        Client c = new Client();
        c.post(v,new Tester());

    }


}
