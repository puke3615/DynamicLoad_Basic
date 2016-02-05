package pk.proxy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import pk.dynamicload_basic.proxy.Proxy;

/**
 * @author zijiao
 * @version 2016/2/5
 * @Mark
 */
public class DogActivity extends Proxy {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout layout = new RelativeLayout(mActivity);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        Button button = new Button(mActivity);
        button.setText("ok");
        layout.addView(button, lp);
        mActivity.setContentView(layout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "every thing is ok ..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
