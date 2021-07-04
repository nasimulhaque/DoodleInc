package net.bugfixers.doodleinc.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.bugfixers.doodleinc.R;
import net.bugfixers.doodleinc.adapters.CategoryAdapter;
import net.bugfixers.doodleinc.model.Category;
import net.bugfixers.doodleinc.util.Constants;
import net.bugfixers.doodleinc.util.MySingleton;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private static final String TAG = "Category";

    private CategoryAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        adapter = new CategoryAdapter(this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (Constants.categories != null) {
            recyclerView.setAdapter(adapter);
        }

        TextView clearAll = findViewById(R.id.clear);
        clearAll.setOnClickListener(v -> {
            Constants.categories.forEach((i) -> {
                i.setSelected(false);
                i.getSubcatg().forEach((i2) -> {
                    i2.setSelected(false);
                });
            });

            adapter.notifyDataSetChanged();
        });

        if (Constants.categories == null) {
            Log.d(TAG, "Fetching");
            fetch();
        }
    }

    private void fetch() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                Constants.URL,
                null,
                response -> {
                    try {
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<Category>>(){}.getType();
                        Constants.categories = gson.fromJson(response.getJSONArray("categories").toString(), type);
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                e -> {
                    NetworkResponse networkResponse = e.networkResponse;
                    if (networkResponse != null && networkResponse.data != null) {
                        String jsonError = new String(networkResponse.data);
                        Gson gson = new Gson();
                        Error error = gson.fromJson(jsonError, Error.class);
                        Log.d(TAG, jsonError);
                        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        jsonObjectRequest.setTag(TAG);

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public void update() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop () {
        super.onStop();
        RequestQueue requestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }
}