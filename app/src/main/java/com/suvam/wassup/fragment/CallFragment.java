package com.suvam.wassup.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.suvam.wassup.MainActivity;
import com.suvam.wassup.utils.QuerySearchResults;
import com.suvam.wassup.R;
import com.suvam.wassup.SampleData;
import com.suvam.wassup.adapter.CallAdapter;
import com.suvam.wassup.utils.PaginationScrollListener;

import java.util.List;
import java.util.Objects;

public class CallFragment extends Fragment implements QuerySearchResults {

    private RecyclerView mRecyclerView;
    private CallAdapter mAdapter;
    private SampleData sampleData;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        ((MainActivity) Objects.requireNonNull(getActivity())).setActivityListener(2, CallFragment.this);
        sampleData = SampleData.getInstance();

        mRecyclerView = view.findViewById(R.id.chats_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new CallAdapter(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();
        return view;
    }

    @Override
    public void doSearchInFragment(String searchKey) {
        if(mAdapter != null)
            mAdapter.getFilter().filter(searchKey);
    }

    private void loadNextPage() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mAdapter.removeLoadingFooter();
                isLoading = false;

                List<Object> results = sampleData.getCallsData(currentPage);
                mAdapter.addAll(results);

                if (currentPage != TOTAL_PAGES) mAdapter.addLoadingFooter();
                else isLastPage = true;

            }

        }, 3000);
    }


    private void loadFirstPage() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                List<Object> results = sampleData.getCallsData(PAGE_START);
                mAdapter.addAll(results);

                if (currentPage <= TOTAL_PAGES) mAdapter.addLoadingFooter();
                else isLastPage = true;

            }
        }, 1500);
    }
}
