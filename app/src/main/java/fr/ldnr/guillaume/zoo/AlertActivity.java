package fr.ldnr.guillaume.zoo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AlertActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert);
        Log.i("AlertActivity", "#### Créer !");
    }

    public void send(View view) {
        TextView tv = findViewById(R.id.tvPlease);
        try {
            EditText etTitle = findViewById(R.id.etTitle);
            String title = etTitle.getText().toString();
            EditText etLocation = findViewById(R.id.etLocation);
            String location = etLocation.getText().toString();
            EditText etInfos = findViewById(R.id.etInformations);
            String infos = etInfos.getText().toString();
            // Enregistrement en base de données
            ZooSQliteHelper h = new ZooSQliteHelper(this);
            String allAlerts = h.addAlert(title,location,infos);
            tv.setText("Alerte !" + title + " Enregistrer ("+allAlerts+")"); // Affiche le titre de l'alerte
            // finish ();
    } catch(Exception ex){
            tv.setText("Erreur : " +ex);
            Log.e("Alert", "Error", ex);
        }
    }
}