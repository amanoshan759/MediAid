package mediaid.dt.medical.doctor.mediaiddoctor.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Divya on 06-05-2017.
 */
public class MyTextViewMedium extends TextView {
    public MyTextViewMedium(Context context) {
        super(context);
        init();
    }

    public MyTextViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextViewMedium(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/FaktSoftPro-Medium.ttf");
            setTypeface(tf);
        }
    }
}
