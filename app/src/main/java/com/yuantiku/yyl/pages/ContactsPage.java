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
import com.yuantiku.yyl.helper.AccountDBHelper;
import com.yuantiku.yyl.interfaces.OnItemClickListener;
import com.yuantiku.yyl.webadapter.WikiAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.InjectView;
import retrofit.client.Response;

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
        swipeRefreshLayout.setOnRefreshListener(() -> swipeRefreshLayout.setRefreshing(false));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW);
        initVertical();
        return super.setupView(view);
    }

    public void initVertical() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
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
        WikiAdapter
                .getService()
                .getMembers()
                .subscribe(response -> updateData(parseResponse(response)),
                        failure -> progress.setVisibility(View.GONE));
    }

    private void updateData(List<Account> accounts) {
        if (adapter == null) {
            adapter = new ContactAdapter(accounts);
            adapter.setOnItemClickListener(this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updateData(accounts);
        }
    }

    private List<Account> parseResponse(Response response) {
        Document document;
        try {
            document = Jsoup.parse(response.getBody().in(), "UTF-8",
                    "https://wiki.zhenguanyu.com/");
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        List<Account> accounts = new ArrayList<>();
        Elements items = document.getElementsByTag("tr");
        for (Element item : items) {
            Elements attrs = item.getElementsByTag("td");
            String[] res = attrs.text().split(" ");
            Account account = saveAccount(res);
            if (account != null) {
                accounts.add(account);
            }
        }
        AccountDBHelper.helper.save(accounts);
        return accounts;
    }

    private Account saveAccount(String[] infos) {
        if (infos.length < 8) {
            return null;
        }
        Account account = new Account();
        account.setName(infos[0]);
        account.setLdap(infos[1]);
        account.setEmail(infos[2]);
        account.setPhone(infos[3]);
        account.setDept(infos[4]);
        account.setGoogleAccount(infos[5]);
        account.setBirth(infos[6]);
        account.setConstellation(infos[7]);
        return account;
    }
}
