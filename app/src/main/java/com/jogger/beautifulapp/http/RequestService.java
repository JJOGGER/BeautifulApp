package com.jogger.beautifulapp.http;

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

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
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
    Observable<HttpResult<AppRecentData>> getFindRecentDatas(@Query("pos") long page,
                                                             @Query("page_size") int page_size,
                                                             @Query("platform") int platform);

    //发现周边头部
    @GET("v3/mediaarticles/top")
    Observable<HttpResult<AppMediaArticleData>> getFindRoundTopDatas(@Query("platform") int
                                                                             platform);

    //发现周边
    @GET("v3/mediaarticles/all")
    Observable<HttpResult<AppMediaArticleData>> getFindRoundDatas(@Query("page") int page,
                                                                  @Query("page_size") int page_size,
                                                                  @Query("platform") int platform);

    //发现合辑
    @GET("v2/albums")
    Observable<HttpResult<AppCompilationsData>> getFindCompilationsDatas(@Query("page") int page,
                                                                         @Query("page_size") int
                                                                                 page_size,
                                                                         @Query("platform") int
                                                                                 platform);

    //发现美友
    @GET("community/rank/users/")
    Observable<HttpResult<AppNiceFriendData>> getFindNiceFriendDatas();

    //美友推荐
    @GET("user/{id}/community/apps")
    Observable<HttpResult<AppRecentData>> getUserRecommendDatas(@Path("id") int id,
                                                                @Query("page") int page,
                                                                @Query("page_size") int
                                                                        page_size,
                                                                @Query("platform") int
                                                                        platform);

    //美友收藏
    @GET("collect/{id}/all/")
    Observable<HttpResult<AppCollectData>> getUserCollectDatas(@Path("id") int id,
                                                               @Query("page") int page,
                                                               @Query("page_size") int
                                                                       page_size,
                                                               @Query("platform") int
                                                                       platform);


    //精选分类
    @GET("navigation/all/app/top/")
    Observable<HttpResult<AppCategoryData>> getCategoryDatas();

    @GET("category/12/all/?type=zuimei.daily&page=1&page_size=20&platform=2")
    Observable<HttpResult<AppInfoData>> getGameDatas(@Query("type") String type,
                                                     @Query("page") int page,
                                                     @Query("page_size") int page_size,
                                                     @Query("platform") int platform);

    @GET("api/v2/apps/rank/")
    Observable<HttpResult<AppSocialArticleData>> getRankDatas(@Query("page") int page,
                                                              @Query("page_size") int page_size,
                                                              @Query("platform") int platform);

    @GET("apps/app/{id}/")
    Observable<HttpResult<AppInfo>> getChoiceDescData(@Path("id") int id);
}
