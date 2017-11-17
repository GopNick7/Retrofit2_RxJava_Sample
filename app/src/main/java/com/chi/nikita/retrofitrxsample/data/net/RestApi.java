package com.chi.nikita.retrofitrxsample.data.net;

import com.chi.nikita.retrofitrxsample.data.model.PlayerResponse;
import com.chi.nikita.retrofitrxsample.data.model.StreamerResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestApi {

    @GET("bins/2go22")
    Observable<StreamerResponse> getStreamer();

    @GET("bins/1dll5f")
    Observable<PlayerResponse> getPlayers();
}
