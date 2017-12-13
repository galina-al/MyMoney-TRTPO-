package com.example.usr.mymoney.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usr.mymoney.Entity.Section;
import com.example.usr.mymoney.R;

import java.util.List;


public class RVAdapterSection extends RecyclerView.Adapter<RVAdapterSection.SectionViewHolder> {

    List<Section> sections;


    public RVAdapterSection(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_item, parent, false);
        SectionViewHolder sectionViewHolder = new SectionViewHolder(v);
        return sectionViewHolder;
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        holder.sectionName.setText(sections.get(position).getNameSection());
        holder.sectionAmount.setText(sections.get(position).getAmount());
        holder.sectionImg.setImageResource(sections.get(position).imageId);
        holder.sectionPercent.setText(sections.get(position).getPercent());
    }

    @Override
    public int getItemCount() {
        return this.sections.size();
    }

    public Section getItem(int position) {
        return sections.get(position);
    }


    public void removeItem(int position) {
        sections.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(Section section, int position) {
        sections.add(position, section);
        notifyItemInserted(position);
    }

    public void updateItem(Section section, int position) {
        sections.set(position, section);
        notifyItemChanged(position);
    }

    public void updateAll(List<Section> sectionList){
        sections = sectionList;
        notifyItemRangeChanged(1, sectionList.size());
    }
    public static class SectionViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView sectionName;
        ImageView sectionImg;
        TextView sectionAmount;
        TextView sectionPercent;

        SectionViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv);
            sectionName = (TextView) itemView.findViewById(R.id.section_name);
            sectionImg = (ImageView) itemView.findViewById(R.id.section_img);
            sectionAmount = (TextView) itemView.findViewById(R.id.section_amount);
            sectionPercent = (TextView) itemView.findViewById(R.id.section_percent);

        }

    }


}
