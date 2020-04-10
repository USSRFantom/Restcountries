package space.kroha.restcountries.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static CountryDatabase database;
    private LiveData<List<Country>> countries;


    public MainViewModel(@NonNull Application application) {
        super(application);
        database = CountryDatabase.getInstance(getApplication());
        countries= database.countryDao().getAllCountry();
    }

    public Country getCoutntryById (int id){
        try {
            return new GetCountryTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteAllCountries(){

        new DeleteCountryTask().execute();
    }

    public void insertCountries(Country country){

        new InsertTask().execute(country);
    }


    public void deleteCountries(Country country){

        new DeleteTask().execute(country);
    }

    public LiveData<List<Country>> getCountries() {
        return countries;
    }

    private static class DeleteTask extends AsyncTask<Country, Void, Void>{

        @Override
        protected Void doInBackground(Country... countries) {
            if (countries != null && countries.length > 0){
                database.countryDao().deleteCountry(countries[0]);
            }
            return  null;
        }
    }




    private static class InsertTask extends AsyncTask<Country, Void, Void>{

        @Override
        protected Void doInBackground(Country... countries) {
            if (countries != null && countries.length > 0){
                database.countryDao().insertCountry(countries[0]);
            }
            return  null;
        }
    }




    private static class DeleteCountryTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... integers) {
                database.countryDao().deleteAllCountry();
            return  null;
        }
    }



    private static class GetCountryTask extends AsyncTask<Integer, Void, Country>{

        @Override
        protected Country doInBackground(Integer... integers) {
            if (integers != null && integers.length > 0){
                return database.countryDao().getCountryById(integers[0]);
            }
            return  null;
        }
    }
}
