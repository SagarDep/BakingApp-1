package com.vpaliy.bakingapp.ui.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.vpaliy.bakingapp.ui.bus.RxBus;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAdapter<T> extends RecyclerView.Adapter<AbstractAdapter.AbstractViewHolder>{

    protected LayoutInflater inflater;
    protected RxBus rxBus;
    private List<T> data;
    private boolean clicked;

    public AbstractAdapter(@NonNull Context context,
                           @NonNull RxBus rxBus){
        this.inflater=LayoutInflater.from(context);
        this.rxBus=rxBus;
        this.data=new ArrayList<>();
    }

    public abstract class AbstractViewHolder extends RecyclerView.ViewHolder{
        public AbstractViewHolder(View itemView){
            super(itemView);
        }

        abstract void onBind();
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public boolean isLocked(){
        return clicked;
    }

    public void lock(){
        clicked=false;
    }

    public void unlock(){
        clicked=true;
    }

    public void resume(){
        unlock();
    }

    public T at(int index){
        return data.get(index);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(AbstractViewHolder holder, int position) {
        holder.onBind();
    }
}