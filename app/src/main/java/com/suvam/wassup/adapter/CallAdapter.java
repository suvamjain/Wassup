package com.suvam.wassup.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.suvam.wassup.MainActivity;
import com.suvam.wassup.R;
import com.suvam.wassup.model.Call;

import java.util.ArrayList;
import java.util.List;

public class CallAdapter extends PaginationAdapter implements Filterable {

    private static final String TAG = "CallAdapter";
    private ArrayList<Call> mAllCalls;
    private List<Call> mFilteredList;
    private Context mContext;
    private String searchedText = "";

    public CallAdapter(Context context) {
        this.mContext = context;
        this.mAllCalls = new ArrayList<>();
        this.mFilteredList = new ArrayList<>();
    }

    public class CallViewHolder extends RecyclerView.ViewHolder {
        TextView mName, mTime;
        ImageView mProfileImg;

        public CallViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.user_name);
            mTime = itemView.findViewById(R.id.call_time);
            mProfileImg = itemView.findViewById(R.id.profile_image);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;
        private TextView textView;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.loadmore_progress);
            textView = itemView.findViewById(R.id.loading_text);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        switch (viewType) {
            case ITEM:
                View viewChat = inflater.inflate(R.layout.call_item, parent, false);
                viewHolder = new CallViewHolder(viewChat);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.progress_item, parent, false);
                viewHolder = new LoadingViewHolder(viewLoading);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Call call = mFilteredList.get(position);

        switch (getItemViewType(position)) {

            case ITEM:
                CallViewHolder callViewHolder = (CallViewHolder) holder;
                callViewHolder.mName.setText("" + call.getName());

                if (call.getCallCount() > 1)
                    callViewHolder.mTime.setText(String.format("(%d) %s", call.getCallCount(), call.getCallTime()));
                else
                    callViewHolder.mTime.setText(call.getCallTime());

                callViewHolder.mTime.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_incoming, 0, 0, 0);

                if (call.getCallType().equals(Call.CallType.OUTGOING)) {
                    callViewHolder.mTime.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_outgoing, 0, 0, 0);
                } else if (call.getCallType().equals(Call.CallType.MISSED)) {
                    DrawableCompat.setTint(
                            DrawableCompat.wrap(callViewHolder.mTime.getCompoundDrawables()[0]),
                            ContextCompat.getColor(mContext, R.color.red)
                    );
                }

                ((MainActivity) mContext).highlightString(searchedText, callViewHolder.mName);
                break;

            case LOADING:
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                loadingViewHolder.textView.setText("Loading Calls");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mFilteredList == null ? 0 : mFilteredList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mFilteredList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().trim();
                searchedText = charString.toLowerCase().trim();
                if (charString.isEmpty()) {
                    mFilteredList = mAllCalls;
                } else {
                    List<Call> filteredList = new ArrayList<>();
                    for (Call row : mAllCalls) {
                        if(row.getName() != null)
                            if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Call>) filterResults.values;

                if (mFilteredList.size() == 0 && mAllCalls.size() > 0)
                    ((MainActivity)mContext).showNoSearchResults(View.VISIBLE);
                else
                    ((MainActivity)mContext).showNoSearchResults(View.GONE);

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Call());
    }

    @Override
    public void removeLoadingFooter() {
        isLoadingAdded = false;
        int position = mAllCalls.size() - 1;
        Call result = getItem(position);

        if (result != null) {
            mAllCalls.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public void add(Object call) {
        mAllCalls.add((Call) call);
        notifyItemInserted(mAllCalls.size() - 1);
    }

    @Override
    public void addAll(List<Object> callResults) {
        for (Object result : callResults) {
            add(result);
        }

        // perform filtering again
        getFilter().filter(searchedText);
    }

    @Override
    public Call getItem(int position) {
        return mAllCalls.get(position);
    }
}
