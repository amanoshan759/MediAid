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

import mediaid.dt.medical.doctor.mediaiddoctor.R;
import mediaid.dt.medical.doctor.mediaiddoctor.Report_Adapter;
import mediaid.dt.medical.doctor.mediaiddoctor.beans.LabReportBean;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Reports extends Fragment {

    private RecyclerView recycle;
    private Report_Adapter ca;
    private FrameLayout frame1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports2, container, false);
        init(view);
        return view;
    }

    void init(View v) {
        recycle = (RecyclerView) v.findViewById(R.id.recycle);
        frame1 = (FrameLayout) v.findViewById(R.id.frame1);
        String msg = Utility.internetCheck(getActivity());
        if (msg.equals("no internet")) {
            Utility.showInSnackbar(R.string.nointernet, v);
        } else {
            frame1.setVisibility(View.VISIBLE);
            getPatientReports(v);
        }

    }

    public void getPatientReports(View v) {
        LabReportBean labReportBean = new LabReportBean();
        SharedPreferences sp = SpUtility.getSharedPreference(getActivity());
        String doctorid = sp.getString(SpUtility.KEY_DOCTOR_ID, SpUtility.DEFAULT_VALUE_STRING);
        labReportBean.setDoctor_id(Integer.parseInt(doctorid));
        Retrofit retrofit = RestClient.build(ALLURL.APP_BASE_URL);
        UserApi userApi = retrofit.create(UserApi.class);
        Call<ResultModel<ArrayList<LabReportBean>>> call = userApi.getPatientReports((labReportBean));
        call.enqueue(new Callback<ResultModel<ArrayList<LabReportBean>>>() {
            @Override
            public void onResponse(Call<ResultModel<ArrayList<LabReportBean>>> call, Response<ResultModel<ArrayList<LabReportBean>>> response) {
                ResultModel<ArrayList<LabReportBean>> r = response.body();
                ArrayList<LabReportBean> al = r.getResult_data();
                ca = new Report_Adapter(al, getActivity());
                RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
                recycle.setLayoutManager(lm);
                recycle.setAdapter(ca);
                frame1.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResultModel<ArrayList<LabReportBean>>> call, Throwable t) {
                t.printStackTrace();
                frame1.setVisibility(View.GONE);
            }
        });
    }
}