package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyPlantActivity extends AppCompatActivity {

    ArrayList<Plant> plantsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plants);

        // recherche dans le gabarit l’objet ListView (à partir de son id)
        // créé une instance de notre adaptateur (cf point 5)
        // relie l’adaptateur à la liste

        DBHandler db = new DBHandler(this);
        plantsList = db.getAllPlants();

        for (Plant cn : plantsList) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

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
            nomRandoText.setText(plant.getName());
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

