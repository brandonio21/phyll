package com.brandonio21.phyll.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brandonio21.chloromedia.Data.ProviderItem;
import com.brandonio21.phyll.R;

import java.util.List;

public class ProviderItemAdapter extends RecyclerView.Adapter<ProviderItemAdapter.ViewHolder> {
    List<ProviderItem> providerItemDataset;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View itemCard = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.card_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemCard);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView poopText = (TextView) holder.view.findViewById(R.id.item_textview);
        ImageView poopImage = (ImageView) holder.view.findViewById(R.id.item_imageview);
        poopText.setText(this.providerItemDataset.get(position).getName());
        poopImage.setImageBitmap(this.providerItemDataset.get(position).getPreviewImage(holder.view.getContext()));
    }

    @Override
    public int getItemCount() {
        return this.providerItemDataset.size();
    }

    public ProviderItemAdapter(List<ProviderItem> dataset) {
        this.providerItemDataset = dataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

}
