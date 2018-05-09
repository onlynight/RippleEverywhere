package com.github.onlynight.rippleverywhere.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.onlynight.rippleeverywhere.library.RippleLayout;
import com.github.onlynight.rippleverywhere.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerDemoAdapter extends RecyclerView.Adapter<RecyclerDemoAdapter.ViewHolder> {

    private List<Integer> images = new ArrayList<>();

    public RecyclerDemoAdapter() {
        fakeData();
    }

    private void fakeData() {
        images.add(R.drawable.ic_dark_soul_8);
        images.add(R.drawable.ic_dark_soul_1);
        images.add(R.drawable.ic_dark_soul_2);
        images.add(R.drawable.ic_dark_soul_3);
        images.add(R.drawable.ic_dark_soul_4);
        images.add(R.drawable.ic_dark_soul_5);
        images.add(R.drawable.ic_dark_soul_6);
        images.add(R.drawable.ic_dark_soul_7);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        RippleLayout rippleLayout;
        ImageView imgContent;

        ViewHolder(View itemView) {
            super(itemView);
            rippleLayout = (RippleLayout) itemView.findViewById(R.id.ripple_layout);
            imgContent = (ImageView) itemView.findViewById(R.id.img_content);
        }

        void bindData(int drawableId) {
            imgContent.setImageResource(drawableId);
            rippleLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rippleLayout.getRadiusAnimator().start();
                }
            });
        }

    }
}
