package mediaid.dt.medical.doctor.mediaiddoctor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import mediaid.dt.medical.doctor.mediaiddoctor.beans.PasswordBean;
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

public class ChangePassword extends AppCompatActivity implements Button.OnClickListener {

    private EditText etCurrentPassword, etNewPassword, etConfirmPassword;
    private Button btnSave, btnCancel;
    private FrameLayout frame1;
    private boolean change_password_flag = false;
    private TextView txtCurPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
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
        SharedPreferences sp = SpUtility.getSharedPreference(getApplicationContext());
        change_password_flag = sp.getBoolean(SpUtility.KEY_CHANGE_PASSWORD, SpUtility.DEFAULT_VALUE_BOOLEAN);
        if (!change_password_flag) {
            etCurrentPassword.setVisibility(View.GONE);
            txtCurPass.setVisibility(View.GONE);
        } else {
            etCurrentPassword.setVisibility(View.VISIBLE);
            txtCurPass.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        etCurrentPassword = (EditText) findViewById(R.id.etCurrentPassword);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        txtCurPass = (TextView) findViewById(R.id.txtCurPass);
        frame1 = (FrameLayout) findViewById(R.id.frame1);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave: {
                Utility.hideKeyboard(ChangePassword.this);
                if (change_password_flag && etCurrentPassword.getText().toString().trim().isEmpty()) {
                    Utility.showInSnackbar(R.string.snackbar_current_password, v);
                }
                if (etNewPassword.getText().toString().trim().isEmpty()) {
                    Utility.showInSnackbar(R.string.snackbar_new_password, v);
                    etNewPassword.requestFocus();
                } else if (etNewPassword.getText().toString().trim().length() < 6) {
                    Utility.showInSnackbar(R.string.snackbar_passwordlimit, v);
                    etNewPassword.requestFocus();
                } else if (etConfirmPassword.getText().toString().trim().isEmpty()) {
                    Utility.showInSnackbar(R.string.snackbar_confirm_password, v);
                    etConfirmPassword.requestFocus();
                } else if (!etNewPassword.getText().toString().trim().equals(etConfirmPassword.getText().toString().trim())) {
                    Utility.showInSnackbar(R.string.snackbar_passwordnotmatch, v);
                } else {
                    String msg = Utility.internetCheck(getApplicationContext());
                    if (msg.equals("no internet")) {
                        Utility.showInSnackbar(R.string.nointernet, v);
                    } else {
                        frame1.setVisibility(View.VISIBLE);
                        if (change_password_flag) {
                            changePassword(btnSave);
                        } else {
                            resetPassword(btnSave);
                        }
                    }
                }
                break;
            }
            case R.id.btnCancel: {
                if (change_password_flag) {
                    Utility.startIntent(ChangePassword.this, MainActivity.class);
                    ChangePassword.this.finish();
                } else {
                    Utility.startIntent(ChangePassword.this, LoginPage.class);
                    ChangePassword.this.finish();
                }
                break;
            }
        }
    }

    void changePassword(final View v) {
        PasswordBean passwordBean = new PasswordBean();
        passwordBean.setCurrent_password(etCurrentPassword.getText().toString().trim());
        passwordBean.setNew_password(etNewPassword.getText().toString().trim());
        SharedPreferences sp = SpUtility.getSharedPreference(getApplicationContext());
        String id = sp.getString(SpUtility.KEY_USER_ID, SpUtility.DEFAULT_VALUE_STRING);
        passwordBean.setUser_id(Integer.parseInt(id));
        Retrofit retrofit = RestClient.build(ALLURL.APP_BASE_URL);
        UserApi userApi = retrofit.create(UserApi.class);
        Call<ResultModel> call = userApi.changePassword(passwordBean);
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                ResultModel r = response.body();
                if (r.getMsg().equals("success") && r.getResult_code() == 1) {
                    Utility.startIntent(ChangePassword.this, MainActivity.class);
                    ChangePassword.this.finish();
                } else {
                    Utility.showInSnackbar(R.string.snackbar_tryAgain, v);
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

    void resetPassword(final View v) {
        UserBean userBean = new UserBean();
        userBean.setUser_password(etNewPassword.getText().toString().trim());
        SharedPreferences sp = SpUtility.getSharedPreference(getApplicationContext());
        String id = sp.getString(SpUtility.KEY_USER_ID_FORGOT, SpUtility.DEFAULT_VALUE_STRING);
        userBean.setUser_id(Integer.parseInt(id));
        Retrofit retrofit = RestClient.build(ALLURL.APP_BASE_URL);
        UserApi userApi = retrofit.create(UserApi.class);
        Call<ResultModel> call = userApi.resetPasssword(userBean);
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                ResultModel r = response.body();
                if (r.getMsg().equals("success") && r.getResult_code() == 1) {
                    Utility.startIntent(ChangePassword.this, LoginPage.class);
                    ChangePassword.this.finish();
                } else {
                    Utility.showInSnackbar(R.string.snackbar_tryAgain, v);
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
