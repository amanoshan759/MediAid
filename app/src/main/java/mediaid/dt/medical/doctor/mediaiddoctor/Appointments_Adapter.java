package mediaid.dt.medical.doctor.mediaiddoctor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mediaid.dt.medical.doctor.mediaiddoctor.beans.AppointmentBean;

public class Appointments_Adapter extends RecyclerView.Adapter<Appointments_Adapter.MyViewHolder> {

    Context context;
    View itemView;
    ArrayList<AppointmentBean> al;

    public Appointments_Adapter(ArrayList<AppointmentBean> al) {
        this.al = al;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        itemView = LayoutInflater.from(context).inflate(R.layout.rowlayout_appointments, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final AppointmentBean appointmentBean = al.get(position);
        holder.txtAppointment.setText(String.valueOf(appointmentBean.getPatient_name()));
        String s = appointmentBean.getAppointment_time().trim();
        String s1[] = s.split(":");
        if (s1[0].equals("10")) {
            holder.txtTime.setText("10");
            holder.txtAMPM.setText("AM");
        } else if (s1[0].equals("12")) {
            holder.txtTime.setText("12");
            holder.txtAMPM.setText("NOON");
        } else if (s1[0].equals("02")) {
            holder.txtTime.setText("2");
            holder.txtAMPM.setText("PM");
        } else if (s1[0].equals("04")) {
            holder.txtTime.setText("4");
            holder.txtAMPM.setText("PM");
        } else if (s1[0].equals("06")) {
            holder.txtTime.setText("6");
            holder.txtAMPM.setText("PM");
        }
        holder.txtDate.setText(String.valueOf(appointmentBean.getAppointment_date()));
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtAppointment, txtDate, txtTime, txtAMPM;

        public MyViewHolder(View itemView) {

            super(itemView);
            txtAppointment = (TextView) itemView.findViewById(R.id.txtAppointment);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
            txtAMPM = (TextView) itemView.findViewById(R.id.txtAMPM);
        }
    }

}
