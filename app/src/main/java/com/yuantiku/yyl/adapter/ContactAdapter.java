package com.yuantiku.yyl.adapter;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.R;
import com.yuantiku.yyl.interfaces.OnItemClickListener;

import java.util.List;
import java.util.Random;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact,
                viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Account contact = getItem(i);
        viewHolder.data = contact;
        viewHolder.name.setText(contact.getName());
        String label = String.valueOf(contact.getName().charAt(contact.getName().length() - 1));
        viewHolder.icon.setText(label);
        ShapeDrawable shape = new ShapeDrawable(new OvalShape());
        Random random = new Random();
        shape.getPaint().setColor(Color.argb(128, random.nextInt(255), random.nextInt(255),
                random.nextInt(255)));
        viewHolder.icon.setBackgroundDrawable(shape);
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
        @InjectView(R.id.name)
        public TextView name;

        @InjectView(R.id.icon)
        public TextView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
