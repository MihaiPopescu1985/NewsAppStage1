package com.example.android.newsappstage1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

public class NewsLoader extends AsyncTaskLoader<String> {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    public void deliverResult(String data) {
        super.deliverResult(data);
    }

    @Override
    public String loadInBackground() {

        String toBeParsed;
        URL url;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String urlString = preferences.getString("category", null);
        if (Objects.requireNonNull(urlString).matches("Politics"))
            urlString = "http://content.guardianapis.com/search?from-date=2018-01-01&section=politics&show-tags=contributor&api-key=test";
        else if (urlString.matches("Money"))
            urlString = "http://content.guardianapis.com/search?from-date=2018-01-01&section=money&show-tags=contributor&api-key=test";
        else if (urlString.matches("Social"))
            urlString = "http://content.guardianapis.com/search?from-date=2018-01-01&section=social&show-tags=contributor&api-key=test";
        else if (urlString.matches("Technology"))
            urlString = "http://content.guardianapis.com/search?from-date=2018-01-01&section=technology&show-tags=contributor&api-key=test";

        try {
            url = new URL(urlString);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Url error !";
        }
        try {
            toBeParsed = downloadUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
            return "Downloading error !";
        }
        return toBeParsed;
    }

    private String downloadUrl(URL url) throws IOException {

        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);

            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException();
            }
            stream = connection.getInputStream();

            if (stream != null) {
                result = readStream(stream);
            }
        }
        catch(RuntimeException e) {
            return "Connection timed out.";
        }
        finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    private String readStream(InputStream inputStream) {

        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, Charset.forName("UTF-8"));

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            try {
                line = bufferedReader.readLine();
                while (line != null) {
                    output.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }
}
