package com.test.huangxingli.lazyfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by huangxingli on 2015/2/27.
 */
public class TestLazyFragment extends  LazyFragment {

    protected  boolean isPrepared=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG,"testlazyFragment onCreateView");
        Bundle bundle=getArguments();
        View view=inflater.inflate(R.layout.fragmentlayout,null);

        TextView fTextView= (TextView) view.findViewById(R.id.ftextView);
        LinearLayout containerl= (LinearLayout) view.findViewById(R.id.container);
        fTextView.setText(bundle.getString("extra"));
        int color=bundle.getInt("color");
        containerl.setBackgroundColor(color);

        isPrepared=true;
        lazyload();


        return view;
    }

    @Override
    public void lazyload() {
        Log.v(TAG,"===isVisible is =="+isVisible);

        if(!isPrepared || !isVisible) {
            return;
        }

            Log.v(TAG, "====哈哈，我在加载了");

    }
}
