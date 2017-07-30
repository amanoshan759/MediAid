package mediaid.dt.medical.doctor.mediaiddoctor.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

import mediaid.dt.medical.doctor.mediaiddoctor.Appointments_Adapter;
import mediaid.dt.medical.doctor.mediaiddoctor.R;
import mediaid.dt.medical.doctor.mediaiddoctor.beans.AppointmentBean;
import mediaid.dt.medical.doctor.mediaiddoctor.util.ALLURL;
import mediaid.dt.medical.doctor.mediaiddoctor.util.RestClient;
import mediaid.dt.medical.doctor.mediaiddoctor.util.ResultModel;
import mediaid.dt.medical.doctor.mediaiddoctor.util.SpUtility;
import mediaid.dt.medical.doctor.mediaiddoctor.util.UserApi;
import mediaid.dt.medical.doctor.mediaiddoctor.util.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Appointments extends Fragment {

    private RecyclerView recycleView;
    private FrameLayout frame1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointments, container, false);
        init(view);
        return view;
    }

    void init(View v) {
        recycleView = (RecyclerView) v.findViewById(R.id.recycleView);
        frame1 = (FrameLayout) v.findViewById(R.id.frame1);
        String msg = Utility.internetCheck(getActivity());
        if (msg.equals("no internet")) {
            Utility.showInSnackbar(R.string.nointernet, v);
        } else {
            frame1.setVisibility(View.VISIBLE);
            getAppointments();
        }

    }

    public void getAppointments() {
        AppointmentBean appointmentBean = new AppointmentBean();
        SharedPreferences s = SpUtility.getSharedPreference(getActivity());
        String id = s.getString(SpUtility.KEY_USER_ID, SpUtility.DEFAULT_VALUE_STRING);
        appointmentBean.setUser_id((Integer.parseInt(id)));
        appointmentBean.setUser_type("doctor");
        Retrofit retrofit = RestClient.build(ALLURL.APP_BASE_URL);
        UserApi userApi = retrofit.create(UserApi.class);
        Call<ResultModel<ArrayList<AppointmentBean>>> call = userApi.getAppointment(appointmentBean);
        call.enqueue(new Callback<ResultModel<ArrayList<AppointmentBean>>>() {
            @Override
            public void onResponse(Call<ResultModel<ArrayList<AppointmentBean>>> call, Response<ResultModel<ArrayList<AppointmentBean>>> response) {
                ResultModel<ArrayList<AppointmentBean>> r = response.body();
                System.out.println("=========================================+" + r.getResult_data());
                ArrayList<AppointmentBean> al = r.getResult_data();
                int id = 0;
                for (int i = 0; i < al.size(); i++) {
                    AppointmentBean appointmentBean1 = al.get(i);
                    id = appointmentBean1.getPatient_id();
                    System.out.println(appointmentBean1.getPatient_id() + "patient");
                }
                SharedPreferences sp = SpUtility.getSharedPreference(getActivity());
                SharedPreferences.Editor e = sp.edit();
                e.putInt(SpUtility.KEY_PATIENT_APPOINTMENT_ID, id);
                e.commit();
                Appointments_Adapter ca = new Appointments_Adapter(al);
                RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
                recycleView.setLayoutManager(lm);
                recycleView.setAdapter(ca);
                frame1.setVisibility(View.GONE);
            }

            public void onFailure(Call<ResultModel<ArrayList<AppointmentBean>>> call, Throwable t) {
                t.printStackTrace();
                frame1.setVisibility(View.GONE);
            }
        });
    }
}
