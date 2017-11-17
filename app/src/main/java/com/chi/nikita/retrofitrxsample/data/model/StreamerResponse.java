package com.chi.nikita.retrofitrxsample.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StreamerResponse {

    @SerializedName("time")
    @Expose
    private Double time;
    @SerializedName("streamers")
    @Expose
    private List<StreamerModel> streamerModelList = null;

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public List<StreamerModel> getStreamerModelList() {
        return streamerModelList;
    }

    public void setStreamerModelList(List<StreamerModel> streamerModelList) {
        this.streamerModelList = streamerModelList;
    }
}