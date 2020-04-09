package space.kroha.restcountries.utils;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {

    private static final String BASE_URL = "https://restcountries.eu/rest/v2/all"; //Базовая ссылка

    //метод формирования урл, если в дальнейшем понадобится его редактировать
    private static URL buildURL(){
        URL result = null;
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .build();

        try {
            result = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }
        //мето который вызывает формирование урла и передает на получение данных, возвращает массив обьектов
    public static JSONArray getJSONFromNetwork(){
        JSONArray result = null;
        URL url = buildURL();
        try {
            result = new JSONLoadTask().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    //метод загрузки данных из интернета
    private static class JSONLoadTask extends AsyncTask<URL, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(URL... urls) {
            JSONObject result = null;
            JSONArray mainObjectArray = null;
            if (urls == null || urls.length == 0){
                return null;
            }
            HttpsURLConnection connection = null;
            try {
                connection = (HttpsURLConnection) urls[0].openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();
                while (line != null){
                    builder.append(line);
                    line = reader.readLine();
                }
                mainObjectArray = new JSONArray(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null){
                    connection.disconnect();
                }
            }
            return mainObjectArray;
        }
    }

}
