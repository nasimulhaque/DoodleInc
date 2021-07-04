package net.bugfixers.doodleinc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.bugfixers.doodleinc.R;
import net.bugfixers.doodleinc.activities.MainActivity;
import net.bugfixers.doodleinc.model.SelectedItem;
import net.bugfixers.doodleinc.util.Constants;

import java.util.ArrayList;

public class SelectedItemAdapter extends RecyclerView.Adapter<SelectedItemAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<SelectedItem> list;

    public SelectedItemAdapter(Context context, ArrayList<SelectedItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedItemAdapter.ViewHolder holder, int position) {
        holder.text.setText(list.get(position).getName());
        holder.imageClose.setOnClickListener(v -> {
            System.out.println("Category: " + list.get(position).getCategory() + ", Subcategory: " + list.get(position).getSubcategory());
            if (list.get(position).getSubcategory() == -1) {
                Constants.categories.get(list.get(position).getCategory()).setSelected(false);
            } else {
                Constants.categories.get(list.get(position).getCategory()).getSubcatg().get(list.get(position).getSubcategory()).setSelected(false);
            }
            ((MainActivity) context).updateData();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageClose;
        private final TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageClose = itemView.findViewById(R.id.image_close);
            text = itemView.findViewById(R.id.text);
        }
    }
}
