package com.yuantiku.yyl.helper;

import android.database.sqlite.SQLiteDatabase;

import com.yuantiku.dbdata.Account;
import com.yuantiku.dbdata.AccountDao;
import com.yuantiku.dbdata.DaoMaster;
import com.yuantiku.dbdata.DaoMaster.DevOpenHelper;
import com.yuantiku.dbdata.DaoSession;
import com.yuantiku.yyl.MyApplication;

import java.util.List;

/**
 * @author lirui
 * @date 15/4/29.
 */
public enum AccountDBHelper {
    helper;

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private AccountDao accountDao;

    AccountDBHelper() {
        DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getInstance()
                .getApplicationContext(),
                "accounds-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        accountDao = daoSession.getAccountDao();
    }

    public List<Account> getAccounts() {
        return accountDao.loadAll();
    }

    public void save(Account account) {
        accountDao.insertOrReplace(account);
    }

    public void save(List<Account> accounts) {
        accountDao.insertOrReplaceInTx(accounts);
    }

    public void clear() {
        accountDao.deleteAll();
    }
}
