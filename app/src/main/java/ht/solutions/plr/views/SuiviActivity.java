package ht.solutions.plr.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import ht.solutions.plr.R;

import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.MainActivity;
import ht.solutions.plr.util.ZoomOutPageTransformer;

public class SuiviActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static   int NUM_PAGES = 4;

    private FragmentTrouvailleVisite FragmentTrouvailleVisite;
    private FragmentConfirm FragmentConfirm;
    private InitVisitFragment InitVisitFragment;
    private FragmentResultatRelance FragmentResultatRelance;


    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivi);
        Suivi2 s = Session.getCurrentSuivi();
        if (s == null){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            this.finish();
            return;
        }
        boolean isverificationAdresse = s.getRaisonvisite().contains("adresse");
        boolean distribution = s.getRaisonvisite().contains("distribution");
        if(isverificationAdresse)
            NUM_PAGES = 3;
        else
            NUM_PAGES = 4;

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(NUM_PAGES);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);


    }
    public void save() {
        boolean isverificationAdresse = Session.getCurrentSuivi().getRaisonvisite().contains("adresse");

        InitVisitFragment.save();
        if(!isverificationAdresse)
            FragmentTrouvailleVisite.save();
        FragmentResultatRelance.save();
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            // super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }


    }



    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            boolean isverificationAdresse = Session.getCurrentSuivi().getRaisonvisite().contains("adresse");
            boolean distribution = Session.getCurrentSuivi().getRaisonvisite().contains("distribution");

            if(isverificationAdresse){
                if(position == 0){
                    if(InitVisitFragment == null)
                        InitVisitFragment = new InitVisitFragment();
                    return InitVisitFragment ;
                }
                else if(position == 1){
                    if(FragmentResultatRelance == null)
                        FragmentResultatRelance = new FragmentResultatRelance();
                    return FragmentResultatRelance ;
                }
                else if(position == 2) {
                    if(FragmentConfirm == null)
                        FragmentConfirm = new FragmentConfirm();
                    return FragmentConfirm ;
                }
            }

            else{
                if(position == 0){
                    if(InitVisitFragment == null)
                        InitVisitFragment = new InitVisitFragment();
                    return InitVisitFragment ;
                }

                else if(position == 1){
                    if(FragmentTrouvailleVisite == null)
                        FragmentTrouvailleVisite = new FragmentTrouvailleVisite();
                    return FragmentTrouvailleVisite ;

                }
                else if(position == 2){
                    if(FragmentResultatRelance == null)
                        FragmentResultatRelance = new FragmentResultatRelance();
                    return FragmentResultatRelance ;
                }
                else if(position == 3) {
                    if(FragmentConfirm == null)
                        FragmentConfirm = new FragmentConfirm();
                    return FragmentConfirm ;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
