package com.motionlaboratory.tododiet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.motionlaboratory.tododiet.DetailBCAActivity;
import com.motionlaboratory.tododiet.Model.BCA;
import com.motionlaboratory.tododiet.R;

import java.util.List;

/**
 * Created by naofal on 7/18/2017.
 */

public class AdapterBCA extends RecyclerView.Adapter<AdapterBCA.ViewHolder> {

    public List<BCA> bcaList;
    private Context context;

    public AdapterBCA(List<BCA> bcaList, Context context) {
        this.bcaList = bcaList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bca,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BCA dBCA = bcaList.get(position);
        holder.txt_id.setText(dBCA.getSerial_number());
        holder.txt_date.setText(dBCA.getDate());
    }

    @Override
    public int getItemCount() {
        return bcaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        RelativeLayout relativeLayout;
        TextView txt_id, txt_date;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txt_id = (TextView) itemView.findViewById(R.id.txt_id_bca);
            txt_date = (TextView) itemView.findViewById(R.id.txt_dodate_bca);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.l_bca1);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            BCA bca = bcaList.get(position);

            Intent intent = new Intent(context, DetailBCAActivity.class);
            intent.putExtra("date",bca.getDate());
            intent.putExtra("serial_number",bca.getSerial_number());
            intent.putExtra("bca_weight",bca.getBca_weight ());
            intent.putExtra("fat_percent",bca.getFat_percent());
            intent.putExtra("fat_mass",bca.getFat_mass());
            intent.putExtra("ffm",bca.getFfm());
            intent.putExtra("muscle_mass",bca.getMuscle_mass());
            intent.putExtra("tbw",bca.getTbw());
            intent.putExtra("tbw_percent",bca.getTbw_percent());
            intent.putExtra("bone_mass",bca.getBone_mass());
            intent.putExtra("bmr",bca.getBmr());
            intent.putExtra("metabolic_age",bca.getMbolic_age());
            intent.putExtra("visceral_fat_rating",bca.getVisceral_fat_rating());
            intent.putExtra("bca_bmi",bca.getBca_bmi());
            intent.putExtra("ideal_body_weight",bca.getIdeal_body_weight());
            intent.putExtra("degree_of_obesity_percent",bca.getDegree_of_obesity_percent());
            intent.putExtra("fat_bot_range_percent",bca.getFat_bot_range_percent());
            intent.putExtra("fat_top_range_percent",bca.getFat_top_range_percent());
            intent.putExtra("fat_mass_bot_range_percent",bca.getFat_mass_bot_range_percent());
            intent.putExtra("fat_mass_top_range_percent",bca.getFat_mass_top_range_percent());

            context.startActivity(intent);
        }
    }
}
