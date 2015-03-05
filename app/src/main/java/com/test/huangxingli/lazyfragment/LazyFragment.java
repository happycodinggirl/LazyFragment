package com.test.huangxingli.lazyfragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by huangxingli on 2015/2/27.
 */
public abstract  class LazyFragment extends Fragment {
   public static final String TAG="TAG";
    protected  boolean isVisible=false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG,"LAZYfragment onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.v(TAG,"lazyfragment setUserVisibleHint");
        if (getUserVisibleHint()){

            isVisible=true;
            onVisible();

        }else{
            isVisible=false;
            onInVisible();

        }

    }

    public void onVisible(){
        Log.v(TAG,"哈哈，layzyfragment我已经可见了，准备加载");
        lazyload();
    }
    public abstract  void lazyload();
    public void onInVisible(){
        Log.v(TAG,"layzyfragmrent我还没准备好呢");

    }

}
