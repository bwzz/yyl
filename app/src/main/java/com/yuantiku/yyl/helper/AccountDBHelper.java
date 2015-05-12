package com.yuantiku.yyl.helper;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.yuantiku.dbdata.Account;
import com.yuantiku.dbdata.AccountDao;
import com.yuantiku.dbdata.AccountDao.Properties;
import com.yuantiku.dbdata.DaoMaster;
import com.yuantiku.dbdata.DaoMaster.DevOpenHelper;
import com.yuantiku.dbdata.DaoSession;
import com.yuantiku.yyl.MyApplication;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @author lirui
 * @date 15/4/29.
 */
public enum AccountDBHelper {
    INSTANCE;

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

    public List<Account> query(String text) {
        if (TextUtils.isEmpty(text)) {
            return getAccounts();
        }
        StringBuilder stringBuilder = new StringBuilder(text);
        QueryBuilder queryBuilder = accountDao.queryBuilder();
        if (isChinese(text.charAt(0))) {
            stringBuilder.insert(0, "%").append("%");
            queryBuilder.where(Properties.Name.like(stringBuilder.toString()));
        } else {
            for (int i = 0; i < text.length(); i++) {
                stringBuilder.insert(i * 2, "%");
            }
            stringBuilder.append("%");
            queryBuilder.where(Properties.Ldap.like(stringBuilder.toString()));
        }
        return queryBuilder.list();
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    public void clear() {
        accountDao.deleteAll();
    }
}
