package com.jogger.beautifulapp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jogger on 2018/6/9.应用信息
 */

public class AppInfo implements Serializable {
    private String thankto;
    private String cover_comment_author_avatar_url;
    private String video_url;
    private String cover_comment_content;
    private List<SameApp> same_apps;
    private List<User> up_users;
    private String author_gender;
    private String qrcode_image;
    private String create_time;
    private CommentData comments;
    private int id;
    private String cover_comment_article;
    private String digest;
    private String size;
    private String cover_comment_id;
    private String title;
    private String cover_comment_author_bgcolor;
    private int min_sdk_version;
    private String sub_title;
    private String download_url;
    private String icon_image;
    private String content;
    private String version;
    private String recommend_level;
    private String cover_comment_author_gender;
    private String cover_comment_is_on_cover;
    private String update_time;
    private String author_username;
    private String cover_comment_author_name;
    private String cover_comment_author_career;
    private String cover_comment_updated_at;
    private int price;
    private boolean video_is_portrait;
    private String recommanded_date;
    private String cover_comment_author_id;
    private String recommanded_background_color;
    private String price_format;
    private String author_avatar_url;
    private String tags;
    private Info info;
    private String author_bgcolor;
    private List<DownloadMethod> other_download_url;
    private String cover_comment_created_at;
    private String publish_date;
    private String author_career;
    private String cover_image;
    private String video_share_url;
    private int author_id;
    private String package_name;

    public String getThankto() {
        return thankto;
    }

    public void setThankto(String thankto) {
        this.thankto = thankto;
    }

    public String getCover_comment_author_avatar_url() {
        return cover_comment_author_avatar_url;
    }

