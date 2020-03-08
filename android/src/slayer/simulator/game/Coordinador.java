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

public class Coordinador  extends AppCompatActivity implements InterfazDialogo.NoticeDialogListener {
    private FragmentTransaction transaction;
    private Fragment jugar;
    private Fragment logros;
    private BaseDeDatosAndroid base;
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

    public void palJuego(View view) {
        Intent intent = new Intent(this,AndroidLauncher.class);
        startActivity(intent);
    }
    private void cambiarFragment(Fragment fragment){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    public void irJugar(View view) {
        cambiarFragment(jugar);
    }

    public void irLogros(View view) {
        cambiarFragment(logros);
    }

    @Override
    public void onBackPressed() {
        DialogFragment dialog = new InterfazDialogo();
        dialog.show(getSupportFragmentManager(), "Dialogo");

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        super.onBackPressed();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this,"Ya decia yo",Toast.LENGTH_LONG).show();
    }
}
