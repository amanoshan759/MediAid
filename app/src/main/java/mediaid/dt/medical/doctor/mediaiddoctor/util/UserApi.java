package mediaid.dt.medical.doctor.mediaiddoctor.util;

import java.util.ArrayList;

import mediaid.dt.medical.doctor.mediaiddoctor.beans.AppointmentBean;
import mediaid.dt.medical.doctor.mediaiddoctor.beans.DoctorBean;
import mediaid.dt.medical.doctor.mediaiddoctor.beans.LabReportBean;
import mediaid.dt.medical.doctor.mediaiddoctor.beans.PasswordBean;
import mediaid.dt.medical.doctor.mediaiddoctor.beans.UserBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    //(@Query('abc') String a)----parameter
    @POST(ALLURL.POST_AUTHENTICATE_DOCTOR)
    Call<ResultModel<UserBean>> authenticateDoctor(@Body UserBean userbean);


    @POST(ALLURL.POST_FORGOT_PASSWORD)
    Call<ResultModel> forgotPassword(@Body UserBean userbean);

    @POST(ALLURL.POST_RESET_PASSWORD)
    Call<ResultModel> resetPasssword(@Body UserBean userbean);

    @POST(ALLURL.POST_CHANGE_PASSWORD)
    Call<ResultModel> changePassword(@Body PasswordBean passwordBean);

    @POST(ALLURL.POST_GET_PROFILE)
    Call<ResultModel<DoctorBean>> getProfile(@Body UserBean userbean);

    @POST(ALLURL.POST_UPDATE_PROFILE)
    Call<ResultModel> updateDoctorProfile(@Body DoctorBean doctorBean);

    @POST(ALLURL.POST_GET_APPOINTMENT)
    Call<ResultModel<ArrayList<AppointmentBean>>> getAppointment(@Body AppointmentBean appointmentBean);


    @POST(ALLURL.POST_PATIENT_REPORTS)
    Call<ResultModel<ArrayList<LabReportBean>>> getPatientReports(@Body LabReportBean labReportBean);


}
