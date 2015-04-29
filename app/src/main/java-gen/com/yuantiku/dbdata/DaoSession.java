package com.yuantiku.dbdata;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.yuantiku.dbdata.Account;

import com.yuantiku.dbdata.AccountDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig accountDaoConfig;

    private final AccountDao accountDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        accountDaoConfig = daoConfigMap.get(AccountDao.class).clone();
        accountDaoConfig.initIdentityScope(type);

        accountDao = new AccountDao(accountDaoConfig, this);

        registerDao(Account.class, accountDao);
    }
    
    public void clear() {
        accountDaoConfig.getIdentityScope().clear();
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

}
