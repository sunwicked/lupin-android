package app.com.application.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.com.application.R;
import app.com.application.model.Beer;

/**
 * Created by admin on 09/09/17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {


    List<Beer> beers;
    private List<Beer> filteredList;
    Context context;


    public DataAdapter(Context context, ArrayList<Beer> beers) {

        this.context = context;
        this.beers = beers;
        this.filteredList = beers;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

        holder.bindData(beers.get(position));

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                    filteredList = beers;
                } else {
                    List<Beer> filterList = new ArrayList<>();
                    for (Beer row : beers) {

                        if (row.getName().toLowerCase().contains(charString)) {
                            filterList.add(row);
                        }
                    }

                    filteredList = filterList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<Beer>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void sort(final boolean ascending) {

        Utils.INSTANCE.sort(filteredList,ascending);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvStyle;

        public ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_title);
            tvStyle = v.findViewById(R.id.tv_description);
        }

        public void bindData(Beer beer) {
            tvTitle.setText(beer.getName());
            tvStyle.setText(beer.getStyle());
        }
    }

}
