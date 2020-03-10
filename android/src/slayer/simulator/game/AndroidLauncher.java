package slayer.simulator.game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import basedatos.BaseDeDatosAndroid;
import servicios.Musicote;
import slayer.simulator.game.SlakerSimulator;

/**
 * Clase que inicia el juego
 */
public class AndroidLauncher extends AndroidApplication {
	/**
	 * Sobreescibe el onCreate donde inicia el juego y el servicio de la musica
	 * @param savedInstanceState El savedInstanceState
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SlakerSimulator(new BaseDeDatosAndroid(this),true), config);
		startService(new Intent(this, Musicote.class));

	}

	/**
	 * Sobreescibe el onDestroy para que pare el servicio de musica y saque un Toast por pantalla
	 */
	@Override
	protected void onDestroy() {
		Toast.makeText(this,"Has muerto",Toast.LENGTH_LONG).show();
		stopService(new Intent(this, Musicote.class));
		super.onDestroy();
	}

	/**
	 * Sobreescribe el onBackPressed Para que pare el servicio
	 */
	@Override
	public void onBackPressed() {
		stopService(new Intent(this, Musicote.class));
		super.onBackPressed();
	}
}
