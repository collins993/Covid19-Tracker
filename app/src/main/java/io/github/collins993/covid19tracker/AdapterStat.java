package io.github.collins993.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterStat extends RecyclerView.Adapter<AdapterStat.MyViewHolder> implements Filterable {

    private Context context;
    public ArrayList<ModelStat> statArrayList, filterList;
    private FilterStat filter;

    public AdapterStat(Context context, ArrayList<ModelStat> statArrayList) {
        this.context = context;
        this.statArrayList = statArrayList;
        this.filterList = statArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_stat, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ModelStat modelStat = statArrayList.get(position);

        String country = modelStat.getCountry();
        String totalConfirmed = modelStat.getTotalConfirmed();
        String totalDeaths = modelStat.getTotalDeaths();
        String totalRecovered = modelStat.getTotalRecovered();
        String newConfirmed = modelStat.getNewConfirmed();
        String newDeaths = modelStat.getNewDeaths();
        String newRecovered = modelStat.getNewRecovered();

        holder.countryTxt.setText(country);
        holder.todayRecoveredTxt.setText(newRecovered);
        holder.todayDeathsTxt.setText(newDeaths);
        holder.todayCasesTxt.setText(newConfirmed);
        holder.recoveredTxt.setText(totalRecovered);
        holder.deathsTxt.setText(totalDeaths);
        holder.casesTxt.setText(totalConfirmed);

    }

    @Override
    public int getItemCount() {
        return statArrayList.size();
    }

    @Override
    public Filter getFilter() {

        if (filter == null) {
            filter = new FilterStat(this, filterList);
        }
        return filter;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView countryTxt, casesTxt, todayCasesTxt, deathsTxt, todayDeathsTxt, recoveredTxt, todayRecoveredTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            countryTxt = itemView.findViewById(R.id.country_txt);
            casesTxt = itemView.findViewById(R.id.case_txt);
            todayCasesTxt = itemView.findViewById(R.id.today_case_txt);
            deathsTxt = itemView.findViewById(R.id.deaths_txt);
            todayDeathsTxt = itemView.findViewById(R.id.today_death_txt);
            recoveredTxt = itemView.findViewById(R.id.recovered_txt);
            todayRecoveredTxt = itemView.findViewById(R.id.today_recovered_txt);

        }
    }
}
