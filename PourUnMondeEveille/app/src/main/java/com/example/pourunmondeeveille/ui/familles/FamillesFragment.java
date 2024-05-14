package com.example.pourunmondeeveille.ui.familles;

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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.pourunmondeeveille.R;
import com.example.pourunmondeeveille.databinding.FragmentFamillesBinding;
import com.example.pourunmondeeveille.model.familles.FamilleAccueil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FamillesFragment extends Fragment {
    private FamillesViewModel famillesViewModel;
    private FragmentFamillesBinding binding;
    private ListView famillesListView;
    private List<FamilleAccueil> clonedFamillesList;
    private ArrayAdapter<String> adapter;

    public List<FamilleAccueil> getClonedFamillesList() {
        return clonedFamillesList;
    }

    public void setClonedFamillesList(List<FamilleAccueil> clonedFamillesList) {
        this.clonedFamillesList = clonedFamillesList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        famillesViewModel = new ViewModelProvider(this).get(FamillesViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFamillesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        famillesListView = view.findViewById(R.id.famillesList);
        EditText barreDeRecherche = view.findViewById(R.id.barreDeRecherche);

        famillesViewModel.fetchFamillesAccueil();

        List<String> nomsDeFamille = getNomsDesFamilles();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, nomsDeFamille);

        famillesViewModel.getFamillesAccueilLiveData().observe(getViewLifecycleOwner(), new Observer<List<FamilleAccueil>>() {
            @Override
            public void onChanged(List<FamilleAccueil> familles) {
                List<String> nomsDeFamille = getNomsDesFamilles();
                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, nomsDeFamille);
                famillesListView.setAdapter(adapter);
            }
        });

        famillesListView.setOnItemClickListener((AdapterView<?> parent, View v, int position, long id) -> {
            String nomFamilleSelectionnee = getNomsDesFamilles().get(position);
            naviguerAuFragmentProfileFamille(nomFamilleSelectionnee);
        });

        barreDeRecherche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    getClonedPostulantsList();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
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

    private List<String> getNomsDesFamilles() {
        try {
            getClonedPostulantsList();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        List<String> nomsDeFamille = getNomsPostulantsList();
        return nomsDeFamille;
    }

    public List<String> getNomsPostulantsList() {
        List<String> nomsDesPostulants = new ArrayList<>();

        for (FamilleAccueil famille : getClonedFamillesList()) {
            if (famille.getPostulant() != null) {
                String nomPostulant = famille.getPostulant().getNom();
                nomsDesPostulants.add(nomPostulant);
            }
        }

        return nomsDesPostulants;
    }

    public List<FamilleAccueil> getClonedPostulantsList() throws CloneNotSupportedException {
        List<FamilleAccueil> familles = famillesViewModel.getOriginalFamillesList();
        List<FamilleAccueil> clonedPostulants = new ArrayList<>();

        for (FamilleAccueil famille : familles) {
            clonedPostulants.add((FamilleAccueil) famille.clone());
        }

        setClonedFamillesList(clonedPostulants);

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