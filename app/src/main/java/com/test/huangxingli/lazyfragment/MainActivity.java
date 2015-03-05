package com.test.huangxingli.lazyfragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;



import java.util.ArrayList;



public class MainActivity extends ActionBarActivity {

    static final String TAG="TAG";
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager);
        Button bn=new Button(getApplicationContext());
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ArrayList<TestLazyFragment> datas=new ArrayList<TestLazyFragment>();

        TestLazyFragment testLazyFragment1=new TestLazyFragment();

        TestLazyFragment testLazyFragment2=new TestLazyFragment();
        TestLazyFragment testLazyFragment3=new TestLazyFragment();
        TestLazyFragment testLazyFragment4=new TestLazyFragment();
        TestLazyFragment testLazyFragment5=new TestLazyFragment();
        TestLazyFragment testLazyFragment6=new TestLazyFragment();
        addFragmentBundle(testLazyFragment1,"this is fragment1", Color.RED);
        addFragmentBundle(testLazyFragment2,"this is fragment2",Color.GREEN);
        addFragmentBundle(testLazyFragment3,"this is fragment3",Color.WHITE);
        addFragmentBundle(testLazyFragment4,"this is fragment4",Color.BLUE);
        addFragmentBundle(testLazyFragment5,"this is fragment5",Color.GRAY);
        addFragmentBundle(testLazyFragment6,"this is fragment6",Color.MAGENTA);



        datas.add(testLazyFragment1);
        datas.add(testLazyFragment2);
        datas.add(testLazyFragment3);
        datas.add(testLazyFragment4);
        datas.add(testLazyFragment5);
        datas.add(testLazyFragment6);
        MyFragmentViewPagerAdapter adapter=new MyFragmentViewPagerAdapter(getSupportFragmentManager(),datas);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true,new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                Log.v(TAG, "______________________________________transformPage position is " + position);
                Log.v(TAG, "_______________________________view is ---------------" + view);

                int pageWidth = view.getWidth();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);

                } else if (position <= 0) { // [-1,0]
                    // Use the default slide transition when moving to the left page
                    view.setAlpha(1);
                    view.setTranslationX(0);
                    view.setScaleX(1);
                    view.setScaleY(1);

                } else if (position <= 1) { // (0,1]
                    // Fade the page out.
                    view.setAlpha(1 - position);

                    // Counteract the default slide transition
                    view.setTranslationX(pageWidth * -position);

                    // Scale the page down (between MIN_SCALE and 1)
                    float scaleFactor = MIN_SCALE
                            + (1 - MIN_SCALE) * (1 - Math.abs(position));
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
                //viewPager.setTeans

            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.v(TAG,"===++###############################++position is "+position+"---positionOffset is "+positionOffset+"-------positionOffsetPixels is "+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




    public void addFragmentBundle(Fragment fragment,String data,int color){
        Bundle bundle=new Bundle();
        bundle.putString("extra",data);
        bundle.putInt("color",color);
        fragment.setArguments(bundle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyFragmentViewPagerAdapter extends FragmentPagerAdapter{

        ArrayList<TestLazyFragment> list=new ArrayList<TestLazyFragment>();


        public MyFragmentViewPagerAdapter(FragmentManager manager,ArrayList<TestLazyFragment> list){
            super(manager);
            this.list=list;
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
