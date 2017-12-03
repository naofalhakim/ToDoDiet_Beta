package com.motionlaboratory.tododiet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.motionlaboratory.tododiet.Config.SessionPatient;
import com.motionlaboratory.tododiet.DetailPatientActivity;
import com.motionlaboratory.tododiet.Model.Patient;
import com.motionlaboratory.tododiet.R;

import java.util.List;

/**
 * Created by naofal on 7/18/2017.
 */

public class AdapterPatient extends RecyclerView.Adapter<AdapterPatient.ViewHolder> {

    public List<Patient> patientList;
    private Context context;

    public AdapterPatient(List<Patient> patientList, Context context) {
        this.patientList = patientList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_patient,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Patient dContentTemp = patientList.get(position);
        holder.txt_nama.setText(dContentTemp.getNama());
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        RelativeLayout relativeLayout;
        TextView txt_nama;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txt_nama = (TextView) itemView.findViewById(R.id.txt_nama_patient2);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rel_patient);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
                Patient contentTemp = patientList.get(position);
                Intent intent = new Intent(context, DetailPatientActivity.class);
                SessionPatient sessionPatient = new SessionPatient(context);
                sessionPatient.createPatientSession(Integer.parseInt(contentTemp.getId()));
                context.startActivity(intent);
        }
    }
}
