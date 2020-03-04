package slayer.simulator.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import basedatos.BaseDeDatosAndroid;
import slayer.simulator.game.SlakerSimulator;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SlakerSimulator(new BaseDeDatosAndroid(this),true), config);


	}
}
