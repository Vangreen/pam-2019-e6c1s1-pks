package pl.wat.pks;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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
                PortfelTab portfelTab = new PortfelTab();
                return portfelTab;
            case 1:
                AktualneKursyTab aktualneKursyTab = new AktualneKursyTab();
                return aktualneKursyTab;
            case 2:
                UstawieniaTab ustawieniaTab = new UstawieniaTab();
                return ustawieniaTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

}

