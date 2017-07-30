package mediaid.dt.medical.doctor.mediaiddoctor.util;


/**
 * @author DEVELOPTECH
 */

public class ResultModel<T> {
    int result_code;
    String msg;
    private T result_data;

    public T getResult_data() {
        return result_data;
    }

    public void setResult_data(T result_data) {
        this.result_data = result_data;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}