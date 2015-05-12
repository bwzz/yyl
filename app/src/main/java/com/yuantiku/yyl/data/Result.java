package com.yuantiku.yyl.data;

import com.yuantiku.dbdata.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lirui
 * @date 15/5/12.
 */
public class Result extends BaseData {
    private List<User> data;
    private String detail;

    public List<User> getData() {
        return data;
    }

    public String getDetail() {
        return detail;
    }

    public static class User {
        private String mobile;
        private String fullname;
        private String name;
        private String email;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static List<Account> fromUser(List<User> users){
        List<Account> accounts = new ArrayList<>();
        for(User user : users){
            accounts.add(fromUser(user));
        }
        return accounts;
    }

    public static Account fromUser(User user){
        Account account = new Account();
        account.setName(user.getFullname());
        account.setEmail(user.getEmail());
        account.setLdap(user.getName());
        account.setPhone(user.getMobile());
        return account;
    }
}
