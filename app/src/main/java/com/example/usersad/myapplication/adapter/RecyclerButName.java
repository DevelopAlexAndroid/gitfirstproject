package com.example.usersad.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.usersad.myapplication.R;
import com.example.usersad.myapplication.model.Mpgu;
import com.example.usersad.myapplication.model.Ptv;
import com.example.usersad.myapplication.model.Result;
import com.example.usersad.myapplication.serviceapi.AppRMNTK;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by usersad on 25.12.2017.
 */

public class RecyclerButName extends RecyclerView.Adapter<RecyclerButName.ViewHolder>{

   private List<Ptv> ptvList;
   private List<Mpgu> mpguList = new ArrayList<>();
   private OnItemClickListener listener;

   private Context mcontext;

   private int id_ptv,id,idValues;
   private Runnable task;
   private String token;

   private List<Integer> idCall = new ArrayList<>();
   private int number = 0;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){this.listener = listener;}

    public RecyclerButName (Context context,List<Ptv> ptvList){mcontext = context;this.ptvList = ptvList;}

    @Override
    public RecyclerButName.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.butname_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerButName.ViewHolder holder, final int position) {
        Ptv ptv = ptvList.get(position);
        holder.butName.setText(ptv.getName());

        id_ptv = ptvList.get(position).getId();
        Log.d("mylog", String.valueOf(id_ptv));



        holder.butName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ptvList.get(position).setVisible(!ptvList.get(position).getVisible());
                if (ptvList.get(position).getVisible()) {
                    holder.recyclerMPGU.setVisibility(View.VISIBLE);

                    Toast.makeText(view.getContext(), "МПГУ - лист!", Toast.LENGTH_SHORT).show();

                    id = ptvList.get(position).getId();

                 //   idCall.add(id);
                  //  number++;

                  Log.d("mylog", "нажата кнопка " + id);


                    task = new Runnable() {
                        @Override
                        public void run()  {                                                        //разные запросы
                            AppRMNTK.getPtvMPGU().getData(token, id,"monitor").enqueue(new Callback<Result>() {
                                @Override
                                public void onResponse(Call<Result> call, Response<Result> response) {
                                    if( response.body()!= null ) {

                                        Log.d("mylog", "нажата кнопка " + id);
                                        List<Mpgu> mpguList = response.body().getData().getMpgu();
                                        Log.d("mylog","слежка после запроса");
                                        holder.adapterMPGU = new RecyclerMPGU(mpguList);
                                        holder.adapterMPGU.additemsmpguforsize(mpguList);

                                        mpguList.get(1).getValues().setIdButton(ptvList.get(1).getIdButton());

                                        for(Mpgu mpgu: mpguList){Log.d("mylog",mpgu.getName());}
                                        Log.d("mylog","слежка после вызова метода передачи данных! Колличество элементов = " + mpguList.get(position).getState() );
                                        holder.recyclerMPGU.setAdapter(holder.adapterMPGU);

                                    }

                                }

                                @Override
                                public void onFailure(Call<Result> call, Throwable t) {
                                    Log.d("mylog","fail " + t.getMessage());
                                }
                            });
                        }
                    };
                    holder.executorService.scheduleAtFixedRate(task,0,10,TimeUnit.SECONDS);

                } else {holder.recyclerMPGU.setVisibility(View.GONE);
                        //id = 0;
                } // КОСТЫЛЬ
        }});

            if (mpguList.isEmpty()) {}else {
            mpguList.get(1).getValues().setIdButton(ptvList.get(1).getIdButton());
            holder.executorService.scheduleAtFixedRate(task,0,5,TimeUnit.SECONDS);
            Log.d("mylog","БЫЛА ВЫЗВАНА СМЕНА ДАННЫХ!");}
    }


    @Override
    public int getItemCount() {return ptvList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerMPGU;
        RecyclerMPGU adapterMPGU;
        Button butName;
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(ptvList.size());

        ViewHolder(View itemView) {
            super(itemView);

            butName = itemView.findViewById(R.id.butName);
            final Context context = itemView.getContext();
            recyclerMPGU = itemView.findViewById(R.id.recviewMPGU);
            listener.onItemClick(getAdapterPosition());
            recyclerMPGU.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        }
    }



    public void additems(List<Ptv> ptvList){
        this.ptvList.addAll(ptvList);
        notifyDataSetChanged();
}

    public void addtoken(String token){
        this.token = token;;
    }
    public void idButton(int idValues){
        this.idValues = idValues;
        task.run();
    }


}
