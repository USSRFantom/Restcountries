package space.kroha.restcountries.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {
        @Query("SELECT * FROM countries")
    LiveData<List<Country>> getAllCountry();


        @Query("SELECT * FROM countries WHERE id == :countryId")
        Country getCountryById(int countryId);

        @Query("DELETE FROM countries")
        void deleteAllCountry();

        @Insert
        void insertCountry(Country country);

        @Delete
        void deleteCountry(Country country);
}
