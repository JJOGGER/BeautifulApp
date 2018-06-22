package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.FindChoiceData;
import com.jogger.beautifulapp.entity.MediaArticle;
import com.jogger.beautifulapp.function.contract.FindChoiceContract;
import com.jogger.beautifulapp.function.model.FindChoiceModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;

import java.util.ArrayList;
import java.util.List;


public class FindChoicePresenter extends BasePresenter<FindChoiceContract.View,
        FindChoiceContract.Model> implements FindChoiceContract.Presenter {
    private String mNextDate;

    @Override
    public void getFindChoiceDatas() {
        mModle.getFindChoiceDatas("0", new OnHttpRequestListener<FindChoiceData>() {
            @Override
            public void onFailure(int errorCode) {
                L.e("-------"+errorCode);
            }

            @Override
            public void onSuccess(FindChoiceData appData) {
                if (mView == null || appData == null || appData.getContent() == null) return;
                mNextDate = appData.getNext_date();
                List<MediaArticle> mediaArticles = new ArrayList<>();
                for (int i = 0; i < appData.getContent().size(); i++) {
                    MediaArticle mediaArticle = appData.getContent().get(i).getApps().get(0);
                    mediaArticle.setDate(appData.getContent().get(i).getLabel_date());
                    mediaArticles.add(mediaArticle);
                }
                mView.getFindChoiceDatasSuccess(mediaArticles);

            }
        });
    }

    @Override
    public void getMoreDatas() {
        mModle.getFindChoiceDatas(mNextDate, new OnHttpRequestListener<FindChoiceData>() {
            @Override
            public void onFailure(int errorCode) {
                if (mView == null) return;
                mView.getMoreDatasFail();
            }

            @Override
            public void onSuccess(FindChoiceData appData) {
                if (mView == null || appData == null || appData.getContent() == null) return;
                mNextDate = appData.getNext_date();
                List<MediaArticle> mediaArticles = new ArrayList<>();
                for (int i = 0; i < appData.getContent().size(); i++) {
                    mediaArticles.add(appData.getContent().get(i).getApps().get(0));
                }
                mView.getMoreDatasSuccess(mediaArticles);

            }
        });
    }


    @Override
    public FindChoiceContract.Model attachModel() {
        return new FindChoiceModel();
    }
}
