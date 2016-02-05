package pk.dynamicload_basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.IPlugin;

import java.io.File;

import dalvik.system.DexClassLoader;
import pk.dynamicload_basic.proxy.Proxy;
import pk.dynamicload_basic.proxy.ProxyActivity;
import pk.dynamicload_basic.proxy.ProxyHolder;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();
    private final String DEX_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "pluginTest"
            + File.separator;
    private File DEX_INNER_DIR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DEX_INNER_DIR = getDir("dex", 0);
    }

    //简单的动态加载方法
    public void compute(View view) {
//        Plugin plugin = new Plugin();
//        T(plugin.compute(0, 100));
        try {
            DexClassLoader classLoader = new DexClassLoader(DEX_PATH + "plugin.dex", DEX_INNER_DIR.getAbsolutePath(), null, getClassLoader());
            final String PLUGIN = "com.example.Plugin";
            Class cls = classLoader.loadClass(PLUGIN);
            if (cls == null) {
                T("can`t find class " + PLUGIN);
            } else {
                Object instance = cls.newInstance();
                //方式一：通过反射或调用插件
//                Method method = cls.getMethod("compute", int.class, int.class);
//                int result = (int) method.invoke(instance, 1, 100);
                //方式二：通过预定接口调用插件
                IPlugin plugin = (IPlugin) instance;
                int result = plugin.compute(1, 100);
                T("Success, the result is " + result);
            }


        } catch (Exception e) {
            e.printStackTrace();
            T(e.getMessage());
        }

    }

    //通过代理加载新的Activity
    public void proxy(View view) {
        try {
            DexClassLoader classLoader = new DexClassLoader(DEX_PATH + "proxy.dex", DEX_INNER_DIR.getAbsolutePath(), null, getClassLoader());
            //该Config应该作为通信协议，在插件诞生之前确定
            final String CONFIG = "pk.proxy.Config";
            Class configCls = classLoader.loadClass(CONFIG);
            if (configCls == null) {
                T("can`t find class " + CONFIG);
            } else {
                String proxyClassName = (String) configCls.getField("proxy").get(null);
                Class proxyClass = classLoader.loadClass(proxyClassName);
                if (proxyClass == null) {
                    T("can`t find class " + proxyClass);
                } else {
                    ProxyHolder.mProxy = (Proxy) proxyClass.newInstance();
                    startActivity(new Intent(this, ProxyActivity.class));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            T(e.getMessage());
        }
    }


    private static void L(Object s) {
        Log.i(TAG, s + "");
    }

    private void T(Object s) {
        Toast.makeText(this, s + "", Toast.LENGTH_SHORT).show();
    }

}
