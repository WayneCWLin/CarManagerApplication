package com.example.carmanagerapplication.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carmanagerapplication.R;
import com.example.carmanagerapplication.databinding.FragmentManagerListBinding;
import com.example.carmanagerapplication.view.adapter.ManagerAdapter;
import com.example.carmanagerapplication.view.adapter.listener.ItemClickListener;
import com.example.carmanagerapplication.viewmodel.ManagerViewModel;

import java.util.ArrayList;

public class ManagerListFragment extends BaseFragment {

    public ManagerListFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static ManagerListFragment newInstance(String param1, String param2) {
        ManagerListFragment fragment = new ManagerListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ManagerViewModel.class);
    }
    private ManagerViewModel viewModel;
    private FragmentManagerListBinding binding;

    private ItemClickListener itemClickListener = data -> {
        NavHostFragment.findNavController(ManagerListFragment.this)
                .navigate(R.id.action_CarManagerFragment_to_CarManagerApiFragment);
    };

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        viewModel = new ViewModelProvider(this).get(ManagerViewModel.class);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_manager_list, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        viewModel.initViewModel();
    }

    @Override
    protected void onInitDataBinding() {
        binding.setManagerViewModel(viewModel);
    }

    private void initAdapter(){
        ManagerAdapter adapter = new ManagerAdapter();
        adapter.setItemClickListener(itemClickListener);
        binding.firstRecyclerview.setAdapter(adapter);
        viewModel.managerDataList.observe(getViewLifecycleOwner(), adapter::submitList);
    }

    @Override
    public void onDestroyView() {
        viewModel.managerDataList.removeObservers(getViewLifecycleOwner());
        binding.setLifecycleOwner(null);
        binding.unbind();
        binding = null;
        super.onDestroyView();
    }
}