package com.joinable.phatsprints.joinable;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // declare views
    TextView   today;
    TextView   tomorrow;
    EditText   location;
    EditText   description;
    Button     shareButton;

    // screen sizes for resizing/repositioning views
    int screenWidth = 0;
    int screenHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkGUI();
        setupViewpagerTablayout();
    }

    // link the UI views to their respective layouts
    public void linkGUI() {
        today       = (TextView) findViewById(R.id.todayText);
        tomorrow    = (TextView) findViewById(R.id.tomorrowText);
        location    = (EditText) findViewById(R.id.locationENTRY);
        description = (EditText) findViewById(R.id.descriptionEDITOR);
        shareButton = (Button) findViewById(R.id.shareButton);
    }

    // setup viewpager
    public void setupViewpagerTablayout() {
        // link the TabLayout to its layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        // assign texts for the top tabs
        tabLayout.addTab(tabLayout.newTab().setText("Create"));
        tabLayout.addTab(tabLayout.newTab().setText("Notifications"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter pAdapter = new PagerAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(pAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // manipulate UI views once the elements are fully rendered and sizes
    // are obtainable
    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            getScreenDimensions();
            repositionUI();
            resizeUI();
        }
    }

    public void repositionUI() {
        // may need for future screen sizes/resolutions
    }

    public void resizeUI() {
        // may need for future screen sizes/resolutions
    }

    // retrieve the screen dimensions
    public void getScreenDimensions() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size      = new Point();
        display.getSize(size);
        screenWidth     = size.x;
        screenHeight    = size.y;
    }
}
