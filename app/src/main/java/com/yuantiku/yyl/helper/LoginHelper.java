package com.yuantiku.yyl.helper;

import com.yuantiku.dbdata.Account;
import com.yuantiku.yyl.interfaces.WikiService;
import com.yuantiku.yyl.webadapter.WikiAdapter;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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

    public void login(String name, String password) {
        service.login(name, password, "Login", new Callback<Object>() {

            @Override
            public void success(Object o, Response response) {
                L.i(response.toString());
                loadMembers();
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    private void loadMembers() {
        service.getMembers(new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {

                try {
                    Document document = Jsoup.parse(response.getBody().in(), "UTF-8",
                            "https://wiki.zhenguanyu.com/");
                    Elements items = document.getElementsByTag("tr");
                    for (Element item : items) {
                        Elements attrs = item.getElementsByTag("td");
                        String[] res = attrs.text().split(" ");
                        saveAccount(res);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
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
        AccountDBHelper.helper.save(account);
    }
}
