package com.example.usersad.myapplication.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.usersad.myapplication.R;
import com.example.usersad.myapplication.adapter.RecyclerButName;
import com.example.usersad.myapplication.model.Data;
import com.example.usersad.myapplication.model.Mpgu;
import com.example.usersad.myapplication.model.Ptv;
import com.example.usersad.myapplication.model.Result;
import com.example.usersad.myapplication.model.Value;
import com.example.usersad.myapplication.serviceapi.AppRMNTK;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RmntkActivity extends AppCompatActivity implements RecyclerButName.OnItemClickListener,View.OnClickListener {

    private List<Ptv> ptvList = new ArrayList<>();
    private List<Mpgu> mpguList = new ArrayList<>();
    private List<Data> dataList = new ArrayList<>();
    Context context;

    private Button butSteam,butWater,butGas,butAlfa,butPSteam;

    private RecyclerButName.OnItemClickListener listener;
    private RecyclerView recyclerview;
    int idValues;
    private RecyclerButName adapter = new RecyclerButName(context, ptvList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmntk);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>РМНТК - термические системы</font>"));


        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        recyclerview.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        butSteam = findViewById(R.id.butSteam);
        butSteam.setOnClickListener(this);
        butWater = findViewById(R.id.butWater);
        butWater.setOnClickListener(this);
        butGas = findViewById(R.id.butGas);
        butGas.setOnClickListener(this);
        butAlfa = findViewById(R.id.butAlfa);
        butAlfa.setOnClickListener(this);
        butPSteam = findViewById(R.id.butPSteam);
        butPSteam.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("main",MODE_PRIVATE);
        String token = sharedPreferences.getString("my_string","").toString();

        Log.d("mylog",token);

        adapter.addtoken(token);

        AppRMNTK.getPtvName().getData(token).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.body() != null){

                    List<Ptv> ptvList = response.body().getData().getPtv();

                    adapter.additems(ptvList);

                for (Ptv ptv: ptvList){Log.d("mylog","name = " + ptv.getName() +" id = "+ ptv.getId());}
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.butSteam:
                idValues = 5;
                ptvList.get(1).setIdButton(idValues);
                adapter.idButton(idValues);
                break;
            case R.id.butWater:
               idValues = 2;
               ptvList.get(1).setIdButton(idValues);
                adapter.idButton(idValues);
                break;
            case R.id.butGas:
                idValues = 3;
                ptvList.get(1).setIdButton(idValues);
                adapter.idButton(idValues);
                break;
            case R.id.butAlfa:
                idValues = 4;
                ptvList.get(1).setIdButton(idValues);
                adapter.idButton(idValues);
                break;
            case R.id.butPSteam:
                idValues = 1;
                ptvList.get(1).setIdButton(idValues);
                adapter.idButton(idValues);
                break;
        }
    }


    @Override
    public void onItemClick(final int position) {}
}
