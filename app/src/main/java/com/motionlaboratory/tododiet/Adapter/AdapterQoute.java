package com.motionlaboratory.tododiet.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.motionlaboratory.tododiet.Model.Quote;
import com.motionlaboratory.tododiet.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by naofal on 8/8/2017.
 */


public class AdapterQoute extends RecyclerView.Adapter<AdapterQoute.ViewHolder>{

    private Context context;
    private List<Quote> quoteList;

    public AdapterQoute(Context context, List<Quote> quoteList) {
        this.context = context;
        this.quoteList = quoteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_quote,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Quote quote = quoteList.get(position);
        holder.txt_quote.setText(quote.getQuotes());
    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name, txt_quote;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_quote = (TextView) itemView.findViewById(R.id.txt_quotes);
        }
    }
}
