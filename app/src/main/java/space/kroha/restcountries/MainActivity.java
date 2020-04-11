package space.kroha.restcountries;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.widget.Toast;

import org.json.JSONArray;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import space.kroha.restcountries.data.Country;
import space.kroha.restcountries.data.MainViewModel;
import space.kroha.restcountries.utils.JSONUtils;
import space.kroha.restcountries.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<JSONArray> {

    private RecyclerView recyclerViewCountries;
    private CountryAdapter countryAdapter;

    private MainViewModel viewModel;
    private static final int LOADER_ID = 133;
    private LoaderManager loaderManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        recyclerViewCountries = findViewById(R.id.recyclerViewCountries);
        recyclerViewCountries.setLayoutManager(new GridLayoutManager(this, 2));
        countryAdapter = new CountryAdapter();
        recyclerViewCountries.setAdapter(countryAdapter);
        loaderManager = LoaderManager.getInstance(this);

        countryAdapter.setOnCountryClickListener(new CountryAdapter.OnCountryClickListener() {
            @Override
            public void onCountryClick(int position) {
                Country country = countryAdapter.getCountries().get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("id", country.getId());
                startActivity(intent);
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
        URL url = NetworkUtils.buildURL();
        Bundle bundle = new Bundle();
        bundle.putString("url", url.toString());
        loaderManager.restartLoader(LOADER_ID, bundle, this);

    }

    @NonNull
    @Override
    public Loader<JSONArray> onCreateLoader(int id, @Nullable Bundle bundle) {
        NetworkUtils.JSONLoader jsonLoader = new NetworkUtils.JSONLoader(this, bundle);
        return jsonLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<JSONArray> loader, JSONArray jsonArray1) {
        JSONArray jsonArray = NetworkUtils.getJSONFromNetwork();
        ArrayList<Country> countries =  JSONUtils.getLessonsFromJSON(jsonArray1);
        if (countries != null && !countries.isEmpty()){
            viewModel.deleteAllCountries();
            for(Country country : countries){
                viewModel.insertCountries(country);
            }
        }
        loaderManager.destroyLoader(LOADER_ID);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<JSONArray> loader) {

    }
}











/*

проверка полчение имени стран

 */