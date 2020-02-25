package slayer.simulator.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import basedatos.BaseDeDatosEscritorio;
import slayer.simulator.game.SlakerSimulator;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new SlakerSimulator(new BaseDeDatosEscritorio(),false), config);
	}
}
