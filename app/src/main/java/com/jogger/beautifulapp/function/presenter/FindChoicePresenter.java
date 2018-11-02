package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.FindChoiceData;
import com.jogger.beautifulapp.entity.MediaArticle;
import com.jogger.beautifulapp.function.contract.FindChoiceContract;
import com.jogger.beautifulapp.function.model.FindChoiceModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;
import com.jogger.beautifulapp.util.T;

import java.util.ArrayList;
import java.util.List;


public class FindChoicePresenter extends BasePresenter<FindChoiceContract.View,
        FindChoiceContract.Model> implements FindChoiceContract.Presenter {
    private String mNextDate;

    @Override
    public void getFindChoiceDatas() {
        getView().showLoadingWindow();
        getModel().getFindChoiceDatas("0", new OnHttpRequestListener<FindChoiceData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("-------" + errorCode);
            }

            @Override
            public void onSuccess(FindChoiceData appData) {
                if (unViewAttached() || appData == null || appData.getContent() == null) return;
                getView().dismissLoadingWindow();
                mNextDate = appData.getNext_date();
                List<MediaArticle> mediaArticles = new ArrayList<>();
                for (int i = 0; i < appData.getContent().size(); i++) {
                    MediaArticle mediaArticle = appData.getContent().get(i).getApps().get(0);
                    mediaArticle.setDate(appData.getContent().get(i).getLabel_date());
                    mediaArticles.add(mediaArticle);
                }
                getView().getFindChoiceDatasSuccess(mediaArticles);

            }
        });
    }

    @Override
    public void getMoreDatas() {
        getModel().getFindChoiceDatas(mNextDate, new OnHttpRequestListener<FindChoiceData>() {
            @Override
            public void onFailure(int errorCode) {
                if (unViewAttached()) return;
                getView().getMoreDatasFail();
            }

            @Override
            public void onSuccess(FindChoiceData appData) {
                if (unViewAttached() || appData == null || appData.getContent() == null) return;
                mNextDate = appData.getNext_date();
                List<MediaArticle> mediaArticles = new ArrayList<>();
                for (int i = 0; i < appData.getContent().size(); i++) {
                    mediaArticles.add(appData.getContent().get(i).getApps().get(0));
                }
                getView().getMoreDatasSuccess(mediaArticles);
            }
        });
    }

    @Override
    public void getChoiceDescData(int id) {
        getView().showLoadingWindow();
        getModel().getChoiceDescData(id, new OnHttpRequestListener<AppInfo>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("---errorCode:" + errorCode);
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
            }

            @Override
            public void onSuccess(AppInfo appInfo) {
                if (unViewAttached()) return;
                getView().dismissLoadingWindow();
                if (appInfo == null) {
                    T.show(R.string.request_failure);
                    return;
                }
                getView().getChoiceDescDataSuccess(appInfo);
            }
        });
    }


    @Override
    public FindChoiceContract.Model attachModel() {
        return new FindChoiceModel();
    }
}
