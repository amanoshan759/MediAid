package mediaid.dt.medical.doctor.mediaiddoctor.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Divya on 06-05-2017.
 */
public class MyEditTextBold extends EditText {
    public MyEditTextBold(Context context) {
        super(context);
        init();
    }

    public MyEditTextBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditTextBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/FaktSoftPro-Blond.ttf");
            setTypeface(tf);
        }
    }
}
