package edu.phambdvcu.hacknc;



import android.content.Context;
        import android.util.Log;

        import com.google.api.client.auth.oauth2.Credential;
        import com.google.api.client.googleapis.json.GoogleJsonResponseException;
        import com.google.api.client.http.HttpRequest;
        import com.google.api.client.http.HttpRequestInitializer;
        import com.google.api.client.http.javanet.NetHttpTransport;
        import com.google.api.client.json.jackson2.JacksonFactory;
        import com.google.api.services.youtube.YouTube;
        import com.google.api.services.youtube.model.*;
        import com.google.common.collect.Lists;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;


public class PlaylistUpdates {

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private YouTube youtube;
    private YouTube.Search.List searcher;
    private static final long NUMBER_OF_VIDEOS_RETURNED = 15;

    static public final String DEVELOPER_KEY = "AIzaSyA30-Hg8KHpMmqjuy-wMC1IKOpEybeUMIk";


    public PlaylistUpdates(Context content) {
        youtube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest hr) throws IOException {}
        }).setApplicationName(content.getString(R.string.app_name)).build();

        try{
            searcher = youtube.search().list("id,snippet");
            searcher.setKey(DEVELOPER_KEY);
            searcher.setType("video");
            searcher.setFields("items(id/videoId,snippet/title,snippet/thumbnails/default/url)");
            searcher.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
        }catch(IOException e){
            Log.d("YC", "Could not initialize: " + e);
        }
    }
    public List<VideoItem> search(String keywords){
        searcher.setQ(keywords);
        try{
            SearchListResponse response = searcher.execute();
            List<SearchResult> results = response.getItems();

            List<VideoItem> items = new ArrayList<VideoItem>();
            for(SearchResult result:results){
                VideoItem item = new VideoItem();
                item.setTitle(result.getSnippet().getTitle());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setId(result.getId().getVideoId());
                items.add(item);
            }
            return items;
        }catch(IOException e){
            Log.d("YC", "Could not search: "+e);
            return null;
        }
    }
}


