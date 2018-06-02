package com.bereg.clientapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bereg.clientapp.R;
import com.bereg.clientapp.models.MessageModel;

import java.util.List;

/**
 * Created by 1 on 19.05.2018.
 */

public class LogsRecyclerAdapter extends RecyclerView.Adapter<LogsRecyclerAdapter.ViewHolder>{

    private static final String TAG = LogsRecyclerAdapter.class.getSimpleName();
    private List<MessageModel> items;

    public LogsRecyclerAdapter(List<MessageModel> messages) {

        items = messages;
        Log.e(TAG, "constructor:   ");
    }

    @Override
    public LogsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.logs_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.e(TAG, "onCreateViewHolder:  " + viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LogsRecyclerAdapter.ViewHolder holder, int position) {

        holder.sender.setText(items.get(position).getSender());
        holder.message.setText(items.get(position).getMessage());
        holder.timestamp.setText(items.get(position).getTimestampAsString());
        Log.e(TAG, "onBindViewHolder:  " + holder + position);
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "item count:   " + items.size());
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView sender;
        public TextView message;
        public TextView timestamp;

        ViewHolder(View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.tv_sender);
            message = itemView.findViewById(R.id.tv_message);
            timestamp = itemView.findViewById(R.id.tv_timestamp);
        }
    }
}
