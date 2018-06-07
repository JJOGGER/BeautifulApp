package com.jogger.beautifulapp.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;

/**
 * Created by jogger on 2018/1/10.
 */

public interface RequestService {
    String BASE_URL = "";

    //发送验证码
    Observable<Response<ResponseBody>> test(@Field("userPage.cellphone") String account);
}
