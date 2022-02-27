package vn.edu.tdc.webservice_weather;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.tdc.webservice_weather.data_models.Weather;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    ArrayList<Weather> listItemWeathers;
    private Context context;
    ItemClickListener itemClickListener;
    final String RAIN = "Rain";
    final String CLOUDS = "Clouds";
    final String CLEAR = "Clear";

//    public void setItemClickListener(ItemClickListener itemClickListener) {
//        this.itemClickListener = itemClickListener;
//    }

    public WeatherAdapter(@NonNull Activity context, @NonNull ArrayList<Weather> objects) {

        this.context = context;

        listItemWeathers = objects;
//        this.listItemWeathers = listItemWeathers;
//        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_weather, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Weather item = listItemWeathers.get(position);
        ImageView image = new ImageView(context);

//        holder.im_item.setImageResource(item.get());
//        holder.tv_nhietdo.setText(item.get());
//        holder.tv_tamnhin.setText(String.valueOf(item.getTamNhin()) + " m");
//        holder.tv_tocdogio.setText(String.valueOf(item.getTamNhin()) + " Km/h");
//        holder.tv_thoigian.setText(String.valueOf(item.getThoiGian()));

        if(item.getWeatherItems().get(0).getWeather().equals(CLOUDS)){
            Drawable drawable = context.getResources().getDrawable(R.drawable.clounds, context.getTheme());
            image.setImageDrawable(drawable);
            holder.im_item.setImageDrawable(image.getDrawable());
        }
        else if(item.getWeatherItems().get(0).getWeather().equals(RAIN)){
            Drawable drawable = context.getResources().getDrawable(R.drawable.rain, context.getTheme());
            image.setImageDrawable(drawable);
            holder.im_item.setImageDrawable(image.getDrawable());
        }
        else if(item.getWeatherItems().get(0).getWeather().equals(CLEAR)){
            Drawable drawable = context.getResources().getDrawable(R.drawable.clear, context.getTheme());
            image.setImageDrawable(drawable);
            holder.im_item.setImageDrawable(image.getDrawable());
        }else {
//            Log.e("Bug image", item.getWeatherItems().get(0).getWeather());
            Drawable drawable = context.getResources().getDrawable(R.drawable.unknown, context.getTheme());
            image.setImageDrawable(drawable);
            holder.im_item.setImageDrawable(image.getDrawable());
        }

        holder.tv_nhietdo.setText("Max: " + (int) (item.getMain().getNhietDoMax()+0.5f) + " Now: " + (int) (item.getMain().getNhietDo()+0.5f) + " Min: " + (int) (item.getMain().getNhietDoMin()+0.5f));
        holder.tv_tocdogio.setText(item.getWind().getTocDoGio() + " km/h");
        holder.tv_tamnhin.setText(item.getTamNhinXa() + " m");
        holder.tv_thoigian.setText(item.getThoiGian());

        holder.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
//                    itemClickListener.getInfor(item);
                } else {
                    return;
                }
            }
        };
    }

    @Override
    public int getItemCount() {
        return listItemWeathers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView im_item;
        TextView tv_nhietdo, tv_tocdogio, tv_tamnhin, tv_thoigian;
        View.OnClickListener onClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            im_item = itemView.findViewById(R.id.img);
            tv_tocdogio = itemView.findViewById(R.id.txt_tocdogiosub);
            tv_tamnhin = itemView.findViewById(R.id.txt_tamnhinsub);
            tv_nhietdo = itemView.findViewById(R.id.txt_nhietdosub);
            tv_thoigian = itemView.findViewById(R.id.txt_thoigiansub);
            im_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onClickListener != null) {
                onClickListener.onClick(v);
            }
        }
    }

    public interface ItemClickListener {
        void getInfor(ItemWeather item);
    }
}
