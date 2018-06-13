package com.jogger.beautifulapp.http;

import com.jogger.beautifulapp.entity.AppInfoData;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.entity.FindChoiceData;
import com.jogger.beautifulapp.entity.MediaArticle;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RequestService {
    String BASE_URL = "http://zuimeia.com/api/";

    Observable<Response<ResponseBody>> test(@Field("userPage.cellphone") String account);

    //    Observable<HttpResult<AppInfoData>> getFindChoiceDatas(@Field("platform") int platform1,
//                                                  @Field("openUDID") String openUDID,
//                                                  @Field("appVersion") String appVersion,
//                                                  @Field("appVersionCode") int appVersionCode,
//                                                  @Field("systemVersion") int systemVersion,
//                                                  @Field("resolution") String resolution,
//                                                  @Field("platform") int platform2,
//                                                  @Field("app_client") int app_client,
//                                                  @Field("phoneModel") int phoneModel);
    //每日
    @GET("apps/app/daily")
    Observable<HttpResult<AppInfoData>> getDialyDatas(@Query("page") int page,
                                                      @Query("page_size") int page_size,
                                                      @Query("platform") int platform);

    //发现精选
    @GET("v3/apps")
    Observable<HttpResult<FindChoiceData>> getFindChoiceDatas(@Query("start_date") String
                                                                      start_date,
                                                              @Query("platform") int platform);

    //发现最新pos为-1时显示第一页
    @GET("community/apps")
    Observable<HttpResult<AppRecentData>> getFindRecentDatas(@Query("pos") int page,
                                                             @Query("page_size") int page_size,
                                                             @Query("platform") int platform);

    //发现周边头部
    @GET("v3/mediaarticles/top")
    Observable<HttpResult<MediaArticle>> getFindRoundTopDatas(@Query("platform") int platform);

    //发现周边
    @GET("v3/mediaarticles/all")
    Observable<HttpResult<MediaArticle>> getFindRoundDatas(@Query("pos") int page,
                                                           @Query("page_size") int page_size,
                                                           @Query("platform") int platform);
}
