package com.example.tartiflette;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }// define an adapter
        mAdapter = new MyAdapter(input);
        recyclerView.setAdapter(mAdapter);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://sandipbgt.com/theastrologer/api")
                //je ne suis pas sûr de s'il faut ajouter sunsigns à la fin ...
                // A vérifier ;)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HoroscopeRestApi restApi = retrofit.create(HoroscopeRestApi.class);
        Call<List<String>> call = restApi.getListTrololo();
        call.enqueue(new Callback<List<String>>() { //file d'attente
            @Override

            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
//                RestPokemonResponse restPokemonResponse = response.body();
//                List<Horoscope> listHoroscope = restPokemonResponse.getResults();
                showList(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("Erreur", "API KO");

            }
        });
    }

    private void showList(List<String> list) {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //use this setting to improve performance if you know that changes in content
        // do not change the layout size of the RecyclerView

        recyclerView.setHasFixedSize(true); // use a linear layout manager

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new MyAdapter(list);

        recyclerView.setAdapter(mAdapter);
    }


    //Intent intent = new Intent(this, SecondActivity.class);
        //String message = mMessageEditText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);*/
        //startActivity(intent);
//}
}