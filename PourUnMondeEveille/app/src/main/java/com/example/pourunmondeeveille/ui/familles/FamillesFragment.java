package com.example.pourunmondeeveille.ui.familles;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.pourunmondeeveille.R;
import com.example.pourunmondeeveille.databinding.FragmentFamillesBinding;
import com.example.pourunmondeeveille.model.familles.FamilleAccueil;
import com.example.pourunmondeeveille.ui.creationcompte.CreationCompteFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FamillesFragment extends Fragment {

    private FragmentFamillesBinding binding;
    private ListView famillesList;
    private EditText barreDeRecherche;
    private List<String> nomsFamilles;
    private ArrayAdapter<String> adapter;

    private List<FamilleAccueil> clonedPostulants;

    public List<FamilleAccueil> getClonedPostulants() {
        return clonedPostulants;
    }

    public void setClonedPostulants(List<FamilleAccueil> clonedPostulants) {
        this.clonedPostulants = clonedPostulants;
    }

    public List<String> getNomsFamilles() {
        return nomsFamilles;
    }

    public void setNomsFamilles(List<String> nomsFamilles) {
        this.nomsFamilles = nomsFamilles;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FamillesViewModel famillesViewModel =
                new ViewModelProvider(this).get(FamillesViewModel.class);

        binding = FragmentFamillesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        famillesList = view.findViewById(R.id.famillesList);
        barreDeRecherche = view.findViewById(R.id.barreDeRecherche);

        FamillesViewModel viewModel = new ViewModelProvider(this).get(FamillesViewModel.class);

        viewModel.getFamillesAccueil().observe(getViewLifecycleOwner(), new Observer<List<FamilleAccueil>>() {
            @Override
            public void onChanged(List<FamilleAccueil> familles) {
                try {
                    getClonedPostulantsList(familles);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                List<String> nomsDeFamille = getNomsPostulantsList();
                setNomsFamilles(nomsDeFamille);
                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, nomsDeFamille);
                famillesList.setAdapter(adapter);
            }
        });

        viewModel.fetchFamillesAccueil();

        famillesList.setOnItemClickListener((AdapterView<?> parent, View v, int position, long id) -> {
            String nomFamilleSelectionnee = nomsFamilles.get(position);
            naviguerAuFragmentProfileFamille(nomFamilleSelectionnee);

        });

        barreDeRecherche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> clonedFamillesList =  getNomsPostulantsList();

                if (s.length() > 0) {
                    clonedFamillesList = clonedFamillesList.stream()
                            .filter(name -> name.toLowerCase().contains(s.toString().toLowerCase()))
                            .collect(Collectors.toList());
                }

                adapter.clear();
                adapter.addAll(clonedFamillesList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public List<String> getNomsPostulantsList() {
        List<String> nomsDesPostulants = new ArrayList<>();

        for (FamilleAccueil famille : getClonedPostulants()) {
            if (famille.getPostulant() != null) {
                String nomPostulant = famille.getPostulant().getNom();
                nomsDesPostulants.add(nomPostulant);
            }
        }

        return nomsDesPostulants;
    }

    public List<FamilleAccueil> getClonedPostulantsList(List<FamilleAccueil> familles) throws CloneNotSupportedException {
        List<FamilleAccueil> clonedPostulants = new ArrayList<>();

        for (FamilleAccueil famille : familles) {
            clonedPostulants.add((FamilleAccueil) famille.clone());
        }

        setClonedPostulants(clonedPostulants);

        return clonedPostulants;
    }

    private void naviguerAuFragmentProfileFamille(String nomDeFamille) {
        // Ajout des informations de la famille
        Bundle args = new Bundle();
        args.putString("nomDeFamille", nomDeFamille);

        // Navigation au fragment du profile de la famille
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.action_specific_to_profileFamilleFragment, args);
    }

}