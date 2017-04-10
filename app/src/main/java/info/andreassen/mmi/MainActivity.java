package info.andreassen.mmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static Competition currentCompetition = new Competition("Trondheim - Oslo", 1130000, 3.0f, 50);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        updateCompetition();
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
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_compo) {

        } else if (id == R.id.nav_progression) {

        } else if (id == R.id.nav_register_steps) {
            Intent intent = new Intent(this, RegisterStepsActivity.class);
            startActivityForResult(intent, 1);
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(data.getStringExtra("status")) {
            case "competitionUpdated":
                updateCompetition();
                break;
        }
    }

    private void updateCompetition() {
        int walkedSteps = currentCompetition.getWalkedSteps();
        int totalSteps = currentCompetition.getTotalSteps();
        ((TextView) findViewById(R.id.cur_compo_name)).setText(currentCompetition.getName());
        ((TextView) findViewById(R.id.cur_compo_steps)).setText(String.format(Locale.GERMANY, "%,d/%,d skritt", walkedSteps, totalSteps));
        ((TextView) findViewById(R.id.cur_compo_days_left)).setText(currentCompetition.getDurationDays() + "d");
        ((TextView) findViewById(R.id.steps_today)).setText(Integer.toString(walkedSteps));
        ((TextView) findViewById(R.id.avg_steps)).setText(Integer.toString(walkedSteps));


        ((RatingBar) findViewById(R.id.cur_compo_difficulty)).setRating(currentCompetition.getDifficulty());

        ((ProgressBar) findViewById(R.id.cur_compo_progress)).setMax(totalSteps);
        ((ProgressBar) findViewById(R.id.cur_compo_progress)).setProgress(walkedSteps);

    }

    public static Competition getCurrentCompetition() {
        return currentCompetition;
    }
}
