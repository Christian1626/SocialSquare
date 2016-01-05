package com.example.equipe1.socialsquare;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {



    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        getSupportActionBar().hide();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */


        private static final String ARG_SECTION_NUMBER = "section_number";
        private boolean isDispo = true;
        private View rootView;
        private String[] scores1 = new String[] {
                "Place","Nom","Score",
                "1","Toto", "100","2", "Titi", "95","3", "Tata",
                "80", "4","Tutu", "76","5", "Toto", "60","6",
                "Coco", "50", "7","TheNoob", "0"};

        private String[] scores2 = new String[] {
                "Place","Nom","Score",
                "1","Paul", "88","2", "Thomas", "85","3", "Jack",
                "78", "4","Tom", "67","5", "Jim", "55","6",
                "George", "60", "7","Jerry", "55"};

        private String[] scores = scores1;

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
            //View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
            //View rootView;
            /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/



            //home
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                rootView = inflater.inflate(R.layout.fragment_main_menu,container,false);
                rootView.setOnClickListener(touchListener);
                changerDispo();


            }
            //TOP SCORES
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                rootView = inflater.inflate(R.layout.fragment_topscores,container,false);

                //config spinner
                //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
                Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
                //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
                List listJeux = new ArrayList();
                listJeux.add("Jeux 1");
                listJeux.add("Jeux 2");
                listJeux.add("Jeux 3");

                ArrayAdapter adapterSpinner = new ArrayAdapter(
                        rootView.getContext(),
                        android.R.layout.simple_spinner_item,
                        listJeux
                );

                adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapterSpinner);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = parent.getItemAtPosition(position).toString();

                        if (selectedItem.equals("Jeux 1")) {
                            scores = scores1;
                       } else {
                            scores = scores2;
                        }

                        configGridView();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                configGridView();




            }
            //
            else {
                rootView = inflater.inflate(R.layout.activity_login,container,false);
            }
            return rootView;
        }




        void configGridView() {
            //config gridview
            GridView gridView;

            gridView = (GridView) rootView.findViewById(R.id.gridView);

            ArrayAdapter<String> adapterGrid = new ArrayAdapter<String>(rootView.getContext(),
                    android.R.layout.simple_list_item_1, scores);

            gridView.setAdapter(adapterGrid);
        }

        void changerDispo() {

            ImageView imageDispo = null;
            imageDispo = (ImageView) rootView.findViewById(R.id.img_dispo);
            Log.d("test","changeDispo");

            if(isDispo) {
                isDispo = false;
            } else {
                isDispo = true;
            }

            if(imageDispo != null) {
                System.out.println("isDispo:"+isDispo);
                if(isDispo) {
                    Log.d("test","dispo");
                    imageDispo.setImageResource(R.drawable.disponible);
                } else {
                    Log.d("test","occupe");
                   imageDispo.setImageResource(R.drawable.occupe);
                }
            }
        }




        private View.OnClickListener touchListener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("touch", "touch");
                changerDispo();
            }
        };
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
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HOME";
                case 1:
                    return "TOP SCORES";
                case 2:
                    return "RECHERCHE JOUEURS";
            }
            return null;
        }
    }
}
