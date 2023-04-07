package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.logic.plant.Plant;
import com.example.myapplication.storage.DBHandler;
import com.example.myapplication.ui.fragments.CategoriesFragment;

import java.util.ArrayList;

public class MyPlantListActivity extends AppCompatActivity {

    public static final int RESULT_ADD_PLANT = 1;
    public static final int RESULT_PLANT_DETAILS = 2;
    ArrayList<Plant> plantsList = new ArrayList<>();
    ListView plantListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plant_list);
        plantListView = (ListView) findViewById(R.id.plants);
        refreshPlantList();
        // recherche dans le gabarit l’objet ListView (à partir de son id)
        // créé une instance de notre adaptateur (cf point 5)
        // relie l’adaptateur à la liste
    }

    public void refreshPlantList() {
        DBHandler db = new DBHandler(this);
        plantsList = db.getAllPlants();

        if(plantsList.size() > 0) {
            //on ajoute le header de liste
            addPlantListHeaderFragment();
            //on construit la liste d'item
            buildPlantListItems();
        } else {

        }
    }

    private void buildPlantListItems() {
        PlantAdapter adapter = new PlantAdapter(plantsList);
        plantListView.setAdapter(adapter);

        //On click event
        plantListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int idx, long l) {
                Intent detailsIntent = new Intent(MyPlantListActivity.this, PlantDetailsActivity.class);
                Plant selectedPlant = plantsList.get(idx);
                detailsIntent.putExtra("plant_id", selectedPlant.getId());
                startActivityForResult(detailsIntent, RESULT_PLANT_DETAILS);
            }
        });
    }

    private void addPlantListHeaderFragment() {
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.my_plant_list_categories, categoriesFragment)
                .commit();
    }


    public void onClickGoBack(View view) {
        this.finish();
    }

    public void onClickAddPlant(View view) {
        Intent detailsIntent = new Intent(MyPlantListActivity.this, AddPlantActivity.class);
        startActivityForResult(detailsIntent, RESULT_ADD_PLANT);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refreshPlantList();
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

            TextView plantName = (TextView) itemView.findViewById(R.id.plant_name);
            TextView plantLightNeed = (TextView) itemView.findViewById(R.id.plant_light_need);
            ImageView plantImage = (ImageView) itemView.findViewById(R.id.plant_img);

            Bitmap imageBitmap = BitmapFactory.decodeFile(plant.getImageUrl());
            plantName.setText(plant.getName());
            plantLightNeed.setText(plant.getLightNeeds());
            plantImage.setImageBitmap(imageBitmap);
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

