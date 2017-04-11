package info.andreassen.mmi;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ProgressionActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private static SortedSet<ListEntry> highScoreList = new TreeSet();

    static {
        highScoreList.add(new ListEntry("Gunnar Rolfsen", 5000));
        highScoreList.add(new ListEntry("Rolf Pedersen", 15000));
        highScoreList.add(new ListEntry("Peder Arnesen", 22000));
        highScoreList.add(new ListEntry("Berit Heidal", 55000));
        highScoreList.add(new ListEntry("Veronica Langevåg", 7481));
        highScoreList.add(new ListEntry("Rolf-Arne Larsen", 9814));
        highScoreList.add(new ListEntry("Reidun Andreassen", 100000));
        highScoreList.add(new ListEntry("Per-Ove Hanssen", 20000));
    }

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progression);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                rootView = inflater.inflate(R.layout.fragment_progression, container, false);
                LinearLayout listContainer = (LinearLayout) rootView.findViewById(R.id.listContainer);

                SortedSet<ListEntry> set = new TreeSet(highScoreList);
                String name = MainActivity.anonymous ? "Anonym" : "Jan Johansen";
                ListEntry listEntry = new ListEntry(name, MainActivity.getCurrentCompetition().getWalkedSteps());
                set.add(listEntry);

                int number = 1;

                for(ListEntry e : set) {
                    HighScoreView hView = new HighScoreView(getContext(), number++, e.getName(), e.getSteps(), MainActivity.getCurrentCompetition().getTotalSteps());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, 0, 0, 10);
                    if(e == listEntry)
                        hView.setCardBackgroundColor(0xFF1CC90C);
                    listContainer.addView(hView, layoutParams);
                }

            } else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                rootView = inflater.inflate(R.layout.fragment_map, container, false);
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tabell";
                case 1:
                    return "Kart";
            }
            return null;
        }
    }

    static class ListEntry implements Comparable<ListEntry> {
        private String name;
        private int steps;

        public ListEntry(String name, int steps) {
            this.name = name;
            this.steps = steps;
        }

        public String getName() {
            return name;
        }

        public int getSteps() {
            return steps;
        }

        @Override
        public String toString() {
            return name + ": " + steps;
        }

        @Override
        public int compareTo(@NonNull ListEntry o) {
            return steps - o.getSteps();
        }
    }

}
