package com.jogger.beautifulapp.http;

import com.jogger.beautifulapp.entity.AppData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

/**
 * Created by Jogger on 2018/6/3.
 */
@SuppressWarnings("unchecked")
public class HttpAction extends BaseHttpAction {
    private static HttpAction sHttpAction;

    private HttpAction() {
        super();
    }

    public static HttpAction getHttpAction() {
        if (sHttpAction == null)
            synchronized (HttpAction.class) {
                if (sHttpAction == null)
                    sHttpAction = new HttpAction();
            }
        return sHttpAction;
    }

    /**
     * 每日数据
     */
    public void getDialyDatas(int page, int page_size, OnHttpRequestListener<AppData> listener) {
        mHttpRequest.getDialyDatas(page, page_size, 2, listener);
    }

    @Override
    IHttpRequest getHttpRequest(RequestService requestService) {
        return new HttpRequestImp(requestService);
    }

    @Override
    String getBaseUrl() {
        return RequestService.BASE_URL;
    }
}
