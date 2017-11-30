package com.example.usr.mymoney;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.SectionViewHolder> {

    List<Section> sections;


    RVAdapter(List<Section> sections) {
        this.sections = sections;
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView sectionName;
        ImageView sectionImg;

        SectionViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv);
            sectionName = (TextView) itemView.findViewById(R.id.section_name);
            sectionImg = (ImageView) itemView.findViewById(R.id.section_img);
        }
    }


    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        SectionViewHolder sectionViewHolder = new SectionViewHolder(v);
        return sectionViewHolder;
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return this.sections.size();
    }

}
