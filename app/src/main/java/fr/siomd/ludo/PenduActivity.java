package fr.siomd.ludo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.siomd.ludo.dataaccess.DicoXml;
import fr.siomd.ludo.databinding.ActivityPenduBinding;
import fr.siomd.ludo.entity.Mot;
import fr.siomd.ludo.entity.Theme;

public class PenduActivity extends AppCompatActivity {

    private ActivityPenduBinding ui;
    private ArrayList<Theme> lesThemes;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mettre en place le layout (la mise en page)
        ui = ActivityPenduBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        // récuperer le dictionnaire de thèmes
        lesThemes = DicoXml.getLesThemes(getResources().getXml(R.xml.dico));

        // afficher liste juste pour vérification
        for (Theme unTheme : lesThemes){
            Log.i("DICO-liste", "Theme = " + unTheme.getNom());
            for (Mot unMot : unTheme.getLesMots()) {
                Log.i("DICO-liste", "Mot = " + unMot.getContenu() + " - " + unMot.getNbPoints());
            }
        }

        private void updateImg(int play) {
            int resImg = getResources().getIdentifier("pendu_" + play, "drawable",
                    getPackageName());

            img.setImageResource(resImg);
        }

        public void onClickbtLettre(View v) {
            if (nbErreurs < MAX_ERREURS
                    && !getString(R.string.gagner).equals(ui.tvMotATrouver.getText())) {
                String letter = ((Button) v).getText().toString();
                enter(letter);
                ui.tvMot.setText(motTrouverContent());
                updateImg(nbErreurs);

                // vérifier si le mot est trouvé
                if (motTrouver()) {
                    Toast.makeText(this, R.string.gagner, Toast.LENGTH_SHORT).
                            show();
                    motATrouverTv.setText(R.string.gagner);
                } else {
                    if (nbErreurs >= MAX_ERREURS) {
                        Toast.makeText(this, R.string.perdu, Toast.LENGTH_SHORT).show();
                        ui.tvMotATrouver.setText(getString(R.string.motCacher).
                                replace("#word#", motATrouver));
                    }
                }
            } else {
                Toast.makeText(this, R.string.partieFini, Toast.LENGTH_SHORT).show();
            }
        }
    }
}