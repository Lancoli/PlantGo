package com.example.myapplication.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.activity.MyPlantListActivity;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavbarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavbarFragment extends Fragment {
    private Context context;

    public NavbarFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NavbarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavbarFragment newInstance() {
        NavbarFragment fragment = new NavbarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navbar, container, false);
        context = getContext();
        initNavItemOnClick(view);
        return view;
    }

    public void initNavItemOnClick(View view) {
        ImageView deconnexion_icon = view.findViewById(R.id.deconnexion_icon);
        TextView deconnexion_text = view.findViewById(R.id.deconnexion_text);
        deconnexion_icon.setOnClickListener(event -> {
            handleDisconnect();
        });
        deconnexion_text.setOnClickListener(event -> {
            handleDisconnect();
        });

        //Action pour aller à la liste de plante
        ImageView myPlantIcon = view.findViewById(R.id.my_plants_icon);
        TextView myPlantText = view.findViewById(R.id.my_plants_text);
        myPlantIcon.setOnClickListener(event -> {
            handleRedirectMyPlants();
        });
        myPlantText.setOnClickListener(event -> {
            handleRedirectMyPlants();
        });
    }

    public void handleDisconnect() {
        getActivity().finish();
    }

    public void handleRedirectMyPlants() {
        if (context != null) {
            Intent intent = new Intent(context, MyPlantListActivity.class);
            startActivity(intent);
        }
    }
}