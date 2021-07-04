package net.bugfixers.doodleinc.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.bugfixers.doodleinc.R;
import net.bugfixers.doodleinc.adapters.MainAdapter;
import net.bugfixers.doodleinc.util.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textTitle = findViewById(R.id.text_title);

        Button buttonBrowseCategory = findViewById(R.id.button_browse_category);
        buttonBrowseCategory.setOnClickListener(v -> startActivity(new Intent(this, CategoryActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<String> list = new ArrayList<>();

        textTitle.setVisibility(View.GONE);
        if (Constants.categories != null && Constants.categories.size() != 0) {

            Constants.categories.forEach((i) -> {
                if (i.isSelected()) {
                    list.add(i.getCategory_name());
                }
                i.getSubcatg().forEach((i2) -> {
                    if (i2.isSelected()) {
                        list.add(i2.getSub_category_name());
                    }
                });
            });
            if (list.size() > 0) {
                textTitle.setVisibility(View.VISIBLE);
            }
            MainAdapter adapter = new MainAdapter(this, list);
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
        }
    }
}