package pl.wat.pks;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements Tab1.OnFragmentInteractionListener,Tab2.OnFragmentInteractionListener,Tab3.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initialViews();
        this.createNavigationItem();

        //pages


        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),3);
        viewPager.setAdapter(adapter);

    }

    AHBottomNavigation bottomNavigation;
    TextView headertxt;

    private void initialViews(){
        bottomNavigation= findViewById(R.id.bottom_navigation);
        headertxt=findViewById(R.id.headerLabel);
    }

    private void createNavigationItem(){
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab1, R.drawable.ic_wallet, R.color.design_default_color_primary );
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab2, R.drawable.ic_chart_white, R.color.design_default_color_primary );
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab3, R.drawable.ic_settings_white_24dp, R.color.design_default_color_primary );

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#757575"));

        //force tint
        bottomNavigation.setForceTint(true);

        //use colored navigation with animation :~D
        bottomNavigation.setColored(true);

        //
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Intent intent;
                switch (position){
                    case 0:
                        //
                        break;
                    case 1:
                      //  intent = new Intent(getBaseContext(), ChartActivity.class);
                       // startActivity(intent);
                      //  overridePendingTransition(0, 0);
                        break;
                    case 2:
                        //
                        break;
                    default:
                        break;
                }

                return true;
            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
