package com.example.usr.mymoney;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.SectionViewHolder> {

    List<Section> sections;


    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_item, parent, false);
        SectionViewHolder sectionViewHolder = new SectionViewHolder(v);
        return sectionViewHolder;
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        holder.sectionName.setText(sections.get(position).name);
        holder.sectionAmount.setText(sections.get(position).amount);
        holder.sectionImg.setImageResource(sections.get(position).imageId);
        //holder.btnSection.setText(sections.get(position).name);
        //holder.btnSection.setCompoundDrawablesWithIntrinsicBounds(sections.get(position).imageId, 0, 0, 0);
    }

    @Override
    public int getItemCount() {
        return this.sections.size();
    }


    public RVAdapter(List<Section> sections) {
        this.sections = sections;
    }


    public static class SectionViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        //LinearLayout layout;
        TextView sectionName;
        TextView sectionAmount;
        ImageView sectionImg;
        Button btnSection;

        SectionViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv);
            //layout = (LinearLayout) itemView.findViewById(R.id.item_button);
            sectionName = (TextView) itemView.findViewById(R.id.section_name);
            sectionAmount = (TextView) itemView.findViewById(R.id.amount);
            sectionImg = (ImageView) itemView.findViewById(R.id.section_img);
            //btnSection = (Button) itemView.findViewById(R.id.btnSection);
        }
    }


}
