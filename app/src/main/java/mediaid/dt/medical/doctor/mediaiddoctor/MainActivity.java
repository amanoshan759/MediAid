package mediaid.dt.medical.doctor.mediaiddoctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mediaid.dt.medical.doctor.mediaiddoctor.fragment.Appointments;
import mediaid.dt.medical.doctor.mediaiddoctor.fragment.ManageProfile;
import mediaid.dt.medical.doctor.mediaiddoctor.fragment.Reports;
import mediaid.dt.medical.doctor.mediaiddoctor.util.SpUtility;
import mediaid.dt.medical.doctor.mediaiddoctor.util.Utility;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtUsername, txtEmail;
    private Toolbar toolbar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/FaktSoftPro-Blond.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headernav = navigationView.inflateHeaderView(R.layout.nav_header_main);
        txtUsername = (TextView) headernav.findViewById(R.id.txtUsername);
        txtEmail = (TextView) headernav.findViewById(R.id.txtEmail);
        imageView = (ImageView) headernav.findViewById(R.id.imageView);
        SharedPreferences sp = SpUtility.getSharedPreference(getApplicationContext());
        SharedPreferences.Editor e = sp.edit();
        e.putBoolean(SpUtility.KEY_FLAG_LOGIN, true);
        e.commit();
        txtUsername.setText(sp.getString(SpUtility.KEY_DOCTOR_USERNAME, SpUtility.DEFAULT_VALUE_STRING));
        txtEmail.setText(sp.getString(SpUtility.KEY_DOCTOR_EMAIL, SpUtility.DEFAULT_VALUE_STRING));
        Appointments appointments = new Appointments();
        FragmentManager fragmentManager = getSupportFragmentManager();
        toolbar.setTitle("Appointments");
        fragmentManager.beginTransaction().replace(R.id.fragment_replace, appointments).commit();
//        String s=sp.getString("image",null);
//        Bitmap bm=Utility.StringToBitMap(s);
//        imageView.setImageBitmap(bm);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ChangePassword) {
            SharedPreferences sp = SpUtility.getSharedPreference(getApplicationContext());
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean(SpUtility.KEY_CHANGE_PASSWORD, true);
            e.commit();
            Utility.startIntent(MainActivity.this, ChangePassword.class);
            return true;
        } else if (id == R.id.Share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hey, download this app!");
            startActivity(shareIntent);
            return true;
        } else if (id == R.id.AboutUs) {
            Utility.startIntent(MainActivity.this, WebViewActivity.class);
            return true;
        } else if (id == R.id.Feedback) {
            Utility.startIntent(MainActivity.this, FeedBack.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Appointment) {
            Appointments appointments = new Appointments();
            FragmentManager fragmentManager = getSupportFragmentManager();
            toolbar.setTitle("Appointments");
            fragmentManager.beginTransaction().replace(R.id.fragment_replace, appointments).commit();
        } else if (id == R.id.Manageprofile) {
            ManageProfile manageProfile = new ManageProfile();
            FragmentManager fragmentManager = getSupportFragmentManager();
            toolbar.setTitle("Manage Profile");
            fragmentManager.beginTransaction().replace(R.id.fragment_replace, manageProfile).commit();
        } else if (id == R.id.Reports) {
            Reports reports = new Reports();
            FragmentManager fragmentManager = getSupportFragmentManager();
            toolbar.setTitle("Reports");
            fragmentManager.beginTransaction().replace(R.id.fragment_replace, reports).commit();
        } else if (id == R.id.logout) {
            DialogInterface.OnClickListener dialogclicklistener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which)

                    {
                        case DialogInterface.BUTTON_POSITIVE: {
                            SpUtility.clearSharedPreference(MainActivity.this);
                            Utility.startIntent(MainActivity.this, LoginPage.class);
                            MainActivity.this.finish();
                            break;
                        }
                        case DialogInterface.BUTTON_NEGATIVE: {
                            break;
                        }
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure you want to logout ?");
            builder.setPositiveButton("OK", dialogclicklistener);
            builder.setNegativeButton("Cancel", dialogclicklistener);
            builder.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
