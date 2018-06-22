package com.example.usersad.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.usersad.myapplication.R;
import com.example.usersad.myapplication.model.Mpgu;
import com.example.usersad.myapplication.model.Ptv;
import com.example.usersad.myapplication.model.Value;

import java.util.List;

/**
 * Created by usersad on 25.12.2017.
 */

public class RecyclerMPGU extends RecyclerView.Adapter<RecyclerMPGU.ViewHolder> {

    private List<Mpgu> mpguList;
    private int idValues = 1 ;
    private String state,work = "work",stop = "stop",low = "low_pt003",error = "conn_err";

    public RecyclerMPGU(List<Mpgu> mpguList) {this.mpguList = mpguList;}

    @Override
    public RecyclerMPGU.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        View v = LayoutInflater.from(context).inflate(R.layout.mpgu_item, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(RecyclerMPGU.ViewHolder holder, int position) {

        state = mpguList.get(position).getState();
        idValues = mpguList.get(1).getValues().getIdButton();

        Log.d("mylog", " значение переменной " + idValues);

        Mpgu mpgu = mpguList.get(position);

        holder.textNumber.setText("мпгу " + String.valueOf(mpgu.getBmpguMonNum()));

        if (idValues == 1) {
            holder.butVal.setText(mpgu.getValues().getpSteam());
        } else if (idValues == 2) {
            holder.butVal.setText(mpgu.getValues().getWater());
        } else if (idValues == 3) {
            holder.butVal.setText(mpgu.getValues().getGas());
        } else if (idValues == 4) {
            holder.butVal.setText(String.valueOf(mpgu.getValues().getAlpha()));
        } else {
            holder.butVal.setText(mpgu.getValues().getSteam());
        }

        if (state.equals(work)){
            holder.butVal.setBackgroundResource(R.drawable.but_work);
            holder.textNumber.setBackgroundResource(R.color.MPGUworkName);
        }else if (state.equals(stop)){
            holder.butVal.setBackgroundResource(R.drawable.but_stop);
            holder.textNumber.setBackgroundResource(R.color.MPGUstopName);
        }else if (state.equals(error)){
            holder.butVal.setBackgroundResource(R.drawable.but_error);
            holder.textNumber.setBackgroundResource(R.color.MPGUerrorName);
        }else {
            holder.butVal.setBackgroundResource(R.drawable.but_low);
            holder.textNumber.setBackgroundResource(R.color.MPGUlowName);
        }

    }


    @Override
    public int getItemCount() {return mpguList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textNumber;
        private Button butVal;

        public ViewHolder(View itemView) {
            super(itemView);
            textNumber = itemView.findViewById(R.id.textNumber);
            butVal = itemView.findViewById(R.id.butVal);
        }
    }

    public void additemsmpguforsize(List<Mpgu> mpguList){
        this.mpguList = mpguList ;
        notifyDataSetChanged();

    }


}

