package com.example.smartrefresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefreshAdapter extends RecyclerView.Adapter<RefreshAdapter.ViewHolder> {

    private Context context;
    public List<String> strList;
    private RefreshInterface refreshInterface;

    public RefreshAdapter(Context context, List<String> strList, RefreshInterface refreshInterface) {
        this.context = context;
        this.strList = strList;
        this.refreshInterface = refreshInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_refresh, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.group.setOnClickListener(v -> refreshInterface.Refresh(i));
        viewHolder.tvItem.setText(strList.get(i));
    }

    @Override
    public int getItemCount() {
        return strList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.group)
        LinearLayout group;
        @BindView(R.id.tv_item)
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //加载数据
    public void loadMore(List<String> strings) {
        strList.addAll(strings);
        notifyDataSetChanged();
    }

    //刷新数据
    public void refreshData(List<String> strings) {
        strList.addAll(0, strings);
        notifyDataSetChanged();
    }

    //点击的回掉，将点击的下标返回
    public interface RefreshInterface{
        void Refresh(int ints);
    }
}
