package mediaid.dt.medical.doctor.mediaiddoctor.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtility {
    public final static String KEY_DOCTOR_USERNAME = "doctor_username";
    public final static String KEY_DOCTOR_EMAIL = "doctor_email";
    public final static String KEY_DOCTOR_CONTACT = "doctor_contact";
    public final static String KEY_DOCTOR_PASSWORD = "doctor_password";
    public final static String KEY_USER_ID = "user_id";
    public final static String KEY_DOCTOR_ID = "doctor_id";
    public final static String DEFAULT_VALUE_STRING = "null";
    public final static boolean DEFAULT_VALUE_BOOLEAN = false;
    public final static String KEY_PATIENT_APPOINTMENT_ID = "patient_appoint_id";
    public final static String KEY_CHANGE_PASSWORD = "change_password_key";
    public final static String KEY_USER_ID_FORGOT = "forgot_user_id";
    public final static String KEY_FORGOT_CONTACT = "forgot_contact";
    public final static int DEFAULT_VALUE_INT = 0;
    public final static String KEY_FLAG_LOGIN = "login_flag";

    public static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences("meddoc", Context.MODE_PRIVATE);
    }

    public static void clearSharedPreference(Context context) {
        SharedPreferences sp = context.getSharedPreferences("meddoc", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.clear();
        e.commit();
    }
}
