package com.yuantiku.yyl.pages;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.R;
import com.yuantiku.yyl.adapter.ContactAdapter;
import com.yuantiku.yyl.helper.LoginHelper;
import com.yuantiku.yyl.interfaces.OnItemClickListener;

import java.util.List;

import butterknife.InjectView;

/**
 * @author wanghb
 * @date 15/4/29.
 */
public class ContactsPage extends BasePage implements OnItemClickListener {

    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.recyclerview)
    RecyclerView recyclerView;

    @InjectView(R.id.progress)
    View progress;

    private ContactAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.page_contacts;
    }

    @Override
    protected View setupView(View view) {
        swipeRefreshLayout.setOnRefreshListener(() -> refreshData());
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW);
        initVertical();
        return super.setupView(view);
    }

    public void initVertical() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ContactAdapter(null);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        refreshData();
    }

    @Override
    public void onItemClicked(View itemView, Object data) {
        DetailPage detailPage = new DetailPage();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Account.class.getName(), (java.io.Serializable) data);
        detailPage.setArguments(bundle);
        pageManager.push(detailPage);
    }

    private void refreshData() {
        progress.setVisibility(View.VISIBLE);
        LoginHelper.helper.loadMembers(this::updateData);
    }

    private void updateData(List<Account> accounts) {
        progress.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        adapter.updateData(accounts);
    }

}
