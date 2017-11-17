package com.chi.nikita.retrofitrxsample.activity;

import com.chi.nikita.retrofitrxsample.Presenter;
import com.chi.nikita.retrofitrxsample.View;

import io.reactivex.Observable;

public interface MainPresenter<T extends View> extends Presenter<T> {

    void onAllDataLoaded();

    Observable<Integer> getPlayerModelObservable();

    Observable<String> getStreamerModelObservable();
}
