package net.bugfixers.doodleinc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.bugfixers.doodleinc.R;
import net.bugfixers.doodleinc.util.Constants;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final Context context;

    public CategoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        SubcategoryAdapter adapter = new SubcategoryAdapter(context, position);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(adapter);

        if (Constants.categories.get(position).isExpanded()) {
            holder.recyclerView.setVisibility(View.VISIBLE);
            holder.imageArrow.setRotation(90);
        } else {
            holder.recyclerView.setVisibility(View.GONE);
            holder.imageArrow.setRotation(0);
        }

        holder.imageArrow.setOnClickListener(v -> {
            if (Constants.categories.get(position).isExpanded()) {
                holder.recyclerView.setVisibility(View.GONE);
                Constants.categories.get(position).setExpanded(false);
                holder.imageArrow.setRotation(0);
            } else {
                holder.recyclerView.setVisibility(View.VISIBLE);
                Constants.categories.get(position).setExpanded(true);
                holder.imageArrow.setRotation(90);
            }
        });

        holder.text.setText(Constants.categories.get(position).getCategory_name());

        if (Constants.categories.get(position).isSelected()) {
            holder.imageAdd.setImageResource(R.drawable.ic_baseline_done_24);
        } else {
            holder.imageAdd.setImageResource(R.drawable.ic_baseline_add_24);
        }

        holder.imageAdd.setOnClickListener(v -> {
            if (Constants.categories.get(position).isSelected()) {
                Constants.categories.get(position).getSubcatg().forEach((i) -> i.setSelected(false));
                adapter.notifyDataSetChanged();
                Constants.categories.get(position).setSelected(false);
                holder.imageAdd.setImageResource(R.drawable.ic_baseline_add_24);
            } else {
                Constants.categories.get(position).getSubcatg().forEach((i) -> i.setSelected(true));
                adapter.notifyDataSetChanged();
                Constants.categories.get(position).setSelected(true);
                holder.imageAdd.setImageResource(R.drawable.ic_baseline_done_24);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Constants.categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageArrow;
        private final TextView text;
        private final ImageView imageAdd;
        private final RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageArrow = itemView.findViewById(R.id.image_arrow);
            text = itemView.findViewById(R.id.text);
            imageAdd = itemView.findViewById(R.id.image_add);
            recyclerView = itemView.findViewById(R.id.recyclerview);
        }
    }
}
