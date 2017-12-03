package com.motionlaboratory.tododiet.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.motionlaboratory.tododiet.Model.Badge;
import com.motionlaboratory.tododiet.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by naofal on 8/7/2017.
 */

public class AdapterBadge extends RecyclerView.Adapter<AdapterBadge.ViewHolder> {

    private List<Badge> badgeList;
    private Context context;

    public AdapterBadge(List<Badge> badgeList, Context context) {
        this.badgeList = badgeList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_badge,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Badge badge = badgeList.get(position);
        holder.txt_name.setText(badge.getNama());
        holder.txt_progres.setText(badge.getProgress()+" / " +badge.getObj());
    }

    @Override
    public int getItemCount() {
        return badgeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout relativeLayout;
        private TextView txt_progres, txt_name;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name_badge);
            txt_progres = (TextView) itemView.findViewById(R.id.txt_progress_badge);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.l_badge);
        }
    }
}
