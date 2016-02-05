package pk.dynamicload_basic.proxy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author zijiao
 * @version 2016/2/5
 * @Mark
 */
public class ProxyActivity extends Activity {

    private Proxy mProxy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProxy = ProxyHolder.mProxy;
        if (mProxy != null) {
            mProxy.mActivity = this;
            mProxy.onCreate(savedInstanceState);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mProxy != null) {
            mProxy.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mProxy != null) {
            mProxy.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mProxy != null) {
            mProxy.onPause();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mProxy != null) {
            mProxy.onRestart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mProxy != null) {
            mProxy.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProxy != null) {
            mProxy.onDestroy();
            mProxy = null;
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        if (mProxy != null) {
            mProxy.startActivityForResult(intent, requestCode, options);
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (mProxy != null) {
            mProxy.finish();
        }
    }
}
