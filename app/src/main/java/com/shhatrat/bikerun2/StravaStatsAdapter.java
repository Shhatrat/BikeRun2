package com.shhatrat.bikerun2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shhatrat.bikerun2.model.AthleteDataToStats;

import java.util.List;

/**
 * Created by szymon on 13.04.17.
 */

public class StravaStatsAdapter extends RecyclerView.Adapter<StravaStatsAdapter.ViewHolder>{

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewValue;
        ImageView imageView;
        List<AthleteDataToStats.AthleteData> list;

        public ViewHolder(View itemView, List<AthleteDataToStats.AthleteData> list) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.stats_title);
            textViewValue = (TextView) itemView.findViewById(R.id.stats_value);
            imageView = (ImageView) itemView.findViewById(R.id.stats_image);
            this.list = list;
        }
 }
    private List<AthleteDataToStats.AthleteData> list;

    public StravaStatsAdapter(List<AthleteDataToStats.AthleteData> list) {
        this.list= list;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.card_view_stats, parent, false);
        return new ViewHolder(v,list);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textViewTitle.setText(list.get(position).getAttributeName());
        holder.textViewValue.setText(list.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}