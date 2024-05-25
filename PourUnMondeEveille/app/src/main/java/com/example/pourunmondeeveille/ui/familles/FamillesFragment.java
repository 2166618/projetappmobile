package com.example.pourunmondeeveille.ui.familles;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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
    private List<FamilleAccueil> clonedFamillesList = new ArrayList<>();
    private List<Integer> filteredIdsDeFamille = new ArrayList<>();
    private List<String> filteredNomsDeFamille = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private CheckBox checkBoxFiltreStatut;

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFamillesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        famillesListView = view.findViewById(R.id.famillesList);
        EditText barreDeRecherche = view.findViewById(R.id.barreDeRecherche);
        checkBoxFiltreStatut = view.findViewById(R.id.enAttenteAccueilCheckBox);

        famillesViewModel.fetchFamillesAccueil();

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, filteredNomsDeFamille);
        famillesListView.setAdapter(adapter);

        famillesViewModel.getFamillesAccueilLiveData().observe(getViewLifecycleOwner(), new Observer<List<FamilleAccueil>>() {
            @Override
            public void onChanged(List<FamilleAccueil> familles) {
                try {
                    initializeClonedFamillesList();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                applyFilters();
            }
        });

        famillesListView.setOnItemClickListener((AdapterView<?> parent, View v, int position, long id) -> {
            int idFamilleSelectionnee = filteredIdsDeFamille.get(position);
            List<FamilleAccueil> tempList = null;
            try {
                tempList = initializeClonedFamillesList();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            FamilleAccueil familleSelectionnee = tempList.stream()
                    .filter(famille -> famille.getId() == idFamilleSelectionnee)
                    .findFirst()
                    .orElse(null);
            naviguerAuFragmentProfileFamille(familleSelectionnee);
        });

        barreDeRecherche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                applyFilters();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        checkBoxFiltreStatut.setOnCheckedChangeListener((buttonView, isChecked) -> applyFilters());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public List<FamilleAccueil> initializeClonedFamillesList() throws CloneNotSupportedException {
        List<FamilleAccueil> familles = famillesViewModel.getOriginalFamillesList();
        List<FamilleAccueil> clonedFamilles = new ArrayList<>();
        for (FamilleAccueil famille : familles) {
            clonedFamilles.add((FamilleAccueil) famille.clone());
        }
        setClonedFamillesList(clonedFamilles);
        return clonedFamilles;
    }

    private void applyFilters() {
        String query = binding.barreDeRecherche.getText().toString();
        boolean filterByStatut = checkBoxFiltreStatut.isChecked();

        List<String> filteredNomsList = new ArrayList<>();
        List<Integer> filteredIdsList = new ArrayList<>();

        if (filterByStatut) {
            filteredNomsList = clonedFamillesList.stream()
                    .filter(famille -> famille.getStatutF().getId() == 1)
                    .map(famille -> famille.getPostulant().getNom())
                    .collect(Collectors.toList());
            filteredIdsList = clonedFamillesList.stream()
                    .filter(famille -> famille.getStatutF().getId() == 1)
                    .map(FamilleAccueil::getId)
                    .collect(Collectors.toList());
        } else {
            filteredNomsList = clonedFamillesList.stream()
                    .map(famille -> famille.getPostulant().getNom())
                    .collect(Collectors.toList());
            filteredIdsList = clonedFamillesList.stream()
                    .map(FamilleAccueil::getId)
                    .collect(Collectors.toList());
        }

        if (!query.isEmpty()) {
            filteredNomsList = filteredNomsList.stream()
                    .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
            filteredIdsList = clonedFamillesList.stream()
                    .filter(famille -> famille.getPostulant().getNom().toLowerCase().contains(query.toLowerCase()))
                    .map(FamilleAccueil::getId)
                    .collect(Collectors.toList());
        }

        filteredIdsDeFamille.clear();
        filteredNomsDeFamille.clear();
        filteredIdsDeFamille.addAll(filteredIdsList);
        filteredNomsDeFamille.addAll(filteredNomsList);
        adapter.notifyDataSetChanged();
    }

    private void naviguerAuFragmentProfileFamille(FamilleAccueil familleSelectionnee) {
        Bundle args = new Bundle();
        args.putSerializable("familleSelectionnee", familleSelectionnee);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.action_specific_to_profileFamilleFragment, args);
    }
}
