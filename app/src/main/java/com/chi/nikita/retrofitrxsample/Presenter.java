package com.chi.nikita.retrofitrxsample;


public interface Presenter<T extends View>{

    void bindView(T view);

    void unbindView();

    void onDestroy();
}
