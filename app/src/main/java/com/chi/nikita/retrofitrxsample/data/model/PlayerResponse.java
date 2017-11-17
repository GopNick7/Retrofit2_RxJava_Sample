package com.chi.nikita.retrofitrxsample.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerResponse {

    @SerializedName("player")
    @Expose
    private List<PlayerModel> playerModelList = null;

    public List<PlayerModel> getPlayerModelList() {
        return playerModelList;
    }

    public void setPlayerModelList(List<PlayerModel> playerModelList) {
        this.playerModelList = playerModelList;
    }

}