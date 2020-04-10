package space.kroha.restcountries;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

import space.kroha.restcountries.data.Country;
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>{

    private List<Country> countries;
    private OnCountryClickListener onCountryClickListener;
    private OnReachEndListener onReachEndListener;

    public CountryAdapter(){
        countries = new ArrayList<>();
    }


    interface OnCountryClickListener {
        void onCountryClick(int position);
    }

    interface OnReachEndListener{
        void onReachEnd();
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setOnCountryClickListener(OnCountryClickListener onCountryClickListener) {
        this.onCountryClickListener = onCountryClickListener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_item, viewGroup, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, int i) {
        if (i > countries.size() - 5 &&  onCountryClickListener != null){
            onReachEndListener.onReachEnd();
        }
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCountryClickListener != null){
                        onCountryClickListener.onCountryClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    public void addCountries(List<Country> countries) {
        this.countries.addAll(countries);
        notifyDataSetChanged();
    }

    public List<Country> getCountries() {
        return countries;
    }
}
