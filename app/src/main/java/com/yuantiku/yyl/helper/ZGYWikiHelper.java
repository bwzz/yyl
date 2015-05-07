package com.yuantiku.yyl.helper;

import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.webadapter.WikiAdapter;
import com.yuantiku.yyl.webadapter.WikiAdapter.WikiService;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * @author lirui
 * @date 15/4/29.
 */
public enum ZGYWikiHelper {
    helper;

    private WikiService service;

    ZGYWikiHelper() {
        service = WikiAdapter.getService();
    }

    public Observable<Response> login(String name, String password) {
        return service.login(name, password, "Login");
    }

    public void loadMembers(Action1<List<Account>> onNextAction,
            Action1<Throwable> onErrorAction) {
        service.getMembers()
                .subscribe(response -> parseResponse(response, onNextAction),
                        onErrorAction);
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
                .skip(1) // 第一个item是标题
                .map(item -> item.getElementsByTag("td").text().split(" "))
                .map(this::parseAccount)
                .toList()
                .doOnNext(accountList -> {
                    AccountDBHelper.helper.clear();
                    AccountDBHelper.helper.save(accountList);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction);
        ;
    }

    private Account parseAccount(String[] info) {
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
