package com.yuantiku.yyl.helper;

import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.interfaces.WikiService;
import com.yuantiku.yyl.webadapter.WikiAdapter;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

    LoginHelper() {
        service = WikiAdapter.getService();
    }

    public Observable<Response> login(String name, String password) {
        return service.login(name, password, "Login");
    }

    public void loadMembers(Action1<List<Account>> onNextAction) {
        service.getMembers()
                .subscribe(response -> parseResponse(response, onNextAction),
                        error -> L.e(error.getMessage()));
    }

    private void parseResponse(Response response, Action1<List<Account>> onNextAction) {
        Document document;
        try {
            document = Jsoup.parse(response.getBody().in(), "UTF-8",
                    "https://wiki.zhenguanyu.com/");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Elements items = document.getElementsByTag("tr");
        Observable.from(items)
                .map(item -> item.getElementsByTag("td").text().split(" "))
                .map(this::parseAccout)
                .toList()
                .subscribe(accountList -> {
                    AccountDBHelper.helper.clear();
                    AccountDBHelper.helper.save(accountList);
                    onNextAction.call(accountList);
                });
    }

    private Account parseAccout(String[] info) {
        if (info.length < 8) {
            return null;
        }
        Account account = new Account();
        account.setName(info[0]);
        account.setLdap(info[1]);
        account.setEmail(info[2]);
        account.setPhone(info[3]);
        account.setDept(info[4]);
        account.setGoogleAccount(info[5]);
        account.setBirth(info[6]);
        account.setConstellation(info[7]);
        return account;
    }

}
