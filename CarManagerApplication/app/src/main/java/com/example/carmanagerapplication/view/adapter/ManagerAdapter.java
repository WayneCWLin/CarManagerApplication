package com.example.carmanagerapplication.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.carmanagerapplication.R;
import com.example.carmanagerapplication.databinding.ItemsListBinding;
import com.example.carmanagerapplication.model.ManagerDataModel;
import com.example.carmanagerapplication.view.adapter.listener.ItemClickListener;
import com.example.carmanagerapplication.view.viewholder.ManagerItemViewHolder;

public class ManagerAdapter extends ListAdapter<ManagerDataModel, ManagerItemViewHolder> {
    public static final DiffUtil.ItemCallback<ManagerDataModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<ManagerDataModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ManagerDataModel oldItem, @NonNull ManagerDataModel newItem) {
            return oldItem.managerName.equals(newItem.managerName);
        }

        @Override
        public boolean areContentsTheSame(@NonNull ManagerDataModel oldItem, @NonNull ManagerDataModel newItem) {
            return oldItem.managerName.equals(newItem.managerName);
        }
    };

    public ManagerAdapter() {
        super(DIFF_CALLBACK);
    }
    private ItemClickListener itemClickListener;
    public void setItemClickListener(ItemClickListener listener){
        this.itemClickListener = listener;
    }
    @NonNull
    @Override
    public ManagerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.items_list, parent, false);
        return new ManagerItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerItemViewHolder holder, int position) {
        holder.bind(getItem(position));
        holder.bindEvents(itemClickListener);
    }
}
