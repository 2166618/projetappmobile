package com.example.pourunmondeeveille.ui.recherche;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pourunmondeeveille.databinding.FragmentRechercheBinding;

public class RechercheFragment extends Fragment {

    private FragmentRechercheBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RechercheViewModel rechercheViewModel =
                new ViewModelProvider(this).get(RechercheViewModel.class);

        binding = FragmentRechercheBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRecherche;
        rechercheViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}