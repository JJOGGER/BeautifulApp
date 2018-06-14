package com.jogger.beautifulapp.entity;

import java.util.List;

/**
 * Created by Jogger on 2018/6/15美友
 */

public class AppNiceFriendData {
    private List<UsersRank> users_rank;

    public List<UsersRank> getUsers_rank() {
        return users_rank;
    }

    public void setUsers_rank(List<UsersRank> users_rank) {
        this.users_rank = users_rank;
    }

    @Override
    public String toString() {
        return "AppNiceFriendData{" +
                "users_rank=" + users_rank +
                '}';
    }
}
