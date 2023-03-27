package fr.euroforma.gsb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class parametre extends AppCompatActivity {
    EditText Nom, Prenom, CodeVisiteur, email, URLvisiteur;
    Button enregistrer;
    public static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String NOM = "NOM";
    private static final String PRENOM = "PRENOM";
    public static final String CODE_VISITEUR = "CODE_VISITEUR";
    private static final String EMAIL = "EMAIL";
    private static final String URL = "URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);

        Nom =findViewById(R.id.Nom);
        Prenom =  findViewById(R.id.Prenom);
        CodeVisiteur =  findViewById(R.id.CodeVisiteur);
        email =  findViewById(R.id.email);
        URLvisiteur =  findViewById(R.id.URLvisiteur);
        enregistrer = findViewById(R.id.enregistrer);

        CodeVisiteur.setText(getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString(CODE_VISITEUR, ""));
        Nom.setText(getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString(NOM, ""));
        Prenom.setText(getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString(PRENOM, ""));
        email.setText(getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString(EMAIL, ""));
        URLvisiteur.setText(getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString(URL, ""));



    }
    void afficher(String msg) {Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();}

    public void clickEnregistrer(View view){
        if (Nom.getText().toString().trim().length() == 0||Prenom.getText().toString().trim().length() == 0
                || CodeVisiteur.getText().toString().trim().length() == 0 || email.getText().toString().trim().length() == 0
                || URLvisiteur.getText().toString().trim().length() == 0) {
            afficher("champs vides");
        }else{
            afficher("les parametres ont bien été renseignés");
            SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE);
            getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                    .edit()
                    .putString(NOM, Nom.getText().toString())
                    .putString(PRENOM, Prenom.getText().toString())
                    .putString(CODE_VISITEUR, CodeVisiteur.getText().toString())
                    .putString(EMAIL, email.getText().toString())
                    .putString(URL, URLvisiteur.getText().toString())
                    .apply();

            afficher("Informations enregistrées avec succès");

            Intent menuIntent = new Intent(parametre.this, MainActivity.class);
            startActivity(menuIntent);
        }

    }
}
