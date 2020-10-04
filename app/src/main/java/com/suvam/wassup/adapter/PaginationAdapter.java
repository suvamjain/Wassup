package com.suvam.wassup.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static final int LOADING = 0;
    static final int ITEM = 1;
    boolean isLoadingAdded = false;

    abstract void addLoadingFooter();

    abstract void removeLoadingFooter();

    abstract void add(Object obj);

    abstract void addAll(List<Object> objResults);

    abstract Object getItem(int position);

}