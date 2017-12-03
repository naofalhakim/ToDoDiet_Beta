package com.motionlaboratory.tododiet.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.motionlaboratory.tododiet.Model.TaskResult;
import com.motionlaboratory.tododiet.R;

import java.util.List;

/**
 * Created by naofal on 8/12/2017.
 */

public class AdapterTaskResult extends RecyclerView.Adapter<AdapterTaskResult.ViewHolder> {

    private Context context;
    private List<TaskResult> taskResults;

    public AdapterTaskResult(Context context, List<TaskResult> taskResults) {
        this.context = context;
        this.taskResults = taskResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_task_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskResult taskResult = taskResults.get(position);

        holder.txt_calories.setText(taskResult.getCalories_loss());
        holder.txt_distance.setText(taskResult.getDistance());
        holder.txt_done_date.setText(taskResult.getDone_date());
        holder.txt_jump_count.setText(taskResult.getJumping_count());
        holder.txt_played_time.setText(taskResult.getPlayed_time());
    }

    @Override
    public int getItemCount() {
        return taskResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_calories,txt_played_time,txt_distance, txt_jump_count,txt_done_date;
        public ViewHolder(View itemView) {
            super(itemView);

            txt_calories = (TextView) itemView.findViewById(R.id.txt_calories);
            txt_played_time = (TextView) itemView.findViewById(R.id.txt_played_time);
            txt_distance = (TextView) itemView.findViewById(R.id.txt_distance);
            txt_jump_count = (TextView) itemView.findViewById(R.id.txt_jump_count);
            txt_done_date = (TextView) itemView.findViewById(R.id.txt_done_date);
        }
    }
}
