package com.example.jsonparser1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
//    public String getStudentData(){
//        String jsonContact="{\n" +
//                "\t\"Student\":[{\"name\":\"Aamir\",\"number\":\" 111\"},\n" +
//                "\t\t{\"name\":\"xyz\",\"number\":\" 123\"},\n" +
//                "\t\t{\"name\":\"abc\",\"number\":\" 454\"}\n" +
//                "]\n" +
//                "\n" +
//                "\n" +
//                "}";
//        return jsonContact;
//    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("student.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
    ListView lvDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            ArrayList<HashMap<String,String>> userList=new ArrayList<>();
            ListView lstView = (ListView) findViewById(R.id.lvDisplay);
            JSONObject jobj=new JSONObject(loadJSONFromAsset());
            JSONArray jsonArry=jobj.getJSONArray("Student");
            for (int i=0;i<jsonArry.length();i++)
            {
                HashMap<String,String>user=new HashMap<>();
                JSONObject obj=jsonArry.getJSONObject(i);
                user.put("name",obj.getString("name"));
                user.put("number",obj.getString("number"));
                userList.add(user);
            }
            ListAdapter adapter=new SimpleAdapter(MainActivity.this,userList,R.layout.list_view,new String[]{"name","number"},new int[]{R.id.name,R.id.number});
            lstView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}