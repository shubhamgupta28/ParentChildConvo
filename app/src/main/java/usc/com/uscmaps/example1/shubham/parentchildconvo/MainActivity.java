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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean viewIsAtHome;
    private  String TAG = getClass().getSimpleName();
    SharedPreferences sharedPref;

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

        //TODO: Put this code snippet in Login Activity

        String serverData = "{'login_success':'yes','user_data':{'name': 'Shubham Gupta','email':'shubham.gupta@gmail.com','phone':'7838390604','image_url':'https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAd_AAAAJDVkOTA1NzBmLTYxMTItNDM2Yy1iZTQ5LTQzNzJhNDI2NDM1NQ.jpg','children':[{'id':'1','name':'Samarth Garg','image_url':'https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAa2AAAAJGIxOTgxZGRiLTU4NzctNGNiYy05MmYwLWE5NzU1ZTI4ZGI4Ng.jpg'},{'id':'2','name':'Manan Bhutani','image_url':'https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAK3AAAAJDkxYzVhNTE4LTBlNTgtNDAxNC1iOTRlLWY0ZDA5YTQ1YzE3Ng.jpg'}]}}";
        try {
            sharedPref = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPref.edit();

            JSONObject obj = new JSONObject(serverData);

            JSONObject user_data = obj.getJSONObject("user_data");

//            Log.e("My App", user_data.getString("name"));
            editor.putString("curr_user_name", user_data.getString("name"));
//            editor.putString("curr_user_email", user_data.getString("email"));
//            editor.putString("curr_user_phone", user_data.getString("phone"));
//            editor.putString("curr_user_display_pic", user_data.getString("image_url"));

            JSONArray children = user_data.getJSONArray("children");

            List<ChildrenGSON> list = new ArrayList<ChildrenGSON>();

            for(int i = 0; i < children.length();i++){
                JSONObject children1 = children.getJSONObject(i);
//                Log.e("curr_user_children_"+i, ""+children1.getString("id"));
//                Log.e("curr_user_children_"+i, ""+children1.getString("name"));
//                Log.e("curr_user_children_"+i, ""+children1.getString("image_url"));
                list.add(new ChildrenGSON(Integer.parseInt(children1.getString("id")), children1.getString("name"),
                        children1.getString("image_url")));
            }
            Gson gson = new Gson();
            Type type = new TypeToken<List<ChildrenGSON>>() {}.getType();
            String jsonData = gson.toJson(list, type);
            Log.e("Added Children Data", jsonData);
            editor.putString("children", jsonData);

//            To get back the json from String converted Gson.
//            List<ChildrenGSON> fromJson = gson.fromJson(jsonData, type);
//            for (ChildrenGSON task : fromJson) {
//                Log.e("fefefe", ""+task);
//            }
//            Log.e(TAG, sharedPref.getString("curr_user_name", "no string"));

            editor.commit();
        } catch (Exception t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + serverData + "\"");
        }
        displayView(R.id.home);
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
     * @return
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
        String highScore = sharedPref.getString("Child", "nothin");
        Log.e("SharedPref", highScore);

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
                Intent intent = new Intent(this, SelectChildActivity.class);

                startActivity(intent);
//                fragment = new ApplyForLeaveFragment();
                title = "Apply for Leave";
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
