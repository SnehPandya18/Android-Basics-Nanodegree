package com.snehpandya.newsfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final String TITLE = "toolbarTitle";
                String mTitle = "";
                Intent fragmentSendIntent;
                switch (item.getItemId()) {
                    case R.id.nav_top_stories:
                        tabLayout.getTabAt(0).select();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_world:
                        tabLayout.getTabAt(1).select();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_politics:
                        tabLayout.getTabAt(2).select();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_national:
                        mTitle = getString(R.string.national);
                        fragmentSendIntent = new Intent(MainActivity.this, MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE, mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_technology:
                        mTitle = getString(R.string.technology);
                        fragmentSendIntent = new Intent(MainActivity.this, MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE, mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_food:
                        mTitle = getString(R.string.food);
                        fragmentSendIntent = new Intent(MainActivity.this, MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE, mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_travel:
                        mTitle = getString(R.string.travel);
                        fragmentSendIntent = new Intent(MainActivity.this, MoreOption.class);
                        fragmentSendIntent.putExtra(TITLE, mTitle);
                        startActivity(fragmentSendIntent);
                        break;
                    case R.id.nav_share:
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "Enter Your App Link");
                        startActivity(shareIntent);
                        break;
                    case R.id.nav_about:
                        Intent aboutIntent = new Intent(MainActivity.this, AboutPage.class);
                        startActivity(aboutIntent);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}