package com.jogger.beautifulapp.http;

import com.jogger.beautifulapp.entity.AppData;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jogger on 2018/1/10.
 */

public interface RequestService {
    String BASE_URL = "http://zuimeia.com/api/";

    Observable<Response<ResponseBody>> test(@Field("userPage.cellphone") String account);

    //    Observable<HttpResult<AppData>> getDialyDatas(@Field("platform") int platform1,
//                                                  @Field("openUDID") String openUDID,
//                                                  @Field("appVersion") String appVersion,
//                                                  @Field("appVersionCode") int appVersionCode,
//                                                  @Field("systemVersion") int systemVersion,
//                                                  @Field("resolution") String resolution,
//                                                  @Field("platform") int platform2,
//                                                  @Field("app_client") int app_client,
//                                                  @Field("phoneModel") int phoneModel);
    @GET("apps/app/daily")
    Observable<HttpResult<AppData>> getDialyDatas(@Query("page") int page,
                                                  @Query("page_size") int page_size,
                                                  @Query("platform") int platform);
}
