package cn.wbnull.hellotlj.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import butterknife.ButterKnife;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;

/**
 * Activity 基类
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
public class BaseActivity extends AppCompatActivity {

    private int layoutId = -1;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        displayInjectLayout();
        requestPermission();
        setHideVirtualKey();

        ButterKnife.bind(this);
    }

    public void setHideVirtualKey() {
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                0x00001000;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }

    private void displayInjectLayout() {
        Class<?> clazz = this.getClass();
        if (clazz.isAnnotationPresent(ActivityLayoutInject.class)) {
            ActivityLayoutInject inject = clazz.getAnnotation(ActivityLayoutInject.class);

            if (inject != null) {
                layoutId = inject.value();
                setContentView(layoutId);
            }
        }
    }

    public void requestPermission() {
        //权限检查
        if (!hasPermission()) {
            //申请权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    protected boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                //已授权
                if (grantResults[i] == 0) {
                    continue;
                }

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                    //选择禁止
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("授权");
                    builder.setMessage("需要允许授权才可使用");
                    builder.setPositiveButton("去允许", (dialog, id) -> {
                        if (mDialog != null && mDialog.isShowing()) {
                            mDialog.dismiss();
                        }
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    });
                    mDialog = builder.create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                } else {
                    //选择禁止并勾选禁止后不再询问
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("授权");
                    builder.setMessage("需要允许授权才可使用");
                    builder.setPositiveButton("去授权", (dialog, id) -> {
                        if (mDialog != null && mDialog.isShowing()) {
                            mDialog.dismiss();
                        }
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        //调起应用设置页面
                        startActivityForResult(intent, 2);
                    });
                    mDialog = builder.create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            //应用设置页面返回后再次判断权限
            requestPermission();
        }
    }

    protected String getVersion() {
        String version = "";

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);

            if (packageInfo != null) {
                version = packageInfo.versionName == null ? "" : packageInfo.versionName + "." + packageInfo.versionCode;
            }
        } catch (Exception e) {

        }

        return version;
    }
}
