package usc.com.uscmaps.example1.shubham.parentchildconvo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SelectChildActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    /**
     * Reference @string/link2
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_child);

        ArrayList<String> list = new ArrayList<String>();

//        list.add("Shubham");
//        list.add("Kundal");
        sharedPref = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);

        String jsonData = sharedPref.getString("children", "a");

        Gson gson = new Gson();
        Type type = new TypeToken<List<ChildrenGSON>>() {}.getType();

        List<ChildrenGSON> fromJson = gson.fromJson(jsonData, type);
        for (ChildrenGSON task : fromJson) {
            list.add(task.getName());
        }
        ArrayAdapter<String> childAdapter = new ArrayAdapter<String>(this, R.layout.child_select_item_list,
                 list);

//        for(int i=0 ; i<childAdapter.getCount() ; i++){
//            Log.e("dds", ""+childAdapter.getItem(i));
//        }

        ListView listView = (ListView) findViewById(R.id.select_child_listview);
        listView.setAdapter(childAdapter);
    }
}
