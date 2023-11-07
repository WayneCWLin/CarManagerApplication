package com.example.carmanagerapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.ViewHolder> {
    private ArrayList<ManagerDataModel> manList;

    public ManagerAdapter(ArrayList<ManagerDataModel> manList) {
        this.manList = manList;
    }

    // This method creates a new ViewHolder object for each item in the RecyclerView
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item and return a new ViewHolder object
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list, parent, false);
        return new ViewHolder(itemView);
    }

    // This method returns the total
    // number of items in the data set
    @Override
    public int getItemCount() {
        return manList.size();
    }

    // This method binds the data to the ViewHolder object
    // for each item in the RecyclerView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ManagerDataModel currentEmp = manList.get(position);
//        holder.name.setText(currentEmp.getName());
    }

    // This class defines the ViewHolder object for each item in the RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
        }
    }
}
