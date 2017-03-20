package usc.com.uscmaps.example1.shubham.parentchildconvo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SelectChildActivity extends AppCompatActivity  {
    private String TAG = getClass().getSimpleName();
    SharedPreferences sharedPref;

    Context mtx;
    ArrayList<ChildrenGSON> tempList = new ArrayList<ChildrenGSON>();
    NavigationView navigationView;
    View headerView;


    /**
     * Reference @string/link2
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_child);
        mtx = this;
        ArrayList<String> list = new ArrayList<>();
        sharedPref = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

        String jsonData = sharedPref.getString("children", null);
        final Gson gson = new Gson();
        final Type type = new TypeToken<List<ChildrenGSON>>() {}.getType();

        try{
            final List<ChildrenGSON> fromJson = gson.fromJson(jsonData, type);
            for (ChildrenGSON task : fromJson) {
                list.add(task.getName());
            }
            ArrayAdapter<String> childAdapter = new ArrayAdapter<>(this, R.layout.child_select_item_list,
                    list);

            ListView listView = (ListView) findViewById(R.id.select_child_listview);
            listView.setAdapter(childAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = ((TextView)view).getText().toString();
                    SharedPreferences.Editor editor = sharedPref.edit();

                    for (ChildrenGSON task : fromJson) {
                        if(item == task.getName()){
                            ArrayList<ChildrenGSON> tempList = new ArrayList<ChildrenGSON>();
                            tempList.add(task);

                            Gson gson = new Gson();
                            Type type = new TypeToken<List<ChildrenGSON>>() {}.getType();
                            String jsonData = gson.toJson(tempList, type);
                            editor.putString("curr_active_child", jsonData);
                            editor.commit();
                            Log.e(TAG, jsonData);
                        }
                    }
                    Intent intent = new Intent(mtx, MainActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception e){
            Log.e(TAG, "In catch, Null SharedPref");
        }
    }
}
