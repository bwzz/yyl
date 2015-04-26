package com.yuantiku.yyl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuantiku.yyl.util.LogUtils;

import butterknife.ButterKnife;

/**
 * @author lirui
 * @date 15/4/13.
 */
public abstract class BaseFragment extends Fragment {

    public static LogUtils L;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.inject(this, view);
        setupView(inflater, view, savedInstanceState);
        return view;
    }

    public void setupView(LayoutInflater inflater, View convertView,
            @Nullable Bundle savedInstanceState) {}

    protected abstract int getLayoutResId();
}
