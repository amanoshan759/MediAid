package mediaid.dt.medical.doctor.mediaiddoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import mediaid.dt.medical.doctor.mediaiddoctor.util.SpUtility;

public class OTPActivity extends AppCompatActivity implements Button.OnClickListener {

    public static final String DEFAULT_VALUE = "empty";
    public static TextView txtTimer, txtMobile;
    public static TextView txtCount;
    public static EditText et1;
    public static LinearLayout llTimer;
    int count = 0;
    private Button btnVerify;
    private String otp;
    private String data = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
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
        SharedPreferences s = SpUtility.getSharedPreference(OTPActivity.this);
        txtMobile.setText(s.getString(SpUtility.KEY_FORGOT_CONTACT, SpUtility.DEFAULT_VALUE_STRING));
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et1.getText().toString().equals(otp)) {
                    txtTimer.setVisibility(View.GONE);

                    Thread timerThread = new Thread() {
                        public void run() {
                            try {
                                sleep(2000);
                            } catch (InterruptedException e) {
                                System.out.print(e);
                            } finally {

                                Intent intent = new Intent(OTPActivity.this, LoginPage.class);
                                startActivity(intent);
                                OTPActivity.this.finish();
                            }
                        }
                    };
                    timerThread.start();
                } else {


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void init() {
        btnVerify = (Button) findViewById(R.id.btnVerify);
        btnVerify.setOnClickListener(this);
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        txtMobile = (TextView) findViewById(R.id.txtMobile);
        llTimer = (LinearLayout) findViewById(R.id.llTimer);
        et1 = (EditText) findViewById(R.id.et1);
        llTimer.setOnClickListener(this);
        //btnSubmit.setOnClickListener(this);
        MyCount counter = new MyCount(60000, 1000);
        counter.start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVerify: {

                if (et1.getText().toString().isEmpty()) {
                    Toast.makeText(OTPActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println(et1.getText().toString().trim() + "/////");
                    System.out.println(data + "////////////");
                    if (et1.getText().toString().trim().equals(data)) {
                        System.out.println("------------------------");


                        Intent intent = new Intent(OTPActivity.this, ChangePassword.class);
                        startActivity(intent);

                        OTPActivity.this.finish();
                        //imgOk.setImageResource(R.mipmap.ok);
                        txtTimer.setVisibility(View.INVISIBLE);
                        Thread timerThread = new Thread() {
                            public void run() {
                                try {
                                    sleep(2000);
                                } catch (InterruptedException e) {
                                    System.out.print(e);
                                } finally {
                                }
                            }
                        };
                        timerThread.start();
                    } else {
                        Snackbar.make(v, "Wrong OTP", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                        count++;
                    }

                }
                break;
            }
            case R.id.llTimer: {
                Intent it = new Intent(OTPActivity.this, LoginPage.class);
                startActivity(it);
                break;
            }
        }
    }

}
