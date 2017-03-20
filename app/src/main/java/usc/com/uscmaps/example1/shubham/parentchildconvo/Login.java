package usc.com.uscmaps.example1.shubham.parentchildconvo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shubham on 3/8/17.
 * Login has the initial SharedPref set, so that it is not called again and again inside the MainActivity.
 *
 */
public class Login extends AppCompatActivity{
    private String TAG = getClass().getSimpleName();
    SharedPreferences sharedPref;
    String jsonDataFirstChild;
    Gson gson;
    Type type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "Inside Login");


        //TODO: Put this code snippet in Login Activity
        String serverData = "{'login_success':'yes','user_data':{'name': 'Shubham Gupta','email':'shubham.gupta@gmail.com','phone':'7838390604','image_url':'https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAd_AAAAJDVkOTA1NzBmLTYxMTItNDM2Yy1iZTQ5LTQzNzJhNDI2NDM1NQ.jpg','children':[{'id':'1','name':'Samarth Garg','image_url':'https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAa2AAAAJGIxOTgxZGRiLTU4NzctNGNiYy05MmYwLWE5NzU1ZTI4ZGI4Ng.jpg'},{'id':'2','name':'Manan Bhutani','image_url':'https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAK3AAAAJDkxYzVhNTE4LTBlNTgtNDAxNC1iOTRlLWY0ZDA5YTQ1YzE3Ng.jpg'}]}}";
        try {
            sharedPref = this.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            JSONObject obj = new JSONObject(serverData);
            JSONObject user_data = obj.getJSONObject("user_data");

//            Log.e("My App", user_data.getString("name"));
//            editor.putString("curr_user_name", user_data.getString("name"));
//            editor.putString("curr_user_email", user_data.getString("email"));
//            editor.putString("curr_user_phone", user_data.getString("phone"));
//            editor.putString("curr_user_display_pic", user_data.getString("image_url"));

            JSONArray children = user_data.getJSONArray("children");

            List<ChildrenGSON> list = new ArrayList<ChildrenGSON>();
            List<ChildrenGSON> list_for_first_child = new ArrayList<ChildrenGSON>();

            for(int i = 0; i < children.length();i++){
                JSONObject children1 = children.getJSONObject(i);
//                Log.e("curr_user_children_"+i, ""+children1.getString("id"));
//                Log.e("curr_user_children_"+i, ""+children1.getString("name"));
//                Log.e("curr_user_children_"+i, ""+children1.getString("image_url"));

                if(i==1){
                    list_for_first_child.add(new ChildrenGSON(Integer.parseInt(children1.getString("id")), children1.getString("name"),
                            children1.getString("image_url")));
                }
                list.add(new ChildrenGSON(Integer.parseInt(children1.getString("id")), children1.getString("name"),
                        children1.getString("image_url")));
            }
            gson = new Gson();
            type = new TypeToken<List<ChildrenGSON>>() {}.getType();
            String jsonData = gson.toJson(list, type);
            jsonDataFirstChild = gson.toJson(list_for_first_child, type);
//            Log.e("Added Children Data", ""+jsonData);
            editor.putString("children", jsonData);
            editor.putString("curr_active_child", jsonDataFirstChild);

//            To get back the json from String converted Gson.
//            List<ChildrenGSON> fromJson = gson.fromJson(jsonData, type);
//            for (ChildrenGSON task : fromJson) {
//                Log.e("fefefe", ""+task);
//            }
//            Log.e(TAG, sharedPref.getString("curr_user_name", "no string"));


            editor.commit();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (Exception t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + serverData + "\"");
        }

    }


}
