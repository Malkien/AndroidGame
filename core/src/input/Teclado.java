package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import Personaje.Vago;
import constantes.Constantes;

/**
 * Created by Miguel on 17/02/2019.
 */

public class Teclado implements InputProcessor {
    Vago actor;

    public Teclado(Vago j){
        this.actor=j;
    }


    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("eventoDown","Input "+keycode);
        switch (keycode) {
            case Input.Keys.LEFT:
                actor.getCuerpo().applyForceToCenter(Constantes.fuerzaLanzamientoX*-1,Constantes.fuerzaLanzamientoY,true);
                break;
            case Input.Keys.RIGHT:
                actor.getCuerpo().applyForceToCenter(Constantes.fuerzaLanzamientoX,Constantes.fuerzaLanzamientoY,true);
                break;
            case Input.Keys.UP:
                actor.getCuerpo().applyForceToCenter(0,250,true);
                break;
    }
        Gdx.app.log("Qeeeeee","X "+Constantes.fuerzaLanzamientoX+" Y "+Constantes.fuerzaLanzamientoY);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenX>Gdx.graphics.getWidth()/2){
            actor.getCuerpo().applyForceToCenter(Constantes.fuerzaLanzamientoX*-1,Constantes.fuerzaLanzamientoY,true);
        }else{
            actor.getCuerpo().applyForceToCenter(Constantes.fuerzaLanzamientoX,Constantes.fuerzaLanzamientoY,true);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
