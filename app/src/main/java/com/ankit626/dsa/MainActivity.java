package com.ankit626.dsa;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TextView t1;
    private Button btn;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<User> users;
    private ImageView rf,rf2;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    // Sir i have not set onclick on youutbe because whether you
                                                       // would like or not to create onclick listener i just have
                                                      // create another function and passes integer parameter to judge
                                                      // whether share button or youube aor anything else is clicked
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawid);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


       rf=(ImageView)findViewById(R.id.rf);
       rf2=(ImageView)findViewById(R.id.rf2);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent utube=new Intent(Intent.ACTION_VIEW, Uri.parse("https://courses.learncodeonline.in"));
                startActivity(utube);
            }
        });
        rf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent utube=new Intent(Intent.ACTION_VIEW, Uri.parse("https://courses.learncodeonline.in"));
                startActivity(utube);
            }
        });



        users=new ArrayList<>();



        requestQueue= Volley.newRequestQueue(this);
        jsonparse();
    }

    private void jsonparse(){
        final ArrayList<String> ab=new ArrayList<>();
          String url="https://learncodeonline.in/api/android/datastructure.json";
        final int image[]={
                R.drawable.d1,
                R.drawable.d2,
                R.drawable.d3,R.drawable.kl,R.drawable.d5,R.drawable.d6
        };

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray= null;
                try {
                    jsonArray = response.getJSONArray("questions");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject q=jsonArray.getJSONObject(i);
                        String question=q.getString("question");
                        String Answer=q.getString("Answer");
                        Integer asd=image[i];
                        User abc=new User(question,Answer,asd);
                        users.add(abc);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter=new adapter(users,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

    error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);   }
}
