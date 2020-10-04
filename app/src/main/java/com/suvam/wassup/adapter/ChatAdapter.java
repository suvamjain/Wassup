package com.suvam.wassup.adapter;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.suvam.wassup.ChatActivity;
import com.suvam.wassup.MainActivity;
import com.suvam.wassup.R;
import com.suvam.wassup.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends PaginationAdapter implements Filterable {

    private static final String TAG = "ChatAdapter";
    private ArrayList<Chat> mAllChats;
    private List<Chat> mFilteredList;
    private Context mContext;
    private String searchedText = "";

    public ChatAdapter(Context context) {
        this.mContext = context;
        this.mAllChats = new ArrayList<>();
        this.mFilteredList = new ArrayList<>();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView mName, mMsg, mTime, mNotif;
        ImageView mProfileImg;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.user_name);
            mMsg = itemView.findViewById(R.id.user_msg);
            mTime = itemView.findViewById(R.id.msg_time);
            mNotif = itemView.findViewById(R.id.msg_notif);
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
                View viewChat = inflater.inflate(R.layout.chat_item, parent, false);
                viewHolder = new ChatViewHolder(viewChat);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.progress_item, parent, false);
                viewHolder = new LoadingViewHolder(viewLoading);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final Chat chat = mFilteredList.get(position);

        switch (getItemViewType(position)) {

            case ITEM:
                ChatViewHolder chatViewHolder = (ChatViewHolder) holder;
                chatViewHolder.mName.setText("" + chat.getName());
                chatViewHolder.mMsg.setText("" + chat.getMsg());
                chatViewHolder.mTime.setText("" + chat.getMsgTime());

                chatViewHolder.mNotif.setVisibility(View.GONE);
                chatViewHolder.mTime.setTextColor(mContext.getResources().getColor(R.color.grey));

                chatViewHolder.mMsg.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_double_tick, 0, 0, 0);

                if(chat.getLastMsgType().equals(Chat.MsgType.RECIEVED)) {
                    chatViewHolder.mMsg.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    if(chat.getNewMsgCount() > 0) {
                        chatViewHolder.mNotif.setVisibility(View.VISIBLE);
                        chatViewHolder.mNotif.setText("" + chat.getNewMsgCount());
                        chatViewHolder.mTime.setTextColor(mContext.getResources().getColor(R.color.lightGreen));
                    }
                }

                chatViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent startChat = new Intent(mContext.getApplicationContext(), ChatActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("chat", chat);
                        startChat.putExtras(b);
                        mFilteredList.get(position).setNewMsgCount(0);
                        mContext.startActivity(startChat);
                        notifyItemChanged(position);
                    }
                });

                ((MainActivity)mContext).highlightString(searchedText, chatViewHolder.mName);
                ((MainActivity)mContext).highlightString(searchedText, chatViewHolder.mMsg);
                break;

            case LOADING:
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                loadingViewHolder.textView.setText("Loading Chats");
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
                    mFilteredList = mAllChats;
                } else {
                    List<Chat> filteredList = new ArrayList<>();
                    for (Chat row : mAllChats) {
                        if(row.getName() != null)
                            if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getMsg().toLowerCase().contains(charString.toLowerCase())) {
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
                mFilteredList = (ArrayList<Chat>) filterResults.values;

                if (mFilteredList.size() == 0 && mAllChats.size() > 0)
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
        add(new Chat());
    }

    @Override
    public void removeLoadingFooter() {
        isLoadingAdded = false;
        int position = mAllChats.size() - 1;
        Chat result = getItem(position);

        if (result != null) {
            mAllChats.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public void add(Object chat) {
        mAllChats.add((Chat) chat);
        notifyItemInserted(mAllChats.size() - 1);
    }

    @Override
    public void addAll(List<Object> chatResults) {
        for (Object result : chatResults) {
            add(result);
        }

        // perform filtering again
        getFilter().filter(searchedText);
    }

    @Override
    public Chat getItem(int position) {
        return mAllChats.get(position);
    }

}
