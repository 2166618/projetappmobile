package com.example.pourunmondeeveille.ui.enfants;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.pourunmondeeveille.databinding.FragmentEnfantsBinding;
import com.example.pourunmondeeveille.model.enfants.Enfant;
import com.example.pourunmondeeveille.model.placements.Placement;
import com.example.pourunmondeeveille.ui.placements.PlacementsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnfantFragment extends Fragment {
    private EnfantViewModel enfantViewModel;
    private PlacementsViewModel placementsViewModel;
    private List<Placement> placementsList = new ArrayList<>();
    private FragmentEnfantsBinding binding;
    private ListView enfantsListView;
    private List<Enfant> clonedEnfantsList = new ArrayList<>();
    private List<Integer> filteredIdsEnfant = new ArrayList<>();
    private List<String> filteredNomsEnfant = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private CheckBox checkBoxFiltreStatut;
    private String familleEtDateAdoption;

    public String getFamilleEtDateAdoption() {
        return familleEtDateAdoption;
    }

    public void setFamilleEtDateAdoption(String familleEtDateAdoption) {
        this.familleEtDateAdoption = familleEtDateAdoption;
    }

    private static EnfantFragment instance;

    public List<Enfant> getClonedEnfantsList() {
        return clonedEnfantsList;
    }

    public void setClonedEnfantsList(List<Enfant> clonedEnfantsList) {
        this.clonedEnfantsList = clonedEnfantsList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        enfantViewModel = new ViewModelProvider(this).get(EnfantViewModel.class);
        placementsViewModel = new ViewModelProvider(this).get(PlacementsViewModel.class);
    }

    public static Context getAppContext() {
        return instance.requireActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentEnfantsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        enfantsListView = view.findViewById(R.id.enfantsList);
        EditText barreDeRecherche = view.findViewById(R.id.barreDeRecherche);
        checkBoxFiltreStatut = view.findViewById(R.id.nonPlaceCheckBox);

        // Récupérer le token d'accès depuis SharedPreferences
        SharedPreferences sharedPreferences = getAppContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", "");

        enfantViewModel.fetchEnfants(accessToken);
        placementsViewModel.fetchPlacements(accessToken);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, filteredNomsEnfant);
        enfantsListView.setAdapter(adapter);

        enfantViewModel.getEnfantLiveData().observe(getViewLifecycleOwner(), new Observer<List<Enfant>>() {
            @Override
            public void onChanged(List<Enfant> enfants) {
                try {
                    initializeClonedEnfantsList();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                applyFilters();
            }
        });

        placementsViewModel.getPlacementsLiveData().observe(getViewLifecycleOwner(), new Observer<List<Placement>>() {
            @Override
            public void onChanged(List<Placement> placements) {
                placementsList = placements;
            }
        });

        enfantsListView.setOnItemClickListener((AdapterView<?> parent, View v, int position, long id) -> {
            int idEnfantSelectionnee = filteredIdsEnfant.get(position);
            List<Enfant> tempList = null;
            try {
                tempList = initializeClonedEnfantsList();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            Enfant enfantSelectionnee = tempList.stream()
                    .filter(enfant -> enfant.getId() == idEnfantSelectionnee)
                    .findFirst()
                    .orElse(null);
            if (enfantSelectionnee != null){
                setFamilleEtDateAdoption(getStringFamilleEtDateAdoption(enfantSelectionnee.getId()));
                naviguerAuFragmentProfileEnfant(enfantSelectionnee);
            }

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

    public List<Enfant> initializeClonedEnfantsList() throws CloneNotSupportedException {
        List<Enfant> enfants = enfantViewModel.getOriginalEnfantsList();
        List<Enfant> clonedEnfants = new ArrayList<>();
        for (Enfant enfant : enfants) {
            clonedEnfants.add((Enfant) enfant.clone());
        }
        setClonedEnfantsList(clonedEnfants);
        return clonedEnfants;
    }

    private void applyFilters() {
        String query = binding.barreDeRecherche.getText().toString();
        boolean filterByStatut = checkBoxFiltreStatut.isChecked();

        List<String> filteredNomsList = new ArrayList<>();
        List<Integer> filteredIdsList = new ArrayList<>();

        if (filterByStatut) {
            filteredNomsList = clonedEnfantsList.stream()
                    .filter(enfant -> enfant.getStatutE().getId() == 2)
                    .map(Enfant::getNom)
                    .collect(Collectors.toList());
            filteredIdsList = clonedEnfantsList.stream()
                    .filter(enfant -> enfant.getStatutE().getId() == 1)
                    .map(Enfant::getId)
                    .collect(Collectors.toList());
        } else {
            filteredNomsList = clonedEnfantsList.stream()
                    .map(Enfant::getNom)
                    .collect(Collectors.toList());
            filteredIdsList = clonedEnfantsList.stream()
                    .map(Enfant::getId)
                    .collect(Collectors.toList());
        }

        if (!query.isEmpty()) {
            filteredNomsList = filteredNomsList.stream()
                    .filter(name -> name.toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
            filteredIdsList = clonedEnfantsList.stream()
                    .filter(enfant -> enfant.getNom().toLowerCase().contains(query.toLowerCase()))
                    .map(Enfant::getId)
                    .collect(Collectors.toList());
        }

        filteredIdsEnfant.clear();
        filteredNomsEnfant.clear();
        filteredIdsEnfant.addAll(filteredIdsList);
        filteredNomsEnfant.addAll(filteredNomsList);
        adapter.notifyDataSetChanged();
    }

    private String getStringFamilleEtDateAdoption(int enfantId) {
        StringBuilder retour = new StringBuilder();

        // Récupérer les placements de la famille sélectionnée
        Optional<Placement> placementDeLEnfant = placementsList.stream()
                .filter(p -> p.getEnfant().getId() == enfantId)
                .findFirst();

        if (placementDeLEnfant.isPresent()) {
            Placement placement = placementDeLEnfant.get();
            retour.append("Famille ").append(
                    placement.getDemandePlacement().getFamilleAccueil().getPostulant().getNom())
                    .append("   ").append(placement.getDateDebut());
        }

        return retour.toString();
    }

    private void naviguerAuFragmentProfileEnfant(Enfant enfantSelectionnee) {
        Bundle args = new Bundle();
        args.putSerializable("enfantSelectionnee", enfantSelectionnee);
        args.putString("stringFamilleEtDateAdoption", getFamilleEtDateAdoption());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.action_specific_to_profileEnfantFragment, args);
    }

}