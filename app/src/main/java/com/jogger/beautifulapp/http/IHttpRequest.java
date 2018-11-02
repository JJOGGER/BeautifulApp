package com.jogger.beautifulapp.http;


import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by jogger on 2018/4/14.请求接口
 */

interface IHttpRequest<T> {
    void test(String account, final OnHttpRequestListener<T>
            listener);

    void getDialyDatas(int page, int page_size, int platform, OnHttpRequestListener<T> listener);

    void getFindRecentDatas(long page, int page_size, int platform, OnHttpRequestListener<T>
            listener);

    void getFindChoiceDatas(String startDate, int platform, OnHttpRequestListener<T> listener);

    void getFindRoundTopDatas(int platform, OnHttpRequestListener<T> listener);

    void getFindRoundDatas(int page, int page_size, int platform, OnHttpRequestListener<T>
            listener);

    void getFindCompilationsDatas(int page, int page_size, int platform, OnHttpRequestListener<T>
            listener);

    void getFindNiceFriendDatas(OnHttpRequestListener<T> listener);

    void getUserHomeInfo(int id, int platform, OnHttpRequestListener<T> listener);

    void getUserRecommendDatas(int userId, int page, int page_size, int platform,
                               OnHttpRequestListener<T>
                                       listener);

    void getUserCollectDatas(int userId, int page, int page_size, int platform,
                             OnHttpRequestListener<T>
                                     listener);

    void getCategoryDatas(OnHttpRequestListener<T> listener);

    void getGameDatas(String type, int page, int page_size, int platform,
                      OnHttpRequestListener<T> listener);

    void getRankDatas(int page, int page_size, int platform,
                      OnHttpRequestListener<T> listener);

    void getChoiceDescData(int id, OnHttpRequestListener<T> listener);

    void getRecentDescData(int id, OnHttpRequestListener<T> listener);

    void getCompilationDescDatas(int id, int platform, OnHttpRequestListener<T> listener);

    void getSearchTags(String type, int platform, OnHttpRequestListener<T> listener);

    void getSearchs(String keyword, int platform, OnHttpRequestListener<T> listener);

    void getCategoryMoreDatas(int id, int page, int page_size, int platform,
                              OnHttpRequestListener<T> listener);

    void getRandomDatas(int platform,
                        OnHttpRequestListener<T> listener);

    void getTagsMoreData(int id, int page, int pageSize, int platform, String type,OnHttpRequestListener<T> listener);
}
