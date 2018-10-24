package com.thiago_viotto.projetonewsapi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.thiago_viotto.projetonewsapi.fragment.CienciaFragment;
import com.thiago_viotto.projetonewsapi.fragment.EsporteFragment;
import com.thiago_viotto.projetonewsapi.fragment.HomeFragment;
import com.thiago_viotto.projetonewsapi.R;
import com.thiago_viotto.projetonewsapi.fragment.TecnologiaFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment h = new HomeFragment();
            fragmentTransaction.add(R.id.frame,h,"home");
            fragmentTransaction.commit();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Fragment homeFragment = fragmentManager.findFragmentByTag("home");
            if(homeFragment == null){
                homeFragment = new HomeFragment();
            }
            replaceFragment(homeFragment,"home");
        } else if (id == R.id.nav_tecnologia) {
            Fragment tecnologiaFragment = fragmentManager.findFragmentByTag("tecnologia");
            if(tecnologiaFragment == null){
                tecnologiaFragment = new TecnologiaFragment();
            }
            replaceFragment(tecnologiaFragment,"tecnologia");
        } else if (id == R.id.nav_esporte) {
            Fragment esporteFragment = fragmentManager.findFragmentByTag("esporte");
            if(esporteFragment == null){
                esporteFragment = new EsporteFragment();
            }
            replaceFragment(esporteFragment,"esporte");
        } else if (id == R.id.nav_educacao) {
            Fragment cienciaFragment = fragmentManager.findFragmentByTag("ciencia");
            if(cienciaFragment == null){
                cienciaFragment = new CienciaFragment();
            }
            replaceFragment(cienciaFragment,"ciencia");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment, String tag){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment,tag);
        fragmentTransaction.addToBackStack(null);
        //fragmentManager.popBackStack(tag, fragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.commit();
    }

}
