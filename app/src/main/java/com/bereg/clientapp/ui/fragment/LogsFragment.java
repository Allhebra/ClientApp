package com.bereg.clientapp.ui.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bereg.clientapp.App;
import com.bereg.clientapp.R;
import com.bereg.clientapp.data.InMemoryCacheManager;
import com.bereg.clientapp.domain.ConnectionInteractor;
import com.bereg.clientapp.models.MessageModel;
import com.bereg.clientapp.ui.adapters.LogsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by 1 on 19.05.2018.
 */

public class LogsFragment extends Fragment {

    private static final String TAG = LogsFragment.class.getSimpleName();
    public LogsRecyclerAdapter mLogsRecyclerAdapter;
    public List<MessageModel> messages = new ArrayList<>();
    public ConnectionInteractor mConnectionInteractor;
    public InMemoryCacheManager mInMemoryCacheManager;

    public LogsFragment() {
        mConnectionInteractor = App.getAppComponent().getConnectionInteractor();
        mInMemoryCacheManager = App.getAppComponent().getInMemoryCacheManager();
        Log.e(TAG, "Constructor" +  mConnectionInteractor + mInMemoryCacheManager);
    }

    public static LogsFragment getInstance() {
        Log.e(TAG, "getInstance");
        LogsFragment fragment = new LogsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logs, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView mLogsRecyclerView = view.findViewById(R.id.rv_frag_logs_list);
        messages = mInMemoryCacheManager.getAllMessagesFromMemoryCache();
        mLogsRecyclerAdapter = new LogsRecyclerAdapter(messages);
        mLogsRecyclerView.setAdapter(mLogsRecyclerAdapter);
        mLogsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
