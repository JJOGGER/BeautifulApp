package com.jogger.beautifulapp.entity;


import java.io.Serializable;
import java.util.List;

public class RecentAppData implements Serializable{
    private boolean is_beta_end;
    private String app_name;
    private String package_name;
    private List<SameApp> same_apps;
    private long pos;
    private String author_gender;
    private int author_identity;
    private int author_flowers;
    private List<DownloadUrl> download_urls;
    private int id;
    private String size;
    private int platform_id;
    private String title;
    private List<User> collect_users;
    private int min_sdk_version;
    private String sub_title;
    private List<String> comments;
    private String icon_image;
    private int show_times;
    private List<DownloadUrl> redirect_download_urls;
    private List<User> up_users;
    private String description;
    private List<Tag> tags;
    private String updated_at;
    private int collect_times;
    private String author_name;
    private int down_times;
    private String author_avatar_url;
    private boolean is_beta;
    private String author_bgcolor;
    private List<String> all_images;
    private int comment_times;
    private String created_at;
    private String platform_name;
    private int up_times;
    private boolean can_show;
    private String author_career;
    private String cover_image;
    private int author_id;
    private String type;
    private List<User> down_users;

    public boolean isIs_beta_end() {
        return is_beta_end;
    }

    public void setIs_beta_end(boolean is_beta_end) {
        this.is_beta_end = is_beta_end;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public long getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getAuthor_gender() {
        return author_gender;
    }

    public void setAuthor_gender(String author_gender) {
        this.author_gender = author_gender;
    }

    public int getAuthor_identity() {
        return author_identity;
    }

    public void setAuthor_identity(int author_identity) {
        this.author_identity = author_identity;
    }

    public int getAuthor_flowers() {
        return author_flowers;
    }

    public void setAuthor_flowers(int author_flowers) {
        this.author_flowers = author_flowers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public int getShow_times() {
        return show_times;
    }

    public void setShow_times(int show_times) {
        this.show_times = show_times;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getCollect_times() {
        return collect_times;
    }

    public void setCollect_times(int collect_times) {
        this.collect_times = collect_times;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getDown_times() {
        return down_times;
    }

    public void setDown_times(int down_times) {
        this.down_times = down_times;
    }

    public String getAuthor_avatar_url() {
        return author_avatar_url;
    }

    public void setAuthor_avatar_url(String author_avatar_url) {
        this.author_avatar_url = author_avatar_url;
    }

    public boolean isIs_beta() {
        return is_beta;
    }

    public void setIs_beta(boolean is_beta) {
        this.is_beta = is_beta;
    }

    public String getAuthor_bgcolor() {
        return author_bgcolor;
    }

    public void setAuthor_bgcolor(String author_bgcolor) {
        this.author_bgcolor = author_bgcolor;
    }

    public int getComment_times() {
        return comment_times;
    }

    public void setComment_times(int comment_times) {
        this.comment_times = comment_times;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public int getUp_times() {
        return up_times;
    }

    public void setUp_times(int up_times) {
        this.up_times = up_times;
    }

    public boolean isCan_show() {
        return can_show;
    }

    public void setCan_show(boolean can_show) {
        this.can_show = can_show;
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

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public List<SameApp> getSame_apps() {
        return same_apps;
    }

    public void setSame_apps(List<SameApp> same_apps) {
        this.same_apps = same_apps;
    }

    public List<DownloadUrl> getDownload_urls() {
        return download_urls;
    }

    public void setDownload_urls(List<DownloadUrl> download_urls) {
        this.download_urls = download_urls;
    }

    public List<User> getCollect_users() {
        return collect_users;
    }

    public void setCollect_users(List<User> collect_users) {
        this.collect_users = collect_users;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<DownloadUrl> getRedirect_download_urls() {
        return redirect_download_urls;
    }

    public void setRedirect_download_urls(List<DownloadUrl> redirect_download_urls) {
        this.redirect_download_urls = redirect_download_urls;
    }

    public List<User> getUp_users() {
        return up_users;
    }

    public void setUp_users(List<User> up_users) {
        this.up_users = up_users;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<String> getAll_images() {
        return all_images;
    }

    public void setAll_images(List<String> all_images) {
        this.all_images = all_images;
    }

    public List<User> getDown_users() {
        return down_users;
    }

    public void setDown_users(List<User> down_users) {
        this.down_users = down_users;
    }

    @Override
    public String toString() {
        return "RecentAppData{" +
                "is_beta_end=" + is_beta_end +
                ", app_name='" + app_name + '\'' +
                ", package_name='" + package_name + '\'' +
                ", pos=" + pos +
                ", author_gender='" + author_gender + '\'' +
                ", author_identity=" + author_identity +
                ", author_flowers=" + author_flowers +
                ", id=" + id +
                ", size='" + size + '\'' +
                ", platform_id=" + platform_id +
                ", title='" + title + '\'' +
                ", min_sdk_version=" + min_sdk_version +
                ", sub_title='" + sub_title + '\'' +
                ", icon_image='" + icon_image + '\'' +
                ", show_times=" + show_times +
                ", description='" + description + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", collect_times=" + collect_times +
                ", author_name='" + author_name + '\'' +
                ", down_times=" + down_times +
                ", author_avatar_url='" + author_avatar_url + '\'' +
                ", is_beta=" + is_beta +
                ", author_bgcolor='" + author_bgcolor + '\'' +
                ", comment_times=" + comment_times +
                ", created_at='" + created_at + '\'' +
                ", platform_name='" + platform_name + '\'' +
                ", up_times=" + up_times +
                ", can_show=" + can_show +
                ", author_career='" + author_career + '\'' +
                ", cover_image='" + cover_image + '\'' +
                ", author_id=" + author_id +
                ", same_apps=" + same_apps +
                ", download_urls=" + download_urls +
                ", collect_users=" + collect_users +
                ", comments=" + comments +
                ", redirect_download_urls=" + redirect_download_urls +
                ", up_users=" + up_users +
                ", tags=" + tags +
                ", all_images=" + all_images +
                ", down_users=" + down_users +
                '}';
    }
}
