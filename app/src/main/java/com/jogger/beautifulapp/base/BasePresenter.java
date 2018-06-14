package com.jogger.beautifulapp.base;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> implements
        IPresenter<V, M> {
    protected V mView;
    protected M mModle;

    public BasePresenter() {
        mModle = attachModel();
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    protected boolean isViewAttached() {
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
