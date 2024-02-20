package com.example.slothslider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener{
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar();
        setupDrawer();
        //setupNavigationView();
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(findViewById(R.id.toolbar));
    }
    private void setupDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        navigationView = findViewById(R.id.navigation_view);
        NavigationUI.setupWithNavController(navigationView, navController);

        appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.nav_popular, R.id.nav_buscar, R.id.nav_perfil, R.id.nav_ver_mas_tarde, R.id.nav_listas
                        ,R.id.nav_diario, R.id.nav_reviews, R.id.nav_ajustes)
                        .setOpenableLayout(drawerLayout)
                        .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        drawerLayout.addDrawerListener(this);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int titleId = getTitle(menuItem);
       // showFragment(titleId);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    private int getTitle(@NonNull MenuItem menuItem) {
        int op = menuItem.getItemId();
        if (op == R.id.nav_popular) {
            return R.string.menu_popular;
        }else if (op == R.id.nav_buscar) {
            return R.string.menu_buscar;
        }else if(op == R.id.nav_perfil) {
            return R.string.menu_perfil;
        }else if (op == R.id.nav_ver_mas_tarde) {
            return R.string.menu_ver_mas_tarde;
        } else if (op == R.id.nav_listas) {
            return R.string.menu_listas;
        } else if (op == R.id.nav_diario) {
            return R.string.menu_diario;
        } else if (op == R.id.nav_reviews) {
            return R.string.menu_reviews;
        } else if (op == R.id.nav_ajustes) {
            return R.string.menu_ajustes;
        }else
            throw new IllegalArgumentException("menu option not implemented!!");

    }





    @Override
    public void onDrawerSlide(@NonNull View view, float v) {
        //cambio en la posición del drawer
    }

    @Override
    public void onDrawerOpened(@NonNull View view) {
        //el drawer se ha abierto completamente
        Toast.makeText(this, getString(R.string.navigation_drawer_open),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
        //el drawer se ha cerrado completamente
    }

    @Override
    public void onDrawerStateChanged(int i) {
        //cambio de estado, puede ser STATE_IDLE, STATE_DRAGGING or STATE_SETTLING
    }

}
