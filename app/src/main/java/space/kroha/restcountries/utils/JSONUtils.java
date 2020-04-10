package space.kroha.restcountries.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import space.kroha.restcountries.data.Country;

public class JSONUtils {
    private static final String KEY_NAME = "name";
    private static final String KEY_CAPITAL = "capital";
    private static final String KEY_REGION = "region";
    private static final String KEY_POPULATION = "population";
    private static final String KEY_FLAG = "flag";


    //метод получение массива обьектов country
    public static ArrayList<Country> getLessonsFromJSON (JSONArray jsonArray){

        ArrayList<Country> result = new ArrayList<>();
        if (jsonArray == null){
            return result;
        }
        try {
            JSONArray jsonArrayFull = jsonArray;

            for(int i = 0; i < jsonArrayFull.length(); i++){
                JSONObject objectCountry = jsonArrayFull.getJSONObject(i);
                String name = objectCountry.getString(KEY_NAME);
                String capital = objectCountry.getString(KEY_CAPITAL);
                String region = objectCountry.getString(KEY_REGION);
                int population = objectCountry.getInt(KEY_POPULATION);
                String flag = objectCountry.getString(KEY_FLAG);
                Country country = new Country(i, name, capital, region, population, flag);
                result.add(country);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}