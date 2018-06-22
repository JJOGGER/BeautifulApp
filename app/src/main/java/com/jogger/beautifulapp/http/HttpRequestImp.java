package com.jogger.beautifulapp.http;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jogger.beautifulapp.entity.AppCategoryData;
import com.jogger.beautifulapp.entity.AppCollectData;
import com.jogger.beautifulapp.entity.AppCompilationsData;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.AppInfoData;
import com.jogger.beautifulapp.entity.AppMediaArticleData;
import com.jogger.beautifulapp.entity.AppNiceFriendData;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.entity.AppSocialArticleData;
import com.jogger.beautifulapp.entity.FindChoiceData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.L;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by jogger on 2018/4/14.网络请求处理
 */
@SuppressLint("CheckResult")
@SuppressWarnings("unchecked")
class HttpRequestImp implements IHttpRequest {
    private final RequestService mRequestService;
    private Gson mGson;

    HttpRequestImp(RequestService requestService) {
        mRequestService = requestService;
        mGson = new Gson();
    }

    @Override
    public void test(String account, OnHttpRequestListener listener) {
        Observable<Response<ResponseBody>> test = mRequestService.test(account);
    }

    @Override
    public void getDialyDatas(int page, int page_size, int platform, OnHttpRequestListener
            listener) {
        Observable<HttpResult<AppInfoData>> getDialyDatas = mRequestService.getDialyDatas(page,
                page_size, platform);
        enqueue(getDialyDatas, listener);
    }

    @Override
    public void getFindRecentDatas(long page, int page_size, int platform, OnHttpRequestListener
            listener) {
        Observable<HttpResult<AppRecentData>> getRecentDatas = mRequestService.getFindRecentDatas
                (page,
                        page_size, platform);
        enqueue(getRecentDatas, listener);
    }

    @Override
    public void getFindChoiceDatas(String startDate, int platform, OnHttpRequestListener listener) {
        Observable<HttpResult<FindChoiceData>> getRecentDatas = mRequestService
                .getFindChoiceDatas(startDate, platform);
        enqueue(getRecentDatas, listener);
    }

    @Override
    public void getFindRoundTopDatas(int platform, OnHttpRequestListener listener) {
        Observable<HttpResult<AppMediaArticleData>> getFindRoundTopDatas = mRequestService
                .getFindRoundTopDatas(platform);
        enqueue(getFindRoundTopDatas, listener);
    }

    @Override
    public void getFindRoundDatas(int page, int page_size, int platform, OnHttpRequestListener
            listener) {
        Observable<HttpResult<AppMediaArticleData>> getFindRoundDatas = mRequestService
                .getFindRoundDatas(page, page_size, platform);
        enqueue(getFindRoundDatas, listener);
    }

    @Override
    public void getFindCompilationsDatas(int page, int page_size, int platform,
                                         OnHttpRequestListener listener) {
        Observable<HttpResult<AppCompilationsData>> getFindCompilationsDatas = mRequestService
                .getFindCompilationsDatas(page, page_size, platform);
        enqueue(getFindCompilationsDatas, listener);
    }

    @Override
    public void getFindNiceFriendDatas(OnHttpRequestListener listener) {
        Observable<HttpResult<AppNiceFriendData>> getFindNiceFriendDatas = mRequestService
                .getFindNiceFriendDatas();
        enqueue(getFindNiceFriendDatas, listener);
    }

    @Override
    public void getUserRecommendDatas(int userId, int page, int page_size, int platform,
                                      OnHttpRequestListener listener) {
        Observable<HttpResult<AppRecentData>> getUserRecommendDatas = mRequestService
                .getUserRecommendDatas
                        (userId, page, page_size, platform);
        enqueue(getUserRecommendDatas, listener);
    }

    @Override
    public void getUserCollectDatas(int userId, int page, int page_size, int platform,
                                    OnHttpRequestListener listener) {
        Observable<HttpResult<AppCollectData>> getUserCollectDatas = mRequestService
                .getUserCollectDatas(userId, page, page_size, platform);
        enqueue(getUserCollectDatas, listener);
    }

    @Override
    public void getCategoryDatas(OnHttpRequestListener listener) {
        Observable<HttpResult<AppCategoryData>> getCategoryDatas = mRequestService
                .getCategoryDatas();
        enqueue(getCategoryDatas, listener);
    }

    @Override
    public void getGameDatas(String type, int page, int page_size, int platform,
                             OnHttpRequestListener listener) {
        Observable<HttpResult<AppInfoData>> getGameDatas = mRequestService
                .getGameDatas(type, page, page_size, platform);
        enqueue(getGameDatas, listener);
    }

    @Override
    public void getRankDatas(int page, int page_size, int platform, OnHttpRequestListener
            listener) {
        Observable<HttpResult<AppSocialArticleData>> getRankDatas = mRequestService
                .getRankDatas(page, page_size, platform);
        enqueue(getRankDatas, listener);
    }

    @Override
    public void getChoiceDescData(int id, OnHttpRequestListener listener) {
        Observable<HttpResult<AppInfo>> getChoiceDescData = mRequestService
                .getChoiceDescData(id);
        enqueue(getChoiceDescData, listener);
    }

    /**
     * 不需要解析实体类的回调
     */
    private void enqueueVoid(Observable<Response<ResponseBody>> call, final
    OnHttpRequestListener<Boolean> listener) {
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> response) throws Exception {
                        try {
                            String result = response.body().string();
                            L.e("---------enqueueVoid:" + result);
                            if (listener != null) {
                                int code = getCode(result);
                                if (code == 1) {
                                    listener.onSuccess(true);
                                } else {
                                    listener.onFailure(code);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (listener != null)
                            listener.onFailure(-1);
                    }
                });
    }


    /**
     * 自动解析
     */

    private <T> void enqueue(Observable<HttpResult<T>> call, final OnHttpRequestListener<T>
            listener) {
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HttpResult<T>>() {
                    @Override
                    public void accept(HttpResult<T> tHttpResult) throws Exception {
                        if (listener != null) {
                            if (tHttpResult.getResult() == 1)
                                listener.onSuccess(tHttpResult.getData());
                            else
                                listener.onFailure(tHttpResult.getResult());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        L.e("----------onError:" + throwable);
                        if (listener != null)
                            listener.onFailure(-1);
                    }
                });
    }

    //解决多级嵌套解析不出的问题AppData<AppInfo>
    private <T> void enqueues(Observable<Response<ResponseBody>> call, final
    OnHttpRequestListener<T>
            listener) {
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<ResponseBody>>() {
                    @Override
                    public void accept(Response<ResponseBody> response) throws Exception {
                        String result = response.body().string();
                        TypeToken<T> typeToken = new TypeToken<T>() {
                        };
                        Type type = typeToken.getType();
                        JSONObject jsonObject_all = new JSONObject(result);
                        JSONObject data = jsonObject_all.getJSONObject("data");
                        L.e("---------json:" + data.toString());
                        Object o = mGson.fromJson(data.toString(), type);
                        int code = jsonObject_all.getInt("result");
                        if (listener != null) {
                            if (code == 1)
                                listener.onSuccess((T) o);
                            else
                                listener.onFailure(code);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        L.e("----------onError:" + throwable);
                        if (listener != null)
                            listener.onFailure(-1);
                    }
                });
    }

    private int getCode(String str) {
        int code = 0;
        try {
            JSONObject json = new JSONObject(str);
            code = json.getInt("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }


}
