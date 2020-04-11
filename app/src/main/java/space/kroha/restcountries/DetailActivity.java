package space.kroha.restcountries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import space.kroha.restcountries.data.Country;
import space.kroha.restcountries.data.MainViewModel;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageViewDeteilFlag;
    private TextView textViewName;
    private TextView textViewCapital;
    private TextView textViewRegion;
    private TextView textViewPopulation;

    private int id;
    private MainViewModel viewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageViewDeteilFlag = findViewById(R.id.imageViewDetailPlag);
        textViewName = findViewById(R.id.textViewName);
        textViewCapital = findViewById(R.id.textViewCapital);
        textViewRegion = findViewById(R.id.textViewRegion);
        textViewPopulation = findViewById(R.id.textViewPopulation);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id")){
            id = intent.getIntExtra("id", -1);
        }else {
            finish();
        }
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        Country country = viewModel.getCoutntryById(id);
        String uri = country.getFlag();
        Glide.with(this)
                .load(uri)
                .into(imageViewDeteilFlag);
        textViewName.setText(country.getName());
        textViewCapital.setText(country.getCapital());
        textViewRegion.setText(country.getRegion());
        textViewPopulation.setText(Integer.toString(country.getPopulation()));



    }
}
