package com.sena.itunes.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sena.itunes.R;
import com.sena.itunes.databinding.ListItemBinding;
import com.sena.itunes.model.ResultContent;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by Sena KILIÇ on 1/12/2022.
 */

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<ResultContent.ItemContent> resultContent;

    private OnItemClickListener mOnItemClickListener;

    public ItemViewAdapter(@NonNull Context context, @NonNull ResultContent resultContent1) {
        mLayoutInflater = LayoutInflater.from(context);
        resultContent = resultContent1.getItemContents();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ListItemBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.list_item, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        ResultContent.ItemContent itemContent = resultContent.get(position);
        holder.bind(itemContent, mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        if (resultContent != null) {
            return resultContent.size();
        } else {
            return 0;
        }
    }

    public void setMenuItems(ResultContent resultContent) {
        this.resultContent = resultContent.getItemContents();
        notifyDataSetChanged();
    }

    public ResultContent.ItemContent getMenuItemPosition(int position) {
        return resultContent.get(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding mBinding;

        public ItemViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bind(ResultContent.ItemContent item, OnItemClickListener onItemClickListener) {
            mBinding.setItem(item);
            mBinding.executePendingBindings();
            itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, ResultContent.ItemContent item);
    }
}