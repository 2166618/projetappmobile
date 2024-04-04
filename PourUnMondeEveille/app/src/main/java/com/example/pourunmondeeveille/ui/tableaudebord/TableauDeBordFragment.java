package com.example.pourunmondeeveille.ui.tableaudebord;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pourunmondeeveille.databinding.FragmentTableauDeBordBinding;

public class TableauDeBordFragment extends Fragment {

    private FragmentTableauDeBordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TableauDeBordViewModel tableauDeBordViewModel =
                new ViewModelProvider(this).get(TableauDeBordViewModel.class);

        binding = FragmentTableauDeBordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTableauDeBord;
        tableauDeBordViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}