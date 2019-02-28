package com.example.asus.chenshaoshuai20190226.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.chenshaoshuai20190226.R;
import com.example.asus.chenshaoshuai20190226.entity.HotMovieBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    private Context mContext;
    private List<HotMovieBean.ResultBean> mData;

    public RecyclerviewAdapter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view = LayoutInflater.from(mContext).inflate(R.layout.item,viewGroup,false);
      ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
      viewHolder.image.setImageURI(Uri.parse(mData.get(i).getImageUrl()));
      viewHolder.price.setText(mData.get(i).getName());
      viewHolder.title.setText(mData.get(i).getSummary());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<HotMovieBean.ResultBean> result) {
        this.mData = result;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView image;
        private TextView title;
        private TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);

        }
    }
}
