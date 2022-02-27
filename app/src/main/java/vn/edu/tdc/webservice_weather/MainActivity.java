package vn.edu.tdc.webservice_weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.edu.tdc.webservice_weather.data_models.Weather;
import vn.edu.tdc.webservice_weather.data_models.WeatherAPI;
import vn.edu.tdc.webservice_weather.data_models.WeatherWrapper;

public class MainActivity extends AppCompatActivity {
    // Khai bao bien
    ArrayList<Weather> list;
    WeatherAPI weatherAPI;
    String city;
//    private RecyclerView recyclerView;
//    private ArrayList<String> listThanhPho;
    private WeatherAdapter weaAdapter;
//    private Spinner spinThanhPho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<Weather>();
        // Khoi tao bien
//        spinThanhPho = findViewById(R.id.spinThanhPho);
//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
        //list = new ArrayList<Weather>();
//        listItemWeathers = new ArrayList<>();
//        listThanhPho = new ArrayList<>();
//        data();
        weaAdapter = new WeatherAdapter(this, list);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        Spinner spinner = findViewById(R.id.spinThanhPho);
//        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinner_thanhpho));
        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(this, R.layout.spin, R.id.text ,getResources().getStringArray(R.array.spinner_thanhpho));
        spinner.setAdapter(spAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = (String) parent.getSelectedItem();
                getWeatherData(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(weaAdapter);
//        weaAdapter.setItemClickListener(itemClickListener);
//        recyclerView.setAdapter(weaAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Gán dữ liệu vào adapter cho spinner
//        ArrayAdapter<String> adapterSpin = new ArrayAdapter<String>(this, R.layout.spin_item, listThanhPho);
//        adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Thiết lập adapter cho Spinner
//        spinThanhPho.setAdapter(adapterSpin);

    }

//    private void data(){
////        listItemWeathers.add(new ItemWeather(R.drawable.mua, "Max: 23 Now: 23 Min: 22", 10000, 3.24, "2021-02-08 15:00:00"));
////        listItemWeathers.add(new ItemWeather(R.drawable.nang, "Max: 24 Now: 25 Min: 19", 8000, 3.00, "2021-02-08 18:00:00"));
////        listItemWeathers.add(new ItemWeather(R.drawable.mua, "Max: 25 Now: 26 Min: 22", 10000, 3.13, "2021-02-08 21:00:00"));
////        listItemWeathers.add(new ItemWeather(R.drawable.nang, "Max: 24 Now: 23 Min: 18", 9000, 3.24, "2021-02-09 15:00:00"));
////        listItemWeathers.add(new ItemWeather(R.drawable.mua, "Max: 23 Now: 23 Min: 22", 11000, 3.25, "2021-02-08 18:00:00"));
////        listThanhPho.add("Ho Chi Minh");
////        listThanhPho.add("Danang");
////        listThanhPho.add("Hanoi");
////        listThanhPho.add("Dongnai");
////        listThanhPho.add("Ninhbinh");
//    }

    private void getWeatherData(String city){
        list.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherAPI = retrofit.create(WeatherAPI.class);
        Call<WeatherWrapper<Weather>> call = weatherAPI.getWeathers(city, "8951d09b5580f4f349d866e30678ccb5");
        call.enqueue(new Callback<WeatherWrapper<Weather>>() {
            @Override
            public void onResponse(Call<WeatherWrapper<Weather>> call, Response<WeatherWrapper<Weather>> response) {
                if (response.isSuccessful()){
                    WeatherWrapper<Weather> weathers = response.body();
                    assert weathers != null;
                    list.addAll(weathers.items);
                    weaAdapter.notifyDataSetChanged();
                    Log.d("test", list.size() + "");
                }
            }

            @Override
            public void onFailure(Call<WeatherWrapper<Weather>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private WeatherAdapter.ItemClickListener itemClickListener = new WeatherAdapter.ItemClickListener() {
        @Override
        public void getInfor(ItemWeather item) {
            Toast.makeText(MainActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
        }
    };
}