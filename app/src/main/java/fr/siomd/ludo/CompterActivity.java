package fr.siomd.ludo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

import fr.siomd.ludo.databinding.ActivityCompterBinding;


public class CompterActivity extends AppCompatActivity {

    private static final String TAG = "Comptage";
    private static final int NB_QUESTIONS = 10;

    private String[] tbAnimaux = {"moutons", "cochons"};

    private int[] tbNombre = new int[NB_QUESTIONS];

    private int numQuestion = 0;
    private int indiceAnimaux = 0;
    private String animaux = tbAnimaux[indiceAnimaux];
    private String debutImageAnimaux = "";

    private int nbAnimauxQuestion = 0;
    private int nbReponsesCorrectes = 0;

    private ActivityCompterBinding ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityCompterBinding.inflate(getLayoutInflater());

        setContentView(ui.getRoot());

        // créer le jeu de carte de mouton
        int indCarte = 0;
        for (int i = 1; i < 11; i++){
            tbNombre[indCarte] = i;
            indCarte++;
        }
        demarrerJeu();
    }

    private void demarrerJeu() {
        int nbTempo;
        Random leHasard = new Random();
        for (int i = 0; i < tbNombre.length; i++){
            int indHasard = leHasard.nextInt(tbNombre.length);
            nbTempo = tbNombre[indHasard];
            tbNombre[indHasard] = tbNombre[i];
            tbNombre[i] = nbTempo;
        }


        numQuestion = -1;
        nbAnimauxQuestion = 0;
        nbReponsesCorrectes = 0;

        ui.imgCarteMouton.setImageResource(R.drawable.back);
        ui.tvResultatCompter.setText("");
        ui.etNbMouton.setText("");
        ui.etNbMouton.setEnabled(false);
        ui.tvNbRepCorrectesCompter.setText(String.format(Locale.getDefault(), "%d / %d", 0, NB_QUESTIONS));

        ui.btAnimaux.setEnabled(true);
        ui.btValider.setEnabled(false);
        ui.btJouer.setEnabled(true);

    }


    public void onClickbtNouveauJeu(View arg0){
        demarrerJeu();
    }

    public void onClickbtAnimaux(View arg0){
        Context contexte = getApplicationContext();
        indiceAnimaux = (indiceAnimaux +1) % 2;
        animaux = tbAnimaux[indiceAnimaux];
        switch (indiceAnimaux){
            case 0:{
                ui.btAnimaux.setBackground(ContextCompat.getDrawable(contexte, R.drawable.mouton));
                debutImageAnimaux = "m";
                break;
            }
            case 1: {
                ui.btAnimaux.setBackground(ContextCompat.getDrawable(contexte, R.drawable.cochon));
                debutImageAnimaux = "c";
                break;
            }
        }
    }

    public void  onClickbtJouer(View arg0){
        if (numQuestion == -1){
            ui.btAnimaux.setEnabled(false);
            ui.btValider.setEnabled(true);
            ui.etNbMouton.setEnabled(true);
            numQuestion=0;
        }
        if (numQuestion < NB_QUESTIONS){
            nbAnimauxQuestion = tbNombre[numQuestion];
            ui.imgCarteMouton.setImageResource(getCarteResource(tbNombre[numQuestion]));
            numQuestion++;
            ui.etNbMouton.setText("");
        } else {
            String message = "";
            if (nbReponsesCorrectes > 5) {
                message = String.format(Locale.getDefault(), "Tu as %d réponses correcte, bravo !", nbReponsesCorrectes);
            } else {
                message = String.format(Locale.getDefault(), "Tu as %d réponses correcte, tu peux y'arriver !", nbReponsesCorrectes);
            }
            ui.tvResultatCompter.setText(message);
            ui.btValider.setEnabled(false);
            ui.btJouer.setEnabled(false);
        }

    }

    public void onClickbtValider(View arg0) {
        traitementReponse();
        onClickbtJouer(arg0);
    }

    private void traitementReponse(){
        String strNbMouton = ui.etNbMouton.getText().toString();
        int nbAnimaux = 0;
        int nbRepCorrecte = 0;
        if (!TextUtils.isEmpty((strNbMouton))) {
            nbAnimaux = Integer.parseInt(strNbMouton);
        }
        String repNbAnimaux = "";
        if (nbAnimaux == nbAnimauxQuestion) {
            repNbAnimaux = String.format(Locale.getDefault(), "Oui, %d %s !", nbAnimauxQuestion, tbAnimaux[indiceAnimaux]);
            nbRepCorrecte++;
        } else {
            repNbAnimaux = String.format(Locale.getDefault(), "NON, c'est %d %s !", nbAnimauxQuestion, tbAnimaux[indiceAnimaux]);
        }

        if (nbRepCorrecte == 1) {
            nbReponsesCorrectes++;
            ui.tvNbRepCorrectesCompter.setText(String.format(Locale.getDefault(), "%d / %d", nbReponsesCorrectes, NB_QUESTIONS));
        }

        Toast toast = Toast.makeText(getApplicationContext(), repNbAnimaux, Toast.LENGTH_LONG);
        toast.show();
    }

    private int getCarteResource(int nbMoutonCarte){
        String nomImgMoutonCarte = String.format("%s%d",debutImageAnimaux , nbMoutonCarte);
        int resId = getResources().getIdentifier(nomImgMoutonCarte, "drawable", "fr.siomd.comptage");
        if (resId !=0) {
            return resId;
        }
        return R.drawable.back;
    }

}