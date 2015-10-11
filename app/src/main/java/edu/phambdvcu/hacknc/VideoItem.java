package edu.phambdvcu.hacknc;



import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;

import java.util.ArrayList;

/**
 * Created by phambd on 10/10/15.
 */

public class VideoItem {
    private String title;
    private String thumbnailURL;
    private String id;
    private int rating;
    private boolean finished, current;

    public int getRating() {
        return rating;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnail) {
        this.thumbnailURL = thumbnail;
    }

    public ArrayList <NameValuePair> toMap(){
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();


        postParameters.add(new BasicNameValuePair("title", title));
        postParameters.add(new BasicNameValuePair("rating",new Integer(rating).toString()));
        postParameters.add(new BasicNameValuePair("thumbnail", thumbnailURL));
        postParameters.add(new BasicNameValuePair("id", id));
        postParameters.add(new BasicNameValuePair("finished",new Boolean(finished).toString()));
        postParameters.add(new BasicNameValuePair("current",new Boolean(current).toString()));
        postParameters.add(new BasicNameValuePair("key", Responder.SECRET_KEY));

        return postParameters;




    }

    public static  BasicHttpParams getKey(){

        BasicHttpParams params = new BasicHttpParams();
        params.setParameter("key", Responder.SECRET_KEY);
        return params;
    }



}

