package com.yuantiku.yyl.helper;

import com.google.gson.JsonObject;
import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.data.Result;
import com.yuantiku.yyl.webadapter.WikiAdapter;
import com.yuantiku.yyl.webadapter.WikiAdapter.WikiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * @author lirui
 * @date 15/4/29.
 */
public enum ZGYWikiHelper {
    INSTANCE;

    private WikiService service;

    ZGYWikiHelper() {
        service = WikiAdapter.getService();
    }

    public Observable<Map<String, String>> login(String name, String password) {
        return service.login(name, password);
    }

    public void loadMembers(Action1<List<Account>> onNextAction,
                            Action1<Throwable> onErrorAction) {
        service.getMembers(UserHelper.INSTANCE.getToken())
                .subscribe(response -> parseResponse(response.getData(), onNextAction),
                        onErrorAction);
    }

    private void parseResponse(Map<String, String> users, Action1<List<Account>> onNextAction) {
        Observable.just(parseAccounts(users))
                .doOnNext(AccountDBHelper.INSTANCE::save)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction);
    }

    private List<Account> parseAccounts(Map<String, String> accounts) {
        List<Account> res = new ArrayList<>();
        if (accounts == null) {
            return res;
        }
        for (String key : accounts.keySet()) {
            Account account = new Account();
            account.setLdap(key);
            account.setName(accounts.get(key));
            res.add(account);
        }
        return res;
    }

    public Observable<Account> getUserDetail(String ldap){
        return service.getUserDetail(UserHelper.INSTANCE.getToken(), ldap)
                .map(user -> Result.fromUser(user.getData()))
                .map(accounts -> accounts.get(0));
    }
//
//    @Deprecated
//    private void parseResponse(Response response, Action1<List<Account>> onNextAction) {
//        Document document;
//        try {
//            document = Jsoup.parse(response.getBody().in(), "UTF-8",
//                    "https://wiki.zhenguanyu.com/");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//        Elements items = document.getElementsByTag("tr");
//        Observable.from(items)
//                .skip(1) // 第一个item是标题
//                .map(item -> item.getElementsByTag("td").text().split(" "))
//                .map(this::parseAccount)
//                .toList()
//                .doOnNext(accountList -> {
//                    AccountDBHelper.INSTANCE.clear();
//                    AccountDBHelper.INSTANCE.save(accountList);
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(onNextAction);
//    }
//
//    private Account parseAccount(String[] info) {
//        if (info.length < 8) {
//            return null;
//        }
//        Account account = new Account();
//        account.setName(info[0]);
//        account.setLdap(info[1]);
//        account.setEmail(info[2]);
//        account.setPhone(info[3]);
//        account.setDept(info[4]);
//        account.setGoogleAccount(info[5]);
//        account.setBirth(info[6]);
//        account.setConstellation(info[7]);
//        return account;
//    }

}
