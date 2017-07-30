package mediaid.dt.medical.doctor.mediaiddoctor.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Calendar;

import mediaid.dt.medical.doctor.mediaiddoctor.MainActivity;
import mediaid.dt.medical.doctor.mediaiddoctor.R;
import mediaid.dt.medical.doctor.mediaiddoctor.beans.DoctorBean;
import mediaid.dt.medical.doctor.mediaiddoctor.beans.UserBean;
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

public class ManageProfile extends Fragment {

    int day, month, year;
    String[] value = new String[]{"Male", "Female"};
    private EditText etName, etEmail, etPhone;
    private Button btnSave;
    private TextView txtGender;
    private ImageView image;
    private Calendar cal;
    private FrameLayout frame1;
    private String msg = null, s = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_profile, container, false);
        init(view);
        SharedPreferences spp = SpUtility.getSharedPreference(getActivity());
        String im = spp.getString("image", null);
        Bitmap bm = Utility.StringToBitMap(im);
        image.setImageBitmap(bm);
        return view;
    }

    public void init(View v) {
        btnSave = (Button) v.findViewById(R.id.btnSave);
        etName = (EditText) v.findViewById(R.id.etName);
        etEmail = (EditText) v.findViewById(R.id.etEmail);
        etPhone = (EditText) v.findViewById(R.id.etPhone);
        txtGender = (TextView) v.findViewById(R.id.txtGender);
        image = (ImageView) v.findViewById(R.id.imagePerson);
        frame1 = (FrameLayout) v.findViewById(R.id.frame1);
        cal = Calendar.getInstance();
        day = cal.get(java.util.Calendar.DAY_OF_MONTH);
        month = cal.get(java.util.Calendar.MONTH);
        year = cal.get(java.util.Calendar.YEAR);
        msg = Utility.internetCheck(getActivity());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (msg.equals("no internet")) {
                    Utility.showInSnackbar(R.string.nointernet, v);
                } else {
                    frame1.setVisibility(View.VISIBLE);
                    updateDoctorProfile(btnSave);
                }
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setMessage("Choose from where to upload");
                alertBuilder.setPositiveButton("Galary", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent i2 = new Intent(Intent.ACTION_PICK);
                        i2.setType("image/*");
                        startActivityForResult(i2, 102);
                    }
                });
                alertBuilder.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent i2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i2, 1001);
                    }
                });
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();
            }
        });

        if (msg.equals("no internet")) {
            Utility.showInSnackbar(R.string.nointernet, v);
        } else {
            frame1.setVisibility(View.VISIBLE);
            getProfile();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bm);
            s = Utility.getEncoded64ImageStringFromBitmap(bm);
            SharedPreferences sp = SpUtility.getSharedPreference(getActivity());
            SharedPreferences.Editor e = sp.edit();
            e.putString("image", s);
            e.commit();
        } else if (requestCode == 102) {
            Uri u = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), u);
                image.setImageBitmap(bitmap);
                s = Utility.getEncoded64ImageStringFromBitmap(bitmap);
                SharedPreferences sp = SpUtility.getSharedPreference(getActivity());
                SharedPreferences.Editor e = sp.edit();
                e.putString("image", s);
                e.commit();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void getProfile() {
        UserBean userBean = new UserBean();
        SharedPreferences sp = SpUtility.getSharedPreference(getActivity());
        String id = sp.getString(SpUtility.KEY_USER_ID, SpUtility.DEFAULT_VALUE_STRING);
        userBean.setUser_id(Integer.parseInt(id));
        Retrofit retrofit = RestClient.build(ALLURL.APP_BASE_URL);
        UserApi userApi = retrofit.create(UserApi.class);
        Call<ResultModel<DoctorBean>> call = userApi.getProfile(userBean);
        call.enqueue(new Callback<ResultModel<DoctorBean>>() {
            @Override
            public void onResponse(Call<ResultModel<DoctorBean>> call, Response<ResultModel<DoctorBean>> response) {
                ResultModel<DoctorBean> resultmodel = response.body();
                DoctorBean doctorBean = resultmodel.getResult_data();
                etName.setText(doctorBean.getDoctor_name());
                etEmail.setText(doctorBean.getUser_email());
                etPhone.setText(doctorBean.getUser_contact());
                if (doctorBean.isDoctor_gender()) {
                    txtGender.setText("Male");
                } else {
                    txtGender.setText("Female");
                }
                txtGender.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(getActivity());
                        alertdialogbuilder.setTitle(" select Gender");
                        alertdialogbuilder.setItems(value, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String selectedText = Arrays.asList(value).get(which);
                                txtGender.setText(selectedText);
                            }
                        });
                        AlertDialog dialog = alertdialogbuilder.create();
                        dialog.show();
                    }
                });
                frame1.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResultModel<DoctorBean>> call, Throwable t) {
                t.printStackTrace();
                frame1.setVisibility(View.GONE);
            }
        });
    }


    public void updateDoctorProfile(final View v) {
        DoctorBean doctorBean = new DoctorBean();
        SharedPreferences sp = SpUtility.getSharedPreference(getActivity());
        int id = Integer.parseInt(sp.getString(SpUtility.KEY_USER_ID, SpUtility.DEFAULT_VALUE_STRING));
        doctorBean.setUser_id(id);
        doctorBean.setDoctor_name(etName.getText().toString().trim());
        doctorBean.setUser_email(etEmail.getText().toString().trim());
        doctorBean.setUser_contact(etPhone.getText().toString().trim());
        if (txtGender.getText().toString().trim().equalsIgnoreCase("Male")) {
            doctorBean.setDoctor_gender(true);
        } else {
            doctorBean.setDoctor_gender(false);
        }
        Retrofit retrofit = RestClient.build(ALLURL.APP_BASE_URL);
        UserApi userApi = retrofit.create(UserApi.class);
        Call<ResultModel> call = userApi.updateDoctorProfile(doctorBean);
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                ResultModel r = response.body();
                if (r.getResult_code() > 0) {
                    Utility.startIntent(getActivity(), MainActivity.class);
                    System.out.println("set");
                } else {
                    Utility.showInSnackbar(R.string.snackbar_updateFail, v);
                }
                frame1.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                t.printStackTrace();
                frame1.setVisibility(View.GONE);
            }
        });
    }

}