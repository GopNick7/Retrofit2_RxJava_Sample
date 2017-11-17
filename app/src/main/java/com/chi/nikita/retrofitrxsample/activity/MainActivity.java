package com.chi.nikita.retrofitrxsample.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.chi.nikita.retrofitrxsample.R;

public class MainActivity extends AppCompatActivity implements MainView {

    private TextView tvName;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        presenter = new MainPresenterImpl();
        presenter.bindView(this);
        presenter.onAllDataLoaded();

    }

    private void init() {
        tvName = findViewById(R.id.tvName);
    }

    @Override
    public void showData(final @NonNull StringBuilder string) {
        tvName.setText(string);
    }
}
