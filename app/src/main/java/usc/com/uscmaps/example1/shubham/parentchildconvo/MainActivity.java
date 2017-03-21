package usc.com.uscmaps.example1.shubham.parentchildconvo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean viewIsAtHome;
    private  String TAG = getClass().getSimpleName();
    SharedPreferences sharedPref;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    View headerView;
    Gson gson;
    Type type;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "Inside MainActivity");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Calling Header", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), SelectChildActivity.class);
                startActivity(intent);
            }
        });

        showActiveChild();
        displayView(R.id.home);
    }

    /**
     * Setting the child from the SharedPref to be the current child after the
     * user selects a child from the list
     */
    private void showActiveChild() {
        Log.e("showActiveChild", "Inside");

        navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerView = navigationView.getHeaderView(0);


        sharedPref = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String curr_active = sharedPref.getString("curr_active_child", null);
        Log.e("showActiveChild: ", ""+curr_active);
        List<ChildrenGSON> set_child_json = new ArrayList<ChildrenGSON>();
        gson = new Gson();
        type = new TypeToken<List<ChildrenGSON>>() {}.getType();
        set_child_json = gson.fromJson(curr_active, type);

        for (ChildrenGSON task : set_child_json) {
            TextView curr_child_name = (TextView) headerView.findViewById(R.id.curr_child_name);
            ImageView curr_child_image = (ImageView) headerView.findViewById(R.id.curr_child_image);
            curr_child_name.setText(task.getName());
            Glide.with(context)
                    .load(task.getImage_url())
                    .placeholder(R.mipmap.child)
                    .into(curr_child_image);
        }
    }

    /**
     * Changed the default onBackPressed to this one to enable the navigation to the home
     * after the user presses the Back button
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!viewIsAtHome) { //if the current view is not the News fragment
            displayView(R.id.home); //display the News fragment
        } else {
            moveTaskToBack(true);  //If view is in News fragment, exit application
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Basic Working: @String/link1
     * @param item
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayView(item.getItemId());
        return true;
//        int id = item.getItemId();
//        Fragment fragment = null;
//        String title = getString(R.string.app_name);
//
//
//        if (id == R.id.home) {
//
//        } else if (id == R.id.messages) {
//
//        } else if (id == R.id.calendar) {
//
//        } else if (id == R.id.apply_for_leave) {
//            fragment = new ApplyForLeaveFragment();
//            title  = "Apply For Leave";
//
//        } else if (id == R.id.gallery) {
//
//        } else if (id == R.id.feedback) {
//
//        } else if (id == R.id.my_account) {
//
//        }
//
//        if (fragment != null) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.content_frame, fragment);
//            ft.commit();
//        }
//
//        // set the toolbar title
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle(title);
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
//        String highScore = sharedPref.getString("Child", "nothin");
//        Log.e("SharedPref", highScore);

        switch (viewId) {
            case R.id.home:
                fragment = new HomeFragment();
                title  = "Home";
                viewIsAtHome = true;
                break;
            case R.id.apply_for_leave:
                fragment = new ApplyForLeaveFragment();
                title = "Apply for Leave";
                viewIsAtHome = false;
                break;
            case R.id.calendar:
//                fragment = new
                title = "Apply for Leave";
                viewIsAtHome = false;
                break;
            case R.id.messages:
                fragment = new MessageFragment();
                title = "Message";
                viewIsAtHome = false;
                break;
            case R.id.my_account:
                fragment = new MyAccountFragment();
                title = "My Account";
                viewIsAtHome = false;
                break;
            case R.id.gallery:
                fragment = new GalleryFragment();
                title = "Gallery";
                viewIsAtHome = false;
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }
}
