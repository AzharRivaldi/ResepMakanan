package com.azhar.resepmakanan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.resepmakanan.R;
import com.azhar.resepmakanan.model.ModelFilter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class FilterFoodAdapter extends RecyclerView.Adapter<FilterFoodAdapter.ViewHolder> {

    private List<ModelFilter> items;
    private FilterFoodAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelFilter modelMain);
    }

    public FilterFoodAdapter(Context context, List<ModelFilter> items, FilterFoodAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_filter_food, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelFilter data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.strMealThumb)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgThumb);

        holder.tvMeal.setText(data.strMeal);
        holder.cvFilterMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvMeal;
        public CardView cvFilterMeal;
        public ImageView imgThumb;

        public ViewHolder(View itemView) {
            super(itemView);
            cvFilterMeal = itemView.findViewById(R.id.cvFilterMeal);
            tvMeal = itemView.findViewById(R.id.tvMeal);
            imgThumb = itemView.findViewById(R.id.imgThumb);
        }
    }

}
