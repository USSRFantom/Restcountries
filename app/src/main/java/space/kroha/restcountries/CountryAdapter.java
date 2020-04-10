package space.kroha.restcountries;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import space.kroha.restcountries.data.Country;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>{

    private ArrayList<Country> countries;

    public CountryAdapter(){
        countries = new ArrayList<>();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_item, viewGroup, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, int i) {
        Country country = countries.get(i);
        String uri = country.getFlag();
        Glide.with(countryViewHolder.itemView.getContext())
                .load(uri)
                .into(countryViewHolder.imageViewFlag);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewFlag;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFlag = itemView.findViewById(R.id.imageViewFlag);
        }
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    public void addCountries(ArrayList<Country> countries) {
        this.countries.addAll(countries);
        notifyDataSetChanged();
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }
}
