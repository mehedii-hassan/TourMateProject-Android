package com.example.tourmatenewproject.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmatenewproject.databinding.ForecastRowDesignBinding;
import com.example.tourmatenewproject.entities.forecast.ForecastResponseModel;
import com.example.tourmatenewproject.entities.forecast.ListItem;
import com.example.tourmatenewproject.utils.Constants;
import com.example.tourmatenewproject.utils.WeatherHelperFunctions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ForecastWeatherAdapter extends RecyclerView.Adapter<ForecastWeatherAdapter.ForecastWeatherViewHolder> {
    private List<ListItem> forecastListItem;
    private int listSize;
    private ForecastResponseModel forecastResponseModel;

    public ForecastWeatherAdapter() {

        forecastListItem = new ArrayList<>();
        forecastResponseModel = new ForecastResponseModel();
    }

    @NonNull
    @Override
    public ForecastWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ForecastRowDesignBinding binding = ForecastRowDesignBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ForecastWeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastWeatherViewHolder holder, int position) {
        final ListItem listItem = forecastListItem.get(position);
        holder.bind(listItem);
    }

    public void submitNewForecastList(ForecastResponseModel forecastResponseModel) {
        forecastListItem = forecastResponseModel.getList();
        listSize = forecastResponseModel.getList().size();
        this.forecastResponseModel = forecastResponseModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listSize;
    }

    class ForecastWeatherViewHolder extends RecyclerView.ViewHolder {
        private ForecastRowDesignBinding binding;

        public ForecastWeatherViewHolder(ForecastRowDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ListItem forecastModel) {

            binding.tvForecastDate.setText(WeatherHelperFunctions.getFormattedDateTime(forecastModel.getDt(), "MMM dd, hh.mm aa"));
            binding.tvForecastHumidity.setText(String.valueOf(forecastModel.getMain().getHumidity()));
            binding.tvForecastvHighLow.setText(
                    String.format("%.0f/ %.0f\u00b0", forecastModel.getMain().getTempMax(), forecastModel.getMain().getTempMin())
            );
            binding.tvForecastCondition.setText(forecastModel.getWeather().get(0).getDescription());

            final String iconUrl = Constants.ICON_PREFIX + forecastModel.getWeather().get(0).getIcon() + Constants.ICON_SUFFIX;
            Picasso.get().load(iconUrl).into(binding.ivForecastIcon);

            binding.tvForecastPressure.setText("Pressure " + forecastModel.getMain().getPressure() + "hPa");
            binding.tvForecastHumidity.setText("Humidity " + forecastModel.getMain().getHumidity() + "%");

            long sunrise = forecastResponseModel.getCity().getSunrise();
            long sunset = forecastResponseModel.getCity().getSunset();
            binding.tvForecastSunrise.setText((WeatherHelperFunctions.getFormattedDateTime(sunrise, "hh.mm aa")));
            binding.tvForecastSunset.setText((WeatherHelperFunctions.getFormattedDateTime(sunset, "hh.mm aa")));
        }
    }
}
