package com.yuantiku.yyl.helper;

import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.interfaces.WikiService;
import com.yuantiku.yyl.webadapter.WikiAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import retrofit.client.Response;
import rx.Observable;
import rx.functions.Action1;

/**
 * @author lirui
 * @date 15/4/29.
 */
public enum LoginHelper {
    helper;

    private WikiService service;
    private List<Account> accountList;
    private Action1<Account> onNextAction;

    LoginHelper() {
        service = WikiAdapter.getService();
        accountList = new ArrayList<>();
    }

    public void login(String name, String password) {
        service.login(name, password, "Login")
                .subscribe(success -> loadMembers(), failure -> L.e(failure.getMessage()));
    }

    private void loadMembers() {
        service.getMembers()
                .subscribe(this::parseResponse, error -> L.e(error.getMessage()));
    }

    private void parseResponse(Response response) {
        Document document;
        try {
            document = Jsoup.parse(response.getBody().in(), "UTF-8",
                    "https://wiki.zhenguanyu.com/");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Elements items = document.getElementsByTag("tr");
        for (Element item : items) {
            Elements attrs = item.getElementsByTag("td");
            String[] res = attrs.text().split(" ");
            saveAccount(res);
        }
        Observable.from(accountList)
                .subscribe(onNextAction);
        AccountDBHelper.helper.save(accountList);
    }

    private void saveAccount(String[] infos) {
        if (infos.length < 8) {
            return;
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
        accountList.add(account);
    }

    public void setOnNextAction(Action1<Account> onNextAction) {
        this.onNextAction = onNextAction;
    }
}
