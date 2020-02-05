package cn.wbnull.hellotlj.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import butterknife.ButterKnife;
import cn.wbnull.hellotlj.anno.ActivityLayoutInject;

/**
 * Dialog 基类
 *
 * @author dukunbiao(null)  2020-02-05
 * https://github.com/dkbnull/HelloTlj
 */
public class BaseDialog extends Dialog {

    private int layoutId = -1;

    public BaseDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        displayInjectLayout();
        ButterKnife.bind(this);
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
}
