package space.kroha.restcountries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import space.kroha.restcountries.data.Country;
import space.kroha.restcountries.data.MainViewModel;
import space.kroha.restcountries.utils.JSONUtils;
import space.kroha.restcountries.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCountries;
    private CountryAdapter countryAdapter;

    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        recyclerViewCountries = findViewById(R.id.recyclerViewCountries);
        recyclerViewCountries.setLayoutManager(new GridLayoutManager(this, 2));
        countryAdapter = new CountryAdapter();
        recyclerViewCountries.setAdapter(countryAdapter);

        countryAdapter.setOnCountryClickListener(new CountryAdapter.OnCountryClickListener() {
            @Override
            public void onCountryClick(int position) {
                Toast.makeText(MainActivity.this, "Clicked: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        countryAdapter.setOnReachEndListener(new CountryAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                Toast.makeText(MainActivity.this, "Конец списка", Toast.LENGTH_SHORT).show();
            }
        });

        LiveData<List<Country>> countriesFromLiveData = viewModel.getCountries();
        countriesFromLiveData.observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                countryAdapter.setCountries(countries);
            }
        });
        downloadData();
    }

    private void downloadData(){
        JSONArray jsonArray = NetworkUtils.getJSONFromNetwork();
        ArrayList<Country> countries =  JSONUtils.getLessonsFromJSON(jsonArray);
        if (countries != null && !countries.isEmpty()){
            viewModel.deleteAllCountries();
            for(Country country : countries){
                viewModel.insertCountries(country);
            }
        }

    }
}











/*

проверка полчение имени стран

 */