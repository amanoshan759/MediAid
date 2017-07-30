package mediaid.dt.medical.doctor.mediaiddoctor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

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

public class ForgotPassword extends AppCompatActivity {

    int n;
    private EditText etMobile;
    private ImageButton btnEnter;
    private FrameLayout frame1;

    private void init() {
        etMobile = (EditText) findViewById(R.id.etMobile);
        btnEnter = (ImageButton) findViewById(R.id.btnEnter);
        frame1 = (FrameLayout) findViewById(R.id.frame1);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.hideKeyboard(ForgotPassword.this);
                if (etMobile.getText().toString().trim().isEmpty()) {
                    Utility.showInSnackbar(R.string.snackbar_phone, v);
                    etMobile.requestFocus();
                } else {
                    String msg = Utility.internetCheck(getApplicationContext());
                    if (msg.equals("no internet")) {
                        Utility.showInSnackbar(R.string.nointernet, v);
                    } else {
                        frame1.setVisibility(View.VISIBLE);
                        forgot(btnEnter);
                    }
                }
            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void forgot(final View v) {
        UserBean userBean = new UserBean();
        userBean.setUser_contact(etMobile.getText().toString().trim());
        Retrofit retrofit = RestClient.build(ALLURL.APP_BASE_URL);
        UserApi userApi = retrofit.create(UserApi.class);
        Call<ResultModel> call = userApi.forgotPassword(userBean);
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                ResultModel r = response.body();
                if (r.getResult_code() > 0 && r.getMsg().equals("success")) {
                    int id = r.getResult_code();
                    SendOtp sendOtp = new SendOtp();
                    sendOtp.otp(etMobile.getText().toString().trim(), ForgotPassword.this);
                    sendOtp.start();
                    Utility.startIntent(ForgotPassword.this, OTPActivity.class);
                    SharedPreferences sp = SpUtility.getSharedPreference(ForgotPassword.this);
                    SharedPreferences.Editor et = sp.edit();
                    et.putString(SpUtility.KEY_USER_ID_FORGOT, String.valueOf(id));
                    et.putString(SpUtility.KEY_FORGOT_CONTACT, etMobile.getText().toString().trim());
                    et.commit();
                    finish();
                } else {
                    Utility.showInSnackbar(R.string.snackbar_numbernotmatch, v);
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
