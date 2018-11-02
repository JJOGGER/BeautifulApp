package com.jogger.beautifulapp.http;

import com.jogger.beautifulapp.entity.AppCategoryData;
import com.jogger.beautifulapp.entity.AppCategoryMoreData;
import com.jogger.beautifulapp.entity.AppCollectData;
import com.jogger.beautifulapp.entity.AppCompilationDescData;
import com.jogger.beautifulapp.entity.AppCompilationsData;
import com.jogger.beautifulapp.entity.AppInfo;
import com.jogger.beautifulapp.entity.AppInfoData;
import com.jogger.beautifulapp.entity.AppMediaArticleData;
import com.jogger.beautifulapp.entity.AppNiceFriendData;
import com.jogger.beautifulapp.entity.AppRecentData;
import com.jogger.beautifulapp.entity.AppSearchData;
import com.jogger.beautifulapp.entity.AppSocialArticleData;
import com.jogger.beautifulapp.entity.FindChoiceData;
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.entity.TagData;
import com.jogger.beautifulapp.entity.UserHomeInfo;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


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


    //用户主页详细
    @GET("user/{id}/statistic")
    Observable<HttpResult<UserHomeInfo>> getUserHomeInfo(@Path("id") int id,
                                                         @Query("platform") int platform);

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

    /**
     * 排名
     */
    @GET("v2/apps/rank/")
    Observable<HttpResult<AppSocialArticleData>> getRankDatas(@Query("page") int page,
                                                              @Query("page_size") int page_size,
                                                              @Query("platform") int platform);

    /**
     * 精选详情
     */
    @GET("apps/app/{id}/")
    Observable<HttpResult<AppInfo>> getChoiceDescData(@Path("id") int id);

    /**
     * 最近详情
     */
    @GET("community/app/{id}/")
    Observable<HttpResult<RecentAppData>> getRecentDescData(@Path("id") int id);

    @GET
    Observable<ResponseBody> download(@Url String url);

    /**
     * 合辑详情
     */
    @GET("v2/albums/{id}/")
    Observable<HttpResult<AppCompilationDescData>> getCompilationDescDatas(@Path("id") int id,
                                                                           @Query("platform") int platform);


    //http://zuimeia.com/api/search/?openUDID=863339030079519&appVersion=3.3.2&appVersionCode=30320&systemVersion=26&resolution=1080x1792&platform=2&app_client=NiceAppAndroid&phoneModel=FRD-AL00

    /**
     * 搜索页标签列表
     */
    @GET("community/tags/")
    Observable<HttpResult<TagData>> getSearchTags(@Query("type") String type,
                                                  @Query("platform") int platform);

    /**
     * 搜索结果
     */
    @FormUrlEncoded
    @POST("search/")
    @Headers("Content-Type: application/x-www-form-urlencoded; charset=UTF-8")
    Observable<HttpResult<AppSearchData>> getSearchs(@Field("keyword") String keyword,
                                                     @Field("platform") int platform);


    @GET("navigation/{id}/app/all/")
    Observable<HttpResult<AppCategoryMoreData>> getCategoryMoreDatas(@Path("id") int id,
                                                                     @Query("page") int page,
                                                                     @Query("page_size") int page_size,
                                                                     @Query("platform") int platform);

    @GET("v2/apps/random/")
    Observable<HttpResult<AppRecentData>> getRandomDatas(@Query("platform") int platform);
//最近tag：http://zuimeia.com/api/category/24/all/?type=zuimei.community&platform=2&page=1&page_size=20&platform=2

    @GET("category/{id}/all/")
    Observable<HttpResult<AppRecentData>> getTagsMoreData(@Path("id") int id,
                                                          @Query("page") int page,
                                                          @Query("page_size") int page_size,
                                                          @Query("platform") int platform,
                                                          @Query("type") String type);
}
