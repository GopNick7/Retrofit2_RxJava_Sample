package com.chi.nikita.retrofitrxsample.activity;

import android.support.annotation.NonNull;

import com.chi.nikita.retrofitrxsample.data.model.PlayerModel;
import com.chi.nikita.retrofitrxsample.data.model.PlayerResponse;
import com.chi.nikita.retrofitrxsample.data.model.StreamerModel;
import com.chi.nikita.retrofitrxsample.data.model.StreamerResponse;
import com.chi.nikita.retrofitrxsample.data.net.RetrofitManager;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImpl implements MainPresenter<MainView> {

    private MainView view;
    Executor executor = Executors.newFixedThreadPool(3);

    @Override
    public void bindView(final @NonNull MainView view) {
        this.view = view;
    }

    @Override
    public void unbindView() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onAllDataLoaded() {
        final StringBuilder sb = new StringBuilder();
        Observable.zip(getStreamerModelObservable(), getPlayerModelObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return integer + " " + s;
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.from(executor))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        sb.append(s).append("\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        view.showData(sb);
                    }
                });
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String r) {
//                        sb.append(r).append("\n");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        view.showData(sb);
//                    }
//                });
    }

    @Override
    public Observable<Integer> getPlayerModelObservable() {
        final Observable<PlayerResponse> playerResponseObservable = RetrofitManager.getInstance().getRestApi().getPlayers();
        return playerResponseObservable
                .flatMapIterable(new Function<PlayerResponse, List<PlayerModel>>() {
                    @Override
                    public List<PlayerModel> apply(PlayerResponse playerResponse) throws Exception {
                        return playerResponse.getPlayerModelList();
                    }
                })
                .map(new Function<PlayerModel, Integer>() {
                    @Override
                    public Integer apply(PlayerModel playerModel) throws Exception {
                        return playerModel.getId();
                    }
                });
    }

    @Override
    public Observable<String> getStreamerModelObservable() {
        final Observable<StreamerResponse> streamerResponseObservable = RetrofitManager.getInstance().getRestApi().getStreamer();
        return streamerResponseObservable
                .flatMapIterable(new Function<StreamerResponse, List<StreamerModel>>() {
                    @Override
                    public List<StreamerModel> apply(StreamerResponse streamerResponse) throws Exception {
                        return streamerResponse.getStreamerModelList();
                    }
                })
                .map(new Function<StreamerModel, String>() {
                    @Override
                    public String apply(StreamerModel streamerModel) throws Exception {
                        return streamerModel.getName();
                    }
                });
    }
}
