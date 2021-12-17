package fr.siomd.ludo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fr.siomd.ludo.databinding.ActivityPenduBinding;
import fr.siomd.ludo.entity.Juge;
import fr.siomd.ludo.entity.Bourreau;

public class PenduActivity extends AppCompatActivity {

    private ActivityPenduBinding ui;
    private Juge monJuge;
    private Bourreau monBourreau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mettre en place le layout (la mise en page)
        ui = ActivityPenduBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());


        monJuge = new Juge(getResources().getXml(R.xml.dico));
        monBourreau = new Bourreau(monJuge);
        // afficher le mot en cours (avec des _ pour chaque lettre non trouvée)
        ui.tvMot.setText(monBourreau.getLeMotEnCours());

    }

    public void onClickbtStart(){
        ui.btStart.setEnabled(false);
        monBourreau.demarrer();
    }

    public void onClickbtLettre(View laVue){
        CharSequence laLettre = ((Button)laVue).getText();
        String ancienneLettreRebus = monBourreau.getLesLettresAuRebut();
        monBourreau.executer(laLettre.charAt(0));
        if(ancienneLettreRebus != monBourreau.getLesLettresAuRebut()){

        }else if(monBourreau.isGagne()){
            Toast.makeText(getApplicationContext(), "Gagné avec " + monJuge.getScore() + "points!", Toast.LENGTH_LONG).show();

        } else if(monBourreau.isPerdu()){
            Toast.makeText(getApplicationContext(), "Perdu mais réessaye encore!", Toast.LENGTH_LONG).show();
        }
    }


}