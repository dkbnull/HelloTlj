package cn.wbnull.hellotlj.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import cn.wbnull.hellotlj.view.IBaseView;

/**
 * Mvp BasePresenter
 *
 * @author dukunbiao(null)  2020-02-04
 * https://github.com/dkbnull/HelloTlj
 */
public class BasePresenter<T extends IBaseView> {

    private Reference<T> mReference;
    protected T mView;

    public void attachView(T view) {
        mReference = new WeakReference<>(view);
        if (isViewAttached()) {
            mView = getView();
        }
    }

    public void detachView() {
        if (mReference != null) {
            mReference.clear();
            mReference = null;
        }
    }

    public boolean isViewAttached() {
        return mReference != null && mReference.get() != null;
    }

    public T getView() {
        return mReference.get();
    }
}
