package com.jogger.beautifulapp.entity;

import java.util.List;

/**
 * Created by jogger on 2018/10/30.
 */
public class TagData {
    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "TagData{" +
                "tags=" + tags +
                '}';
    }
}
