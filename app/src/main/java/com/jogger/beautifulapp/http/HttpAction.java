package com.jogger.beautifulapp.http;

import com.jogger.beautifulapp.constant.Constant;
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
import com.jogger.beautifulapp.entity.RecentAppData;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;

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
    public void getDialyDatas(int page, int page_size, OnHttpRequestListener<AppInfoData>
            listener) {
        mHttpRequest.getDialyDatas(page, page_size, 2, listener);
    }

    /**
     * 发现精选
     */
    public void getFindChoiceDatas(String startDate, OnHttpRequestListener<FindChoiceData>
            listener) {
        mHttpRequest.getFindChoiceDatas(startDate, 2, listener);
    }

    /**
     * 发现最近
     */
    public void getFindRecentDatas(long page, int page_size,
                                   OnHttpRequestListener<AppRecentData> listener) {
        mHttpRequest.getFindRecentDatas(page, page_size, 2, listener);
    }

    /**
     * 发现周边头部
     */
    public void getFindRoundTopDatas(OnHttpRequestListener<AppMediaArticleData> listener) {
        mHttpRequest.getFindRoundTopDatas(2, listener);
    }

    /**
     * 发现周边
     */
    public void getFindRoundDatas(int page, int page_size,
                                  OnHttpRequestListener<AppMediaArticleData> listener) {
        mHttpRequest.getFindRoundDatas(page, page_size, 2, listener);
    }

    public void getFindCompilationsDatas(int page, int page_size,
                                         OnHttpRequestListener<AppCompilationsData> listener) {
        mHttpRequest.getFindCompilationsDatas(page, page_size, 2, listener);
    }

    public void getFindNiceFriendDatas(OnHttpRequestListener<AppNiceFriendData> listener) {
        mHttpRequest.getFindNiceFriendDatas(listener);
    }

    /**
     * 用户主页信息
     */
    public void getUserHomeInfo(int id, OnHttpRequestListener listener) {
        mHttpRequest.getUserHomeInfo(id, 2, listener);
    }

    /**
     * 美友推荐
     */
    public void getUserRecommendDatas(int userId, int page, int page_size,
                                      OnHttpRequestListener<AppRecentData> listener) {
        mHttpRequest.getUserRecommendDatas(userId, page, page_size, 2, listener);
    }

    /**
     * 美友收藏
     */
    public void getUserCollectDatas(int userId, int page, int page_size,
                                    OnHttpRequestListener<AppCollectData> listener) {
        mHttpRequest.getUserCollectDatas(userId, page, page_size, 2, listener);
    }

    /**
     * 精选分类
     */
    public void getCategoryDatas(OnHttpRequestListener<AppCategoryData> listener) {
        mHttpRequest.getCategoryDatas(listener);
    }

    /**
     * 游戏
     */
    public void getGameDatas(int page, int page_size, OnHttpRequestListener<AppInfoData> listener) {
        mHttpRequest.getGameDatas("zuimei.daily", page, page_size, 2, listener);
    }

    /**
     * 应用排行
     */
    public void getRankDatas(int page, int page_size, OnHttpRequestListener<AppSocialArticleData>
            listener) {
        mHttpRequest.getRankDatas(page, page_size, 2, listener);
    }

    /**
     * 精选详情
     */
    public void getChoiceDescData(int id, OnHttpRequestListener<AppInfo> listener) {
        mHttpRequest.getChoiceDescData(id, listener);
    }

    /**
     * 最近详情
     */
    public void getRecentDescData(int id, OnHttpRequestListener<RecentAppData> listener) {
        mHttpRequest.getRecentDescData(id, listener);
    }

    /**
     * 合辑详情
     */
    public void getCompilationDescDatas(int id, OnHttpRequestListener listener) {
        mHttpRequest.getCompilationDescDatas(id, 2, listener);
    }

    /**
     * 搜索页面标签列表
     */
    public void getSearchTags(OnHttpRequestListener listener) {
        mHttpRequest.getSearchTags("zuimei.tag.home", 2, listener);
    }

    /**
     * 搜索列表
     *
     * @param keyword 关键词
     */
    public void getSearchs(String keyword, OnHttpRequestListener listener) {
        mHttpRequest.getSearchs(keyword, 2, listener);
    }

    /**
     * 精选页更多
     */
    public void getCategoryMoreDatas(int id, int page, int page_size, OnHttpRequestListener listener) {
        mHttpRequest.getCategoryMoreDatas(id, page, page_size, 2, listener);
    }

    /**
     * 主页随机数据
     */
    public void getRandomDatas(OnHttpRequestListener listener) {
        mHttpRequest.getRandomDatas(2, listener);
    }

    /**
     * tag标签点击详情
     */
    public void getTagsMoreData(int id, int page, int pageSize, OnHttpRequestListener<AppRecentData> listener) {
        mHttpRequest.getTagsMoreData(id, page, pageSize, 2, Constant.TYPE_COMMUNITY,listener);
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
