package pl.wat.pks.views.components;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import pl.wat.pks.views.ActualCourseView;
import pl.wat.pks.views.SettingsView;
import pl.wat.pks.views.WalletView;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position)
        {

            case 0:
                WalletView walletView = new WalletView();
                return walletView;
            case 1:
                ActualCourseView actualCourseView = new ActualCourseView();
                return actualCourseView;
            case 2:
                SettingsView settingsView = new SettingsView();
                return settingsView;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

}

