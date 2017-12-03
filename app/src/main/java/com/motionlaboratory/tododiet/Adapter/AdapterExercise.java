package com.motionlaboratory.tododiet.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.motionlaboratory.tododiet.Model.Task;
import com.motionlaboratory.tododiet.R;

import java.util.List;

/**
 * Created by naofal on 8/5/2017.
 */

public class AdapterExercise extends RecyclerView.Adapter<AdapterExercise.ViewHolder> {

    private List<Task> taskList;
    private Context context;

    public AdapterExercise(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_exe,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task taskTemp = taskList.get(position);
        holder.txt_type.setText(taskTemp.getType());
        holder.txt_due_date.setText(taskTemp.getDue_date());
        changeColor(holder.txt_status,taskTemp.getDone());
    }

    private void changeColor(TextView txt, int done){
        if(done == 1){
            txt.setText("Done");
            txt.setTextColor(Color.GREEN);
        }else{
            txt.setText("In Queue");
            txt.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        RelativeLayout relativeLayout;
        TextView txt_type, txt_due_date,txt_status;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rel_exe);
            txt_type = (TextView) itemView.findViewById(R.id.txt_type);
            txt_due_date = (TextView) itemView.findViewById(R.id.txt_due_date);
            txt_status = (TextView) itemView.findViewById(R.id.txt_status_exe);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
