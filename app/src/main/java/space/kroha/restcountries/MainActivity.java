package space.kroha.restcountries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import java.net.URL;
import java.util.ArrayList;

import space.kroha.restcountries.data.Country;
import space.kroha.restcountries.utils.JSONUtils;
import space.kroha.restcountries.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCountries;
    private CountryAdapter countryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCountries = findViewById(R.id.recyclerViewCountries);
        recyclerViewCountries.setLayoutManager(new GridLayoutManager(this, 2));
        countryAdapter = new CountryAdapter();
        JSONArray jsonArray = NetworkUtils.getJSONFromNetwork();
        ArrayList<Country> countries =  JSONUtils.getLessonsFromJSON(jsonArray);
        countryAdapter.setCountries(countries);
        recyclerViewCountries.setAdapter(countryAdapter);






    }
}











/*

проверка полчение имени стран

 */