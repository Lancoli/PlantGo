package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SELECTED_CATEGORY = "selectedCategory";

    // TODO: Rename and change types of parameters
    private String selectedCategory = "water";

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public CategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param selectedCategory
     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriesFragment newInstance(String selectedCategory) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString(SELECTED_CATEGORY, selectedCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedCategory = getArguments().getString(SELECTED_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        ImageButton selectWater = view.findViewById(R.id.selectWater);
        selectWater.setOnClickListener(event -> {
            handleSelectWater();
        });

        ImageButton selectSize = view.findViewById(R.id.selectSize);
        selectSize.setOnClickListener(event -> {
            handleSelectSize();
        });

        ImageButton selectDifficulty = view.findViewById(R.id.selectDifficulty);
        selectDifficulty.setOnClickListener(event -> {
            handleSelectDifficulty();
        });

        ImageButton selectLight = view.findViewById(R.id.selectLight);
        selectLight.setOnClickListener(event -> {
            handleSelectLight();
        });

        return view;
    }

    public void handleSelectWater() {
        selectedCategory = "water";
    }

    public void handleSelectSize() {
        selectedCategory = "size";
    }

    public void handleSelectDifficulty() {
        selectedCategory = "difficulty";
    }

    public void handleSelectLight() {
        selectedCategory = "light";
    }
}