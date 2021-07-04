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
import net.bugfixers.doodleinc.adapters.SelectedItemAdapter;
import net.bugfixers.doodleinc.model.SelectedItem;
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

    public void updateData() {
        ArrayList<SelectedItem> list = new ArrayList<>();
        textTitle.setVisibility(View.GONE);
        if (Constants.categories != null && Constants.categories.size() != 0) {
            for (int i = 0; i < Constants.categories.size(); i++) {
                if (Constants.categories.get(i).isSelected()) {
                    list.add(new SelectedItem(Constants.categories.get(i).getCategory_name(), i, -1));
                }
                for (int i1 = 0; i1 < Constants.categories.get(i).getSubcatg().size(); i1++) {
                    if (Constants.categories.get(i).getSubcatg().get(i1).isSelected()) {
                        list.add(new SelectedItem(Constants.categories.get(i).getSubcatg().get(i1).getSub_category_name(), i, i1));
                    }
                }
            }

            if (list.size() > 0) {
                textTitle.setVisibility(View.VISIBLE);
            }
            SelectedItemAdapter adapter = new SelectedItemAdapter(this, list);
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateData();
    }
}