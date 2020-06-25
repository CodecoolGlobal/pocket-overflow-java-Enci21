package com.example.pocketoverflow.signIn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pocketoverflow.MainActivity;
import com.example.pocketoverflow.R;
import com.example.pocketoverflow.roomDB.User;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    TextView nameText;
    TextView houseText;
    User user;
    SharedPreferences sharedPref;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_commonroom, R.id.nav_spells)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        Intent intent = getIntent();
        user = intent.getParcelableExtra(SignInActivity.EXTRA_USER);


        ImageView logoImageView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        if (user.getHouse().replace("\"", "").equals("Gryffindor")) {
            navigationView.getHeaderView(0).setBackgroundResource(R.drawable.gryffindor_side_nav);
            logoImageView.setImageResource(R.drawable.gryffindor_logo);

        } else if (user.getHouse().replace("\"", "").equals("Hufflepuff")) {
            navigationView.getHeaderView(0).setBackgroundResource(R.drawable.huffle_side_nav);
            logoImageView.setImageResource(R.drawable.hufflepuff_logo);
        } else if (user.getHouse().replace("\"", "").equals("Ravenclaw")) {
            navigationView.getHeaderView(0).setBackgroundResource(R.drawable.ravenclaw_side_nav);
            logoImageView.setImageResource(R.drawable.ravenclaw_logo);
        }

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userName", user.getUsername());
        editor.putString("house", user.getHouse());
        editor.putString("patronus", user.getPatronus());
        editor.putString("animagus", user.getAnimagus());
        editor.putBoolean("DA", user.isDumbledoresArmy());
        editor.putBoolean("OF", user.isOrderOfThePhoenix());
        editor.commit();

        nameText = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nameText);
        houseText = (TextView) navigationView.getHeaderView(0).findViewById(R.id.houseText);
        nameText.setText(user.getUsername());
        houseText.setText(user.getHouse().replace("\"", ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.me, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logOut(MenuItem item) {
        sharedPref.edit().clear().commit();
        Intent intent = new Intent(MeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}