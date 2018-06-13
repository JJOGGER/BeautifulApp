package com.jogger.beautifulapp.entity;


import java.io.Serializable;
import java.util.List;

public class FindChoiceData implements Serializable {
    private String next_date;//用于请求分页
    private List<Content> content;

    public String getNext_date() {
        return next_date;
    }

    public void setNext_date(String next_date) {
        this.next_date = next_date;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FindChoiceData{" +
                "next_date='" + next_date + '\'' +
                ", content=" + content +
                '}';
    }

    public class Content implements Serializable {
        private List<MediaArticle> apps;
        private String label_date;

        public List<MediaArticle> getApps() {
            return apps;
        }

        public void setApps(List<MediaArticle> apps) {
            this.apps = apps;
        }

        public String getLabel_date() {
            return label_date;
        }

        public void setLabel_date(String label_date) {
            this.label_date = label_date;
        }

        @Override
        public String toString() {
            return "Content{" +
                    "apps=" + apps +
                    ", label_date='" + label_date + '\'' +
                    '}';
        }
    }
}
