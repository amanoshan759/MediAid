package mediaid.dt.medical.doctor.mediaiddoctor;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import mediaid.dt.medical.doctor.mediaiddoctor.util.SpUtility;

/**
 * Created by a on 11/5/17.
 */
class SendOtp extends Thread {
    String new_otp;
    Context context;
    String mobile;
    int n;

    public void otp(String mobile, Context context) {
        this.mobile = mobile;
        this.context = context;
        System.out.println(mobile + "/////////////////////////");
        System.out.println(context + "////////////////////////////");
    }

    @Override
    public void run() {
        super.run();
        try {
            Random rnd = new Random();
            n = 1000 + rnd.nextInt(900);
            new_otp = String.valueOf(n);
            SharedPreferences sp = SpUtility.getSharedPreference(context);
            SharedPreferences.Editor e = sp.edit();
            e.putString("otp", new_otp);
            e.commit();
            //String otp1 = "http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=shubhamgupta199526@gmail.com:mediaid&senderID=TEST%20SMS&receipientno="+mobile+"&msgtxt="+"This%20is%20an%20OTP%20for%20MediAid:%20"+new_otp+"%20API&state=4";
            String otp1 = "http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=objbean1@gmail.com:amandeep&senderID=TEST%20SMS&receipientno=" + mobile + "msgtxt=" + "This%20is%20an%20OTP%20for%20MediAid:%20" + new_otp + "%20API&state=4";
            System.out.println(mobile + "----------------------------------------123");
            System.out.println(new_otp + "+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(otp1);
            URL u = new URL(otp1);
            URLConnection urlconnection = u.openConnection();
            BufferedReader bs = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
            System.out.println(".......ddd........" + bs.readLine());

        } catch (Exception e1) {
            System.out.println(e1);
        }
    }
}
