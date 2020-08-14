package com.assignment2.assignment2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentItemSelectedListener,
NavigationView.OnNavigationItemSelectedListener{
    MainFragment mainFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DrawerLayout drawer;
     NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dashboard");
        navigationView = findViewById(R.id.nested);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        mainFragment = new MainFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,mainFragment);
        fragmentTransaction.commit();// add the fragment


    }

    @Override
    public void onButtonSelected() {
        Toast.makeText(this, "Inserted Data", Toast.LENGTH_SHORT).show();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,new SecondFragment());
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        closeDrawer();
        if(menuItem.getItemId() == R.id.home){
            loadFragment2(new MainFragment());
        }
        if(menuItem.getItemId() == R.id.next){
            loadFragment2(new SecondFragment());
        }
        if(menuItem.getItemId() == R.id.next2){
            loadFragment3(new ThirdFragment());
        }
        return true;
    }

    private void loadFragment2(Fragment secondFragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,secondFragment);
        fragmentTransaction.commit();
    }

    private void loadFragment3(Fragment thirdFragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,thirdFragment);
        fragmentTransaction.commit();
    }

    private void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }
}
