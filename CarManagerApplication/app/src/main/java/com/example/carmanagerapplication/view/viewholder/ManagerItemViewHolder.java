package com.example.carmanagerapplication.view.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carmanagerapplication.databinding.ItemsListBinding;
import com.example.carmanagerapplication.model.ManagerDataModel;
import com.example.carmanagerapplication.view.adapter.listener.ItemClickListener;
import com.example.carmanagerapplication.view.viewholder.listener.BindClickListener;

public class ManagerItemViewHolder extends RecyclerView.ViewHolder implements BindClickListener {

    private ItemsListBinding binding;
    public ManagerItemViewHolder(ItemsListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ManagerDataModel data) {
        binding.setData(data);
    }

    @Override
    public void bindEvents(ItemClickListener listener) {
        binding.setItemClickListener(listener);
    }
}
