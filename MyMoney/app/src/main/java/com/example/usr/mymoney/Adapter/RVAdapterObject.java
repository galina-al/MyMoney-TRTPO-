package com.example.usr.mymoney.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usr.mymoney.Entity.FinanceObject;
import com.example.usr.mymoney.R;

import java.util.List;


public class RVAdapterObject extends RecyclerView.Adapter<RVAdapterObject.ObjectViewHolder> {

    List<FinanceObject> financeObjects;


    public RVAdapterObject(List<FinanceObject> objects) {
        this.financeObjects = objects;
    }

    @Override
    public ObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fin_obj, parent, false);
        ObjectViewHolder objectViewHolder = new ObjectViewHolder(v);
        return objectViewHolder;
    }

    @Override
    public void onBindViewHolder(ObjectViewHolder holder, int position) {
        holder.objectName.setText(financeObjects.get(position).getNameObj());
        holder.objectAmount.setText(financeObjects.get(position).getAmount().toString());
        holder.objectImg.setImageResource(financeObjects.get(position).getImgId());
        holder.objectDay.setText(financeObjects.get(position).getDay());
    }

    @Override
    public int getItemCount() {
        return this.financeObjects.size();
    }
    public FinanceObject getItem(int position) {
        return financeObjects.get(position);
    }


    public void removeItem(int position) {
        financeObjects.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(FinanceObject section, int position) {
        financeObjects.add(position, section);
        notifyItemInserted(position);
    }

    public void updateItem(FinanceObject section, int position) {
        financeObjects.set(position, section);
        notifyItemChanged(position);
    }

    public static class ObjectViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView objectName;
        TextView objectAmount;
        ImageView objectImg;
        TextView objectDay;

        ObjectViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_obj);
            objectName = (TextView) itemView.findViewById(R.id.obj_name);
            objectAmount = (TextView) itemView.findViewById(R.id.obj_amount);
            objectImg = (ImageView) itemView.findViewById(R.id.obj_img);
            objectDay = (TextView) itemView.findViewById(R.id.obj_day);
        }

    }


}
