package com.jogger.beautifulapp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> implements
        IPresenter<V, M> {
    private M mModel;
    private Reference<V> mVMReference;

    public BasePresenter() {
        mModel = attachModel();
    }

    @Override
    public void attachView(V view) {
        mVMReference = new WeakReference<>(view);
    }

    protected V getView() {
        return mVMReference.get();
    }

    protected M getModel() {
        return mModel;
    }

    @Override
    public void detachView() {
        if (mVMReference != null) {
            mVMReference.clear();
            mVMReference = null;
        }
        mModel = null;
    }

    public boolean unViewAttached() {//判断是否与View建立了关联
        return mVMReference == null || mVMReference.get() == null;
    }
}
