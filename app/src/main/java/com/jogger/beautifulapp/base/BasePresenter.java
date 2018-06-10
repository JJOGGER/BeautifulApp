package com.jogger.beautifulapp.base;

/**
 * Created by Jogger on 2018/6/7.
 */

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> implements
        IPresenter<V, M> {
    protected V mView;
    protected M mModle;

    public BasePresenter() {
    }

    public BasePresenter(M modle) {
        attachModel(modle);
    }

    @Override
    public void attachModel(M model) {
        mModle = model;
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
