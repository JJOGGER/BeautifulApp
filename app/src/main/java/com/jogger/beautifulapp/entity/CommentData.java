package com.jogger.beautifulapp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jogger on 2018/6/9.
 */

public class CommentData implements Serializable{
    private int has_next;
    private List<?> data;

    public int getHas_next() {
        return has_next;
    }

    public void setHas_next(int has_next) {
        this.has_next = has_next;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
