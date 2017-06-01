package com.snehpandya.newsfeed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryResolver  {

    private QueryResolver(){}

    public static List<NewsData> fetchNewsData (String requestUrl){
        URL url = makeUrl(requestUrl);
        String jsonResponse = null;
        jsonResponse = makeHttpRequest(url);
        List <NewsData> newsData = extractFromJson(jsonResponse);
        return newsData;
    }

    private static URL makeUrl (String input){
        URL url = null;
        try {
            url = new URL(input);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest (URL url){
        String jsonResponse = "";
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        if (url == null){
            return jsonResponse;
        }
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200 ){
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream (inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  jsonResponse;
    }

    private static String readFromStream (InputStream input) throws IOException{
        StringBuilder data = new StringBuilder();
        if (input != null) {
            InputStreamReader inputStream = new InputStreamReader (input, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStream);
            String line = reader.readLine();
            while (line != null){
                data.append(line);
                line = reader.readLine();
            }
        }
        return data.toString();
    }

    private static List <NewsData> extractFromJson (String newsDataJson){
        if (TextUtils.isEmpty(newsDataJson)){
            return null;
        }

        List <NewsData> newsData = new ArrayList<>();
        try {
            JSONObject baseObject = new JSONObject(newsDataJson);
            JSONObject response = baseObject.getJSONObject("response");
            JSONArray resultArray = response.getJSONArray("results");
            for ( int i = 0; i < resultArray.length() ; i++){
                JSONObject currentNewsData = resultArray.getJSONObject(i);
                String sectionName = currentNewsData.optString("id");
                String itemType = currentNewsData.optString("type");
                String webTitle = currentNewsData.optString("webTitle");
                String webAbstract = currentNewsData.optString("webAbstract");
                Bitmap bmpImage;
                try {
                    JSONArray webImage = currentNewsData.getJSONArray("multimedia");
                    String articleImage = (webImage.getJSONObject(1)).optString("url");
                    URL url = new URL(articleImage);
                    bmpImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }
                catch (JSONException e){
                    bmpImage = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
                }
                String webUrl = currentNewsData.optString("webUrl");
                String authorName = currentNewsData.optString("byline");
                String date = currentNewsData.optString("webPublicationDate");
                String webPublicationDate = "";
                for(int j = 0 ; date.charAt(j)!='T'; j++){
                    webPublicationDate += Character.toString(date.charAt(j));
                }
                newsData.add( new NewsData(sectionName, itemType, webTitle, webAbstract,bmpImage ,authorName, webPublicationDate, webUrl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsData;
    }
}