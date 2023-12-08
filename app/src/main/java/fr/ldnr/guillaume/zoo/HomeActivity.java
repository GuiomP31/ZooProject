package fr.ldnr.guillaume.zoo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Log.i("HomeActivity", "#### Créer !");

        TextView tvNews = findViewById(R.id.tvNews);
        // La chaine devrait venir du compte Twitter par exemple
        tvNews.setText("Rien de nouveau aujourd'hui. ");
        // Ajout d'un ! dans les infos
        TextView tvInfos = findViewById(R.id.tvInfos);
        tvInfos.setText(tvInfos.getText() + "!");
        // Evénement -> map
        Button btMap = findViewById(R.id.btMap);
        btMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ouverture de la carte
                Intent i = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(i);
            }
        });

        Button btAlert = findViewById(R.id.btAlert);
        btAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ouverture de l'alerte
                Intent i = new Intent(HomeActivity.this, AlertActivity.class);
                startActivity(i);
            }


        });
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            writeLog();
        } else {
            // Affichage d'une fenêtre de demande, puis appel de onRequ...
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            writeLog();

}
    public void writeLog(){
        File dir = Environment.getExternalStoragePublicDirectory(
              Environment.DIRECTORY_DOWNLOADS);
        try{
          FileWriter fw = new FileWriter(new File(dir, "Zoolog.txt"), true);
          fw.write("Ici !\n");
          fw.close();
         } catch (IOException ex) {
          Log.e("Home", "Error",ex);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home,menu);
        SharedPreferences sp = getSharedPreferences("Zoo",MODE_PRIVATE);
        boolean save = sp.getBoolean("Save",false);
        menu.findItem(R.id.menu_save).setChecked(save);
        return true;
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_map:
            startActivity(new Intent(this,MapActivity.class));
            return true;
            case R.id.menu_save:
                item.setChecked(! item.isChecked());
                // Enregistrement
                SharedPreferences sp = getSharedPreferences("Zoo",MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putBoolean("Save",item.isChecked());
                ed.commit();
                return true;
            default:
                return false;
        }
    }
}

