package com.suvam.wassup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;


import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.suvam.wassup.fragment.CallFragment;
import com.suvam.wassup.fragment.ChatFragment;
import com.suvam.wassup.utils.QuerySearchResults;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private QuerySearchResults chatSearchListener, callSearchListener;

    public SearchView searchView;
    public Toolbar toolbar;
    public Toolbar searchtoolbar;
    private FrameLayout mNoResults;
    Menu search_menu;
    MenuItem item_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mNoResults = (FrameLayout) findViewById(R.id.noSearchResults_container);

        setSupportActionBar(toolbar);
        setSearchtoolbar();
        setupViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                // Close search bar on tab change
                if (searchtoolbar.getVisibility() == View.VISIBLE) {
                    EditText text = searchView.findViewById(R.id.search_src_text);
                    text.setText("");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        circleReveal(R.id.searchtoolbar, 1, true, false);
                    else
                        searchtoolbar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) { }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ChatFragment(), "CHATS");
        adapter.addFragment(new CallFragment(), "CALLS");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) { return mFragmentList.get(position); }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_search) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                circleReveal(R.id.searchtoolbar, 1, true, true);
            else
                searchtoolbar.setVisibility(View.VISIBLE);

            item_search.expandActionView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActivityListener(int tab, Fragment fragment) {
        if (tab == 1)
            chatSearchListener = (ChatFragment) fragment;
        else if(tab == 2)
            callSearchListener = (CallFragment) fragment;
    }

    public void setSearchtoolbar() {
        searchtoolbar = (Toolbar) findViewById(R.id.searchtoolbar);
        if (searchtoolbar != null) {
            searchtoolbar.inflateMenu(R.menu.menu_search);
            search_menu = searchtoolbar.getMenu();

            searchtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        circleReveal(R.id.searchtoolbar, 1, true, false);
                    else
                        searchtoolbar.setVisibility(View.GONE);
                }
            });

            item_search = search_menu.findItem(R.id.action_filter_search);

            MenuItemCompat.setOnActionExpandListener(item_search, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleReveal(R.id.searchtoolbar, 1, true, false);
                    } else
                        searchtoolbar.setVisibility(View.GONE);
                    return true;
                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    return true;
                }
            });

            initSearchView();
        } else
            Log.e("toolbar", "setSearchtoolbar: NULL");
    }

    public void initSearchView() {
        searchView = (SearchView) search_menu.findItem(R.id.action_filter_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setSubmitButtonEnabled(false);

        ImageView closeButton = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_close);
        closeButton.setColorFilter(getResources().getColor(R.color.white));

        EditText txtSearch = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        txtSearch.setHint(R.string.search_hint);
        txtSearch.setHintTextColor(getResources().getColor(R.color.colorPrimaryDark));
        txtSearch.setTextColor(getResources().getColor(R.color.white));

        // to remove the line below editText in search widget
        View searchPlateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        if (searchPlateView != null) {
            searchPlateView.setBackgroundColor(Color.TRANSPARENT);
        }

        AutoCompleteTextView searchTextView = (AutoCompleteTextView) txtSearch;
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                chatSearchListener.doSearchInFragment(query);
                callSearchListener.doSearchInFragment(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                chatSearchListener.doSearchInFragment(query);
                callSearchListener.doSearchInFragment(query);
                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void circleReveal(int viewID, int posFromRight, boolean containsOverflow, final boolean isShow) {
        final View myView = findViewById(viewID);

        int width = myView.getWidth();

        if (posFromRight > 0)
            width -= (posFromRight * getResources().getDimensionPixelSize(android.support.v7.appcompat.R.dimen.abc_action_button_min_width_material)) - (getResources().getDimensionPixelSize(android.support.v7.appcompat.R.dimen.abc_action_button_min_width_material) / 2);
        if (containsOverflow)
            width -= getResources().getDimensionPixelSize(android.support.v7.appcompat.R.dimen.abc_action_button_min_width_overflow_material);

        int cx = width;
        int cy = myView.getHeight() / 2;

        Animator anim;
        if (isShow)
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, (float) width);
        else
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float) width, 0);

        anim.setDuration((long) 220);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isShow) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });

        if (isShow)
            myView.setVisibility(View.VISIBLE);

        anim.start();
    }

    public void highlightString(String input, TextView mTextView) {
        String text = mTextView.getText().toString().toLowerCase();
        if (text.contains(input)) {
            int startPos = text.indexOf(input);
            int endPos = startPos + input.length();

            Spannable spanText = Spannable.Factory.getInstance().newSpannable(mTextView.getText());
            spanText.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.blue)), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            mTextView.setText(spanText, TextView.BufferType.SPANNABLE);
        }
    }

    public void showNoSearchResults(int v) {
        mNoResults.setVisibility(v);
    }
}