package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import javax.jws.Oneway;

import Personaje.Objeto;
import Personaje.Vago;
import constantes.Constantes;

/**
 * Created by Miguel on 17/02/2019.
 */

public class Teclado implements InputProcessor {
    private Vago actor;
    private Objeto[] objetos;

    public Teclado(Vago j, Objeto[] objetos){
        this.actor=j;
        this.objetos = objetos;
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
        checkCollision(actor, objetos);

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
        if(screenY<Gdx.graphics.getHeight()/3) {
            actor.getCuerpo().applyForceToCenter(0, 850, true);
        }else if(screenX>Gdx.graphics.getWidth()/2){
            actor.getCuerpo().applyForceToCenter(Constantes.fuerzaLanzamientoX,Constantes.fuerzaLanzamientoY,true);
        }else{
            actor.getCuerpo().applyForceToCenter(Constantes.fuerzaLanzamientoX*-1,Constantes.fuerzaLanzamientoY,true);
        }

        checkCollision(actor, objetos);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenY>Gdx.graphics.getHeight()/3) {
            actor.getCuerpo().setLinearVelocity(1f,0);
        }


        checkCollision(actor, objetos);
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

    public static void checkCollision(Vago principal, Objeto objeto) {
        if(Intersector.overlaps(principal.getSprite().getBoundingRectangle(), objeto.getSprite().getBoundingRectangle())){
            principal.addObjeto(objeto.getPalabra());
            objeto.setMostrar(false);
        }

    }

    public static void checkCollision(Vago principal, Objeto[] objetos) {
        for(Objeto spriteGroup : objetos) {
            checkCollision(principal, spriteGroup);
        }
    }
}
