package com.jogger.beautifulapp.http;

/**
 * Created by Jogger on 2018/6/3.
 */

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

    public void getEveryDayData() {
    }

    @Override
    public IHttpRequest getHttpRequest(RequestService requestService) {
        return new HttpRequestImp(requestService);
    }

    @Override
    public String getBaseUrl() {
        return RequestService.BASE_URL;
    }
}
