package com.example.equipe1.socialsquare;

import android.os.AsyncTask;
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
import com.example.equipe1.webservice.Score;
import com.example.equipe1.webservice.ThreadDispo;
import com.example.equipe1.webservice.ThreadScore;
import com.example.equipe1.webservice.ThreadWS;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;


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

        //TODO: modifier avec les bon webservices
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
        timer.schedule(doAsynchronousTask, 0, 1000*10); //execute in every 50000 ms
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

        public static boolean isDispo = true;
        public static ImageView imageDispo;
        private static final String ARG_SECTION_NUMBER = "section_number";
        public static View rootView;
        public static GridView gridView;
        public static String[] scores1 = new String[] {
                "Place","Nom","Score",
                "1","Toto", "100","2", "Titi", "95","3", "Tata",
                "80", "4","Tutu", "76","5", "Toto", "60","6",
                "Coco", "50", "7","TheNoob"};

        public static String[] scores2 = new String[] {
                "Place","Nom","Score",
                "1","Paul", "88","2", "Thomas", "85","3", "Jack",
                "78", "4","Tom", "67","5", "Jim", "55","6",
                "George", "60", "7","Jerry", "55"};

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
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            //=================
            //     HOME
            //=================
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                rootView = inflater.inflate(R.layout.fragment_main_menu,container,false);
                //
                // rootView.setOnClickListener(touchListener);
                imageDispo = (ImageView) rootView.findViewById(R.id.img_dispo);
                System.out.println("imagedispo:"+imageDispo);
                //changerDispo();
            }
            //=================
            //   TOP SCORES
            //=================
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                rootView = inflater.inflate(R.layout.fragment_topscores,container,false);
                System.out.println("1");
                configScore("TicTacToe");
                System.out.println("2");
                configSpinner();
                configGridView();
                System.out.println("3");


            }
            //=================
            //    JOUEURS
            //=================
            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                rootView = inflater.inflate(R.layout.fragment_joueurs,container,false);
               ListView list = (ListView) rootView.findViewById(R.id.listJoueur);
                List<Joueur> rowItems = new ArrayList<Joueur>();

                for (int i = 0; i < 5; i++) {
                    Joueur items = new Joueur("title");
                    rowItems.add(items);
                }

                SimpleAdapter adapter = new SimpleAdapter(getActivity(), rowItems);
                list.setAdapter(adapter);
            }
            //=================
            //   EVENEMENTS
            //=================
            else {
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
            //config spinner
            //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
            Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
            //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
            List listJeux = new ArrayList();
            listJeux.add("TicTacToe");
            listJeux.add("Memory");
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
                    System.out.println("nom: " + jeu);
                    configScore(jeu);

                    //configGridView();
                }

                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


        public static void configGridView() {
            System.out.print("ConfigGridView: ");
            //config gridview

            if(gridView == null) {
                gridView = (GridView) rootView.findViewById(R.id.gridView);
            }

            System.out.println(gridView);

            ArrayAdapter<String> adapterGrid = new ArrayAdapter<String>(rootView.getContext(),
                    android.R.layout.simple_list_item_1, scores);



            gridView.setAdapter(adapterGrid);
        }

        public static void changerDispo() {
            Log.d("test","changeDispo");

            if(imageDispo != null) {
                System.out.println("isDispo:" + isDispo);
                if(isDispo) {
                    Log.d("test","dispo");
                    imageDispo.setImageResource(R.drawable.disponible);
                } else {
                    Log.d("test","occupe");
                   imageDispo.setImageResource(R.drawable.occupe);
                }
            }
        }




        /*private View.OnClickListener touchListener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("touch", "touch");
                changerDispo();
            }
        };*/
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
