package net.bugfixers.doodleinc.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.bugfixers.doodleinc.R;
import net.bugfixers.doodleinc.activities.CategoryActivity;
import net.bugfixers.doodleinc.util.Constants;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.ViewHolder> {

    private static final String TAG = "SubCategory";
    private final Context context;
    private final int index;

    public SubcategoryAdapter(Context context, int index) {
        this.context = context;
        this.index = index;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_subcategory, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoryAdapter.ViewHolder holder, int position) {
        Constants.categories.get(index).getSubcatg().get(position);
        Log.d(TAG, Constants.categories.get(index).getSubcatg().get(position).getSub_category_name());
        holder.text.setText(Constants.categories.get(index).getSubcatg().get(position).getSub_category_name());

        if (Constants.categories.get(index).getSubcatg().get(position).isSelected()) {
            holder.imageAdd.setImageResource(R.drawable.ic_baseline_done_24);
        } else {
            holder.imageAdd.setImageResource(R.drawable.ic_baseline_add_24);
        }

        holder.imageAdd.setOnClickListener(v -> {
            if (Constants.categories.get(index).getSubcatg().get(position).isSelected()) {
                Constants.categories.get(index).getSubcatg().get(position).setSelected(false);
                holder.imageAdd.setImageResource(R.drawable.ic_baseline_add_24);
                Constants.categories.get(index).setSelected(false);
                ((CategoryActivity) context).update();
            } else {
                Constants.categories.get(index).getSubcatg().get(position).setSelected(true);
                holder.imageAdd.setImageResource(R.drawable.ic_baseline_done_24);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Constants.categories.get(index).getSubcatg().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView text;
        private final ImageView imageAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            imageAdd = itemView.findViewById(R.id.image_add);
        }
    }
}
