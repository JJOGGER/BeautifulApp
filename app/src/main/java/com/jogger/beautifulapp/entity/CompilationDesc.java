package com.jogger.beautifulapp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jogger on 2018/10/29.
 */
public class CompilationDesc implements Serializable{
    private int article_id;
    private int article_type;
    private String detail_url;
    private String digest;
    private String icon_image;
    private int id;
    private List<Link> links;
    private String package_name;
    private int share_times;
    private String size;
    private String title;
    private int up_times;

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getArticle_type() {
        return article_type;
    }

    public void setArticle_type(int article_type) {
        this.article_type = article_type;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getShare_times() {
        return share_times;
    }

    public void setShare_times(int share_times) {
        this.share_times = share_times;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUp_times() {
        return up_times;
    }

    public void setUp_times(int up_times) {
        this.up_times = up_times;
    }

    @Override
    public String toString() {
        return "CompilationDesc{" +
                "article_id=" + article_id +
                ", article_type=" + article_type +
                ", detail_url='" + detail_url + '\'' +
                ", digest='" + digest + '\'' +
                ", icon_image='" + icon_image + '\'' +
                ", id=" + id +
                ", links=" + links +
                ", package_name='" + package_name + '\'' +
                ", share_times='" + share_times + '\'' +
                ", size='" + size + '\'' +
                ", title='" + title + '\'' +
                ", up_times='" + up_times + '\'' +
                '}';
    }

    public class Link implements Serializable{
        public static final String TYPE_DIRECT = "direct";
        public static final String TYPE_WANDOUJIA = "wandoujia";
        public static final String TYPE_GOOGLEPALY = "googleplay";
        private String type;
        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Link{" +
                    "type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