    public void setCover_comment_author_avatar_url(String cover_comment_author_avatar_url) {
        this.cover_comment_author_avatar_url = cover_comment_author_avatar_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public List<SameApp> getSame_apps() {
        return same_apps;
    }

    public void setSame_apps(List<SameApp> same_apps) {
        this.same_apps = same_apps;
    }

    public List<User> getUp_users() {
        return up_users;
    }

    public void setUp_users(List<User> up_users) {
        this.up_users = up_users;
    }

    public String getCover_comment_content() {
        return cover_comment_content;
    }

    public void setCover_comment_content(String cover_comment_content) {
        this.cover_comment_content = cover_comment_content;
    }

    public String getAuthor_gender() {
        return author_gender;
    }

    public void setAuthor_gender(String author_gender) {
        this.author_gender = author_gender;
    }

    public String getQrcode_image() {
        return qrcode_image;
    }

    public void setQrcode_image(String qrcode_image) {
        this.qrcode_image = qrcode_image;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public CommentData getComments() {
        return comments;
    }

    public void setComments(CommentData comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover_comment_article() {
        return cover_comment_article;
    }

    public void setCover_comment_article(String cover_comment_article) {
        this.cover_comment_article = cover_comment_article;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCover_comment_id() {
        return cover_comment_id;
    }

    public void setCover_comment_id(String cover_comment_id) {
        this.cover_comment_id = cover_comment_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_comment_author_bgcolor() {
        return cover_comment_author_bgcolor;
    }

    public void setCover_comment_author_bgcolor(String cover_comment_author_bgcolor) {
        this.cover_comment_author_bgcolor = cover_comment_author_bgcolor;
    }

    public int getMin_sdk_version() {
        return min_sdk_version;
    }

    public void setMin_sdk_version(int min_sdk_version) {
        this.min_sdk_version = min_sdk_version;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRecommend_level() {
        return recommend_level;
    }

    public void setRecommend_level(String recommend_level) {
        this.recommend_level = recommend_level;
    }

    public String getCover_comment_author_gender() {
        return cover_comment_author_gender;
    }

    public void setCover_comment_author_gender(String cover_comment_author_gender) {
        this.cover_comment_author_gender = cover_comment_author_gender;
    }

    public String getCover_comment_is_on_cover() {
        return cover_comment_is_on_cover;
    }

    public void setCover_comment_is_on_cover(String cover_comment_is_on_cover) {
        this.cover_comment_is_on_cover = cover_comment_is_on_cover;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getAuthor_username() {
        return author_username;
    }

    public void setAuthor_username(String author_username) {
        this.author_username = author_username;
    }

    public String getCover_comment_author_name() {
        return cover_comment_author_name;
    }

    public void setCover_comment_author_name(String cover_comment_author_name) {
        this.cover_comment_author_name = cover_comment_author_name;
    }

    public String getCover_comment_author_career() {
        return cover_comment_author_career;
    }

    public void setCover_comment_author_career(String cover_comment_author_career) {
        this.cover_comment_author_career = cover_comment_author_career;
    }

    public String getCover_comment_updated_at() {
        return cover_comment_updated_at;
    }

    public void setCover_comment_updated_at(String cover_comment_updated_at) {
        this.cover_comment_updated_at = cover_comment_updated_at;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isVideo_is_portrait() {
        return video_is_portrait;
    }

    public void setVideo_is_portrait(boolean video_is_portrait) {
        this.video_is_portrait = video_is_portrait;
    }

    public String getRecommanded_date() {
        return recommanded_date;
    }

    public void setRecommanded_date(String recommanded_date) {
        this.recommanded_date = recommanded_date;
    }

    public String getCover_comment_author_id() {
        return cover_comment_author_id;
    }

    public void setCover_comment_author_id(String cover_comment_author_id) {
        this.cover_comment_author_id = cover_comment_author_id;
    }

    public String getRecommanded_background_color() {
        return recommanded_background_color;
    }

    public void setRecommanded_background_color(String recommanded_background_color) {
        this.recommanded_background_color = recommanded_background_color;
    }

    public String getPrice_format() {
        return price_format;
    }

    public void setPrice_format(String price_format) {
        this.price_format = price_format;
    }

    public String getAuthor_avatar_url() {
        return author_avatar_url;
    }

    public void setAuthor_avatar_url(String author_avatar_url) {
        this.author_avatar_url = author_avatar_url;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getAuthor_bgcolor() {
        return author_bgcolor;
    }

    public void setAuthor_bgcolor(String author_bgcolor) {
        this.author_bgcolor = author_bgcolor;
    }

    public List<DownloadMethod> getOther_download_url() {
        return other_download_url;
    }

    public void setOther_download_url(List<DownloadMethod> other_download_url) {
        this.other_download_url = other_download_url;
    }

    public String getCover_comment_created_at() {
        return cover_comment_created_at;
    }

    public void setCover_comment_created_at(String cover_comment_created_at) {
        this.cover_comment_created_at = cover_comment_created_at;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getAuthor_career() {
        return author_career;
    }

    public void setAuthor_career(String author_career) {
        this.author_career = author_career;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getVideo_share_url() {
        return video_share_url;
    }

    public void setVideo_share_url(String video_share_url) {
        this.video_share_url = video_share_url;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

   public class Info implements Serializable {
        private List<String> up_users;
        private List<String> fav_users;
        private String fav;
        private String up;
        private String down;
        private String av;
        private List<String> down_users;

        public List<String> getUp_users() {
            return up_users;
        }

        public void setUp_users(List<String> up_users) {
            this.up_users = up_users;
        }

        public List<String> getFav_users() {
            return fav_users;
        }

        public void setFav_users(List<String> fav_users) {
            this.fav_users = fav_users;
        }

        public String getFav() {
            return fav;
        }

        public void setFav(String fav) {
            this.fav = fav;
        }

        public String getUp() {
            return up;
        }

        public void setUp(String up) {
            this.up = up;
        }

        public String getDown() {
            return down;
        }

        public void setDown(String down) {
            this.down = down;
        }

        public String getAv() {
            return av;
        }

        public void setAv(String av) {
            this.av = av;
        }

        public List<String> getDown_users() {
            return down_users;
        }

        public void setDown_users(List<String> down_users) {
            this.down_users = down_users;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "up_users=" + up_users +
                    ", fav_users=" + fav_users +
                    ", fav='" + fav + '\'' +
                    ", up='" + up + '\'' +
                    ", down='" + down + '\'' +
                    ", av='" + av + '\'' +
                    ", down_users=" + down_users +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "thankto='" + thankto + '\'' +
                ", cover_comment_author_avatar_url='" + cover_comment_author_avatar_url + '\'' +
                ", video_url='" + video_url + '\'' +
                ", cover_comment_content='" + cover_comment_content + '\'' +
                ", author_gender='" + author_gender + '\'' +
                ", qrcode_image='" + qrcode_image + '\'' +
                ", create_time='" + create_time + '\'' +
                ", comments=" + comments +
                ", id=" + id +
                ", cover_comment_article='" + cover_comment_article + '\'' +
                ", digest='" + digest + '\'' +
                ", size='" + size + '\'' +
                ", cover_comment_id='" + cover_comment_id + '\'' +
                ", title='" + title + '\'' +
                ", cover_comment_author_bgcolor='" + cover_comment_author_bgcolor + '\'' +
                ", min_sdk_version=" + min_sdk_version +
                ", sub_title='" + sub_title + '\'' +
                ", download_url='" + download_url + '\'' +
                ", icon_image='" + icon_image + '\'' +
                ", version='" + version + '\'' +
                ", recommend_level='" + recommend_level + '\'' +
                ", cover_comment_author_gender='" + cover_comment_author_gender + '\'' +
                ", cover_comment_is_on_cover='" + cover_comment_is_on_cover + '\'' +
                ", update_time='" + update_time + '\'' +
                ", author_username='" + author_username + '\'' +
                ", cover_comment_author_name='" + cover_comment_author_name + '\'' +
                ", cover_comment_author_career='" + cover_comment_author_career + '\'' +
                ", cover_comment_updated_at='" + cover_comment_updated_at + '\'' +
                ", price=" + price +
                ", video_is_portrait=" + video_is_portrait +
                ", recommanded_date='" + recommanded_date + '\'' +
                ", cover_comment_author_id='" + cover_comment_author_id + '\'' +
                ", recommanded_background_color='" + recommanded_background_color + '\'' +
                ", price_format='" + price_format + '\'' +
                ", author_avatar_url='" + author_avatar_url + '\'' +
                ", tags='" + tags + '\'' +
                ", info=" + info +
                ", author_bgcolor='" + author_bgcolor + '\'' +
                ", other_download_url=" + other_download_url +
                ", cover_comment_created_at='" + cover_comment_created_at + '\'' +
                ", publish_date='" + publish_date + '\'' +
                ", author_career='" + author_career + '\'' +
                ", cover_image='" + cover_image + '\'' +
                ", video_share_url='" + video_share_url + '\'' +
                ", author_id=" + author_id +
                ", package_name='" + package_name + '\'' +
                '}';
    }
}
