package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyPlantActivity extends AppCompatActivity {

    ArrayList<Plant> plantsList = new ArrayList<Plant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plants);

        Plant plant1 = new Plant();
        plant1.name = "Lamium Purpureum";

        Plant plant2 = new Plant();
        plant2.name = "Arbre à papillon";

        plantsList.add(plant1);
        plantsList.add(plant2);

        // recherche dans le gabarit l’objet ListView (à partir de son id)
        // créé une instance de notre adaptateur (cf point 5)
        // relie l’adaptateur à la liste

        ListView liste = (ListView) findViewById(R.id.plants);
        PlantAdapter adapter = new PlantAdapter(plantsList);
        liste.setAdapter(adapter);

        //On click event
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detailsIntent = new Intent(MyPlantActivity.this, PlantDetailsActivity.class);
                startActivity(detailsIntent);
            }
        });
    }

    public void onClickGoBack(View view) {
        this.finish();
    }

    class PlantAdapter extends BaseAdapter {

        public ArrayList<Plant> plantsList;

        public PlantAdapter(ArrayList<Plant> plantsList) {
            this.plantsList = plantsList;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            // on va chercher la randonnée dans le tableau
            Plant plant = plantsList.get(position);
            View itemView = getLayoutInflater().inflate(R.layout.plant_list_item, null);

            TextView nomRandoText = (TextView) itemView.findViewById(R.id.item_nom_rando);
            nomRandoText.setText(plant.name);
            return itemView;
        }

        public Object getItem(int position) {
            return null;
        }

        public int getCount() {
            return this.plantsList.size();
        }
        public View getView(int position) {
            return null;
        }
        public long getItemId(int position) {
            return 0;
        }
    }
}

