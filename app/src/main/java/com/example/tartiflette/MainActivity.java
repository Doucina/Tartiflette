package com.example.tartiflette;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Il faut optimiser ton code !!

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private String horoscope_list = "12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getBaseContext().getSharedPreferences("database", MODE_PRIVATE);
        // je prend database parce que c'est stylé mais attention !
        // je donne le nom que je veux hein ;)

        gson = new GsonBuilder()
                .setLenient()
                .create();

        if (sharedPreferences.contains(horoscope_list) && sharedPreferences.contains(horoscope_list)) {
            String sunsigns = sharedPreferences.getString(horoscope_list, null);
            List<String> list = gson.fromJson(sunsigns, new TypeToken<List<String>>(){}.getType());
            showList(list);
        } else {

        }

        //Il faut te supprimer mais je ne sais plus à quoi tu sert ...

        /*recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }// define an adapter
        mAdapter = new MyAdapter(input);
        recyclerView.setAdapter(mAdapter);
        */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://sandipbgt.com/theastrologer/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        HoroscopeRestApi restApi = retrofit.create(HoroscopeRestApi.class);

        Call<List<String>> call = restApi.getListTrololo();
        call.enqueue(new Callback<List<String>>() { //file d'attente
            @Override

            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
//                RestPokemonResponse restPokemonResponse = response.body();
//                List<Horoscope> listHoroscope = restPokemonResponse.getResults();
                List<String> listHoroscope = response.body();
                sharedPreferences.edit()
                        .putString(horoscope_list, gson.toJson(listHoroscope))
                        .apply();
                showList(listHoroscope);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("Erreur", "API KO");

            }
        });
    }

    private void showList(final List<String> list) {//j'ai mis final
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //use this setting to improve performance if you know that changes in content
        // do not change the layout size of the RecyclerView

        recyclerView.setHasFixedSize(true); // use a linear layout manager

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new MyAdapter(list);

        recyclerView.setAdapter(mAdapter);
        final Intent intent = new Intent (this,SecondActivity.class); //j'ai mis final
        /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String descrip = list.get(position);
                intent.putExtra("deciption_astro", descrip);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        startActivity(intent);*/
        //autre méthode
    }

}

//SharedPreferences objet de stockage
//Avant de faire l'appel REST il faut faire un if(hasDataInDatabase() )
//Si donnée dans la base on affiche cette donnée sinon appel REST
//Dans le retour de l'appel REST il faut transformer la liste en JSON et le stocker en JSON