package slayer.simulator.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import basedatos.BaseDeDatosAndroid;
import servicios.InterfazDialogo;

/**
 * Clase principal del app
 */
public class Coordinador  extends AppCompatActivity implements InterfazDialogo.NoticeDialogListener {
    /**
     * La transaccion
     */
    private FragmentTransaction transaction;// transaction
    /**
     * El fragmento jugar
     */
    private Fragment jugar;//El fragmento jugar
    /**
     * El fragmento Logros
     */
    private Fragment logros;//El fragmento Logros
    /**
     * La base de datos
     */
    private BaseDeDatosAndroid base;//La base de datos

    /**
     * Sobreescribe el onCreate inicia la bb, los fragmentos y pone la pantalla
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        setContentView(R.layout.activity_coordenador);
        base = new BaseDeDatosAndroid(this);
        jugar = new Jugar();
        cambiarFragment(jugar);
        logros = new Logros(this,base);
    }

    /**
     * Funcion que cambia de activity
     * @param view El view
     */
    public void palJuego(View view) {
        Intent intent = new Intent(this,AndroidLauncher.class);
        startActivity(intent);
    }

    /**
     * Funcion para cambiar el fragmento
     * @param fragment El fragmento que pondras
     */
    private void cambiarFragment(Fragment fragment){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    /**
     * Click para ir a jugar
     * @param view
     */
    public void irJugar(View view) {
        cambiarFragment(jugar);
    }

    /**
     * Click para ir a Logros
     * @param view
     */
    public void irLogros(View view) {
        cambiarFragment(logros);
    }

    /**
     * Sobreescribe el onBackPressed para que te pase el dialogo personalizado
     */
    @Override
    public void onBackPressed() {
        DialogFragment dialog = new InterfazDialogo();
        dialog.show(getSupportFragmentManager(), "Dialogo");

    }

    /**
     * Sobreescribe el manejo de la respuesta positiva del dialogo
     * @param dialog el dialogo
     */
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        super.onBackPressed();
    }

    /**
     * Sobreescribe el manejo de la respuesta negativa del dialogo
     * @param dialog el dialogo
     */
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this,"Ya decia yo",Toast.LENGTH_LONG).show();
    }
}
