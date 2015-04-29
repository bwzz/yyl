package com.yuantiku.yyl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.interfaces.OnItemClickListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author wanghb
 * @date 15/1/19.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<Account> accounts;
    private OnItemClickListener onItemClickListener;

    public ContactAdapter(List<Account> contacts) {
        super();
        this.accounts = contacts;
    }

    public void updateData(List<Account> accounts) {
        this.accounts = accounts;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Account contact = getItem(i);
        viewHolder.data = contact;
        viewHolder.mTextView.setText(contact.getName());
        viewHolder.itemView.setOnClickListener(itemView -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClicked(itemView, contact);
            }
        });
    }

    @Override
    public int getItemCount() {
        return accounts == null ? 0 : accounts.size();
    }

    private Account getItem(int i) {
        if (accounts == null || accounts.size() < i) {
            return null;
        } else {
            return accounts.get(i);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Object data;
        @InjectView(android.R.id.text1)
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
