package mediaid.dt.medical.doctor.mediaiddoctor;

import android.os.CountDownTimer;

public class MyCount extends CountDownTimer {

    public MyCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() {
        OTPActivity.txtTimer.setText("Resend");
    }

    @Override
    public void onTick(long millisUntilFinished) {
        int sec = (int) (millisUntilFinished % 60000);
        int sec1 = sec / 1000;
        String min = String.valueOf(millisUntilFinished / 60000);
        OTPActivity.txtTimer.setText(min + ":" + sec1 + "s");

    }
}
