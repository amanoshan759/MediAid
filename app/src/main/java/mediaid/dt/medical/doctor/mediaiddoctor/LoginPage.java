package mediaid.dt.medical.doctor.mediaiddoctor;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

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

public class LoginPage extends AppCompatActivity implements Button.OnClickListener {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView txtForgotPassword;
    private FrameLayout frame1;

    private void init() {
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);
        frame1 = (FrameLayout) findViewById(R.id.frame1);
        btnLogin.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = SpUtility.getSharedPreference(getApplicationContext());
                SharedPreferences.Editor e = sp.edit();
                e.putBoolean(SpUtility.KEY_CHANGE_PASSWORD, false);
                e.commit();
                Utility.startIntent(LoginPage.this, ForgotPassword.class);
            }
        });


        if ((ActivityCompat.checkSelfPermission(LoginPage.this, "android.permission.INTERNET") != PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(LoginPage.this, "android.permission.ACCESS_NETWORK_STATE") != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(LoginPage.this, new String[]{"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"}, 1);
            return;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin: {

                Utility.hideKeyboard(LoginPage.this);
                if (etUsername.getText().toString().trim().isEmpty()) {
                    Utility.showInSnackbar(R.string.snackbar_username, v);
                } else if (etPassword.getText().toString().trim().isEmpty()) {
                    Utility.showInSnackbar(R.string.snackbar_password, v);
                } else {
                    String msg = Utility.internetCheck(getApplicationContext());
                    if (msg.equals("no internet")) {
                        Utility.showInSnackbar(R.string.nointernet, v);
                    } else {
                        frame1.setVisibility(View.VISIBLE);
                        login(btnLogin);
                    }
                }
                break;
            }

        }
    }

    void login(final View v) {
        UserBean userBean = new UserBean();
        userBean.setUser_name(etUsername.getText().toString().trim());
        userBean.setUser_password(etPassword.getText().toString().trim());
        Retrofit retrofit = RestClient.build(ALLURL.APP_BASE_URL);
        UserApi userApi = retrofit.create(UserApi.class);
        Call<ResultModel<UserBean>> call = userApi.authenticateDoctor(userBean);
        call.enqueue(new Callback<ResultModel<UserBean>>() {
            @Override
            public void onResponse(Call<ResultModel<UserBean>> call, Response<ResultModel<UserBean>> response) {
                ResultModel<UserBean> objbean = response.body();
                if (objbean.getResult_code() == 1) {
                    UserBean userBean1 = objbean.getResult_data();
                    SharedPreferences sp = SpUtility.getSharedPreference(getApplicationContext());
                    SharedPreferences.Editor e = sp.edit();
                    e.putString(SpUtility.KEY_USER_ID, String.valueOf(userBean1.getUser_id()));
                    e.putString(SpUtility.KEY_DOCTOR_ID, String.valueOf(userBean1.getDoctor_id()));
                    e.putString(SpUtility.KEY_DOCTOR_USERNAME, etUsername.getText().toString().trim());
                    e.putString(SpUtility.KEY_DOCTOR_EMAIL, userBean1.getUser_email());
                    e.putString(SpUtility.KEY_DOCTOR_CONTACT, userBean1.getUser_contact());
                    e.putString(SpUtility.KEY_DOCTOR_PASSWORD, userBean1.getUser_password());
                    e.putString(SpUtility.KEY_DOCTOR_PASSWORD, etPassword.getText().toString().trim());
                    e.commit();
                    Utility.startIntent(LoginPage.this, MainActivity.class);
                    LoginPage.this.finish();
                } else {
                    Utility.showInSnackbar(R.string.snackbar_loginfailed, v);
                }
                frame1.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResultModel<UserBean>> call, Throwable t) {
                t.printStackTrace();
                frame1.setVisibility(View.GONE);
            }
        });
    }
}
