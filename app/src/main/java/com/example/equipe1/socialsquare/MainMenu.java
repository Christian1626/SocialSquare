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
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;


import com.example.equipe1.webservice.Joueur;
import com.example.equipe1.webservice.ThreadDispo;
import com.example.equipe1.webservice.ThreadScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


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

        callAsynchronousTask();

        getSupportActionBar().hide();
    }


    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            ThreadDispo performBackgroundTask = new ThreadDispo();
                            // PerformBackgroundTask this class is the class that extends AsynchTask
                            performBackgroundTask.execute();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 1000 * 10); //execute in every 50000 ms
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

        public static boolean isDispo;
        public static ImageView imageDispo;
        private static final String ARG_SECTION_NUMBER = "section_number";
        public static View rootView;
        public static GridView gridView;
        public static int id;
        public static String[] scores1 = new String[] {
                "Place","Nom","Score",
                "1","Toto", "100","2", "Titi", "95","3", "Tata",
                "80", "4","Tutu", "76","5", "Toto", "60","6",
                "Coco", "50", "7","TheNoob"};

        public void setScores(String[] scores) {
            this.scores = scores;
        }

        public static String[] scores = scores1;
        //private List<Score> scores;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            id = sectionNumber;
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Log.d("ID fragment",String.valueOf(getArguments().getInt(ARG_SECTION_NUMBER)));
            //=================
            //     HOME
            //=================
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                rootView = inflater.inflate(R.layout.fragment_main_menu,container,false);

                imageDispo = (ImageView) rootView.findViewById(R.id.img_dispo);

                changerDispo();
                System.out.println("imagedispo:"+imageDispo);
            }

            //=================
            //   TOP SCORES
            //=================
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                rootView = inflater.inflate(R.layout.fragment_topscores,container,false);

                gridView = (GridView) rootView.findViewById(R.id.gridView);


                configScore("Memory");
                configSpinner();
                //configGridView();
            }

            //=================
            //    JOUEURS
            //=================
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                rootView = inflater.inflate(R.layout.fragment_joueurs,container,false);

                ListView list = (ListView) rootView.findViewById(R.id.listJoueur);
                List<Joueur> rowItems = new ArrayList<Joueur>();

                rowItems.add(new Joueur("Christian"));
                rowItems.add(new Joueur("Jeremy"));
                rowItems.add(new Joueur("Martin"));
                rowItems.add(new Joueur("Arnaud"));
                rowItems.add(new Joueur("Robin"));

                SimpleAdapter adapter = new SimpleAdapter(getActivity(), rowItems);
                list.setAdapter(adapter);
            }

            //=================
            //   EVENEMENTS
            //=================
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 4) {
                rootView = inflater.inflate(R.layout.fragment_evenement,container,false);
                WebView webView = (WebView) rootView.findViewById(R.id.webview);
                webView.loadUrl("http://www.mairie-epron.fr/fr/agenda/agenda.php");
                webView.setWebViewClient(new WebViewClient());
            }
            return rootView;
        }

        public void configScore(String jeu) {
            ThreadScore ws = new ThreadScore(jeu);
            ws.execute();
        }


        void configSpinner() {
            //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
            Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
            //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
            List listJeux = new ArrayList();
            listJeux.add("Memory");
            listJeux.add("TicTacToe");
            listJeux.add("ConnectFour");
            listJeux.add("Dance");
            listJeux.add("HopScotch");
            listJeux.add("RunAfterTheLight");

            ArrayAdapter adapterSpinner = new ArrayAdapter(
                    rootView.getContext(),
                    android.R.layout.simple_spinner_item,
                    listJeux
            );

            adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapterSpinner);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String jeu = parent.getItemAtPosition(position).toString();
                    Log.d("Nom du jeu ",jeu);
                    configScore(jeu);
                }

                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


        public static void configGridView() {
            ArrayAdapter<String> adapterGrid = new ArrayAdapter<String>(rootView.getContext(),
                    android.R.layout.simple_list_item_1, scores);

            gridView.setAdapter(adapterGrid);
        }

        public static void changerDispo() {
            if(imageDispo != null) {
                Log.d("isDispo:",String.valueOf(isDispo));
                if(isDispo) {
                    Log.d("changerDispo","dispo");
                    imageDispo.setImageResource(R.drawable.disponible);
                } else {
                    Log.d("changerDispo","occupe");
                   imageDispo.setImageResource(R.drawable.occupe);
                }
            }
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
            Log.d("position",String.valueOf(position));
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ACCUEIL";
                case 1:
                    return "TOP SCORES";
                case 2:
                    return "JOUEURS";
                case 3:
                    return "INFOS";

            }
            return null;
        }
    }
}
