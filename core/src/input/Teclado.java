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
import basededatos.BaseDeDatos;
import constantes.Constantes;

/**
 * Clase Teclado
 */
public class Teclado implements InputProcessor {
    /**
     * El bago
     */
    private Vago actor;//El actor
    /**
     * Los objetos
     */
    private Objeto[] objetos;//Los objetos

    /**
     * Constructor de Teclado
     * @param j El Vago
     * @param objetos Los objetos
     */
    public Teclado(Vago j, Objeto[] objetos){
        this.actor=j;
        this.objetos = objetos;
    }

    /**
     * Sobreescribir metodos de teclado
     * @param keycode La tecla
     * @return
     */
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

    /**
     * Sobreescribir metodos de teclado
     * @param keycode La tecla
     * @return
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Sobreescribir la funcion de pantalla tactil al pulsar
     * @param screenX Posicion x
     * @param screenY Posicion y
     * @param pointer Pointer
     * @param button Boton
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenY<Gdx.graphics.getHeight()/3) {
            //actor.getCuerpo().setLinearVelocity(0f,Constantes.fuerzaSalto);
            if(!actor.getSaltando()){
                actor.getCuerpo().applyForceToCenter(0, Constantes.fuerzaSalto, true);
            }
        }else if(screenX>Gdx.graphics.getWidth()/2){
            actor.getCuerpo().setLinearVelocity(Constantes.fuerzaLanzamientoX,actor.getCuerpo().getLinearVelocity().y);
            actor.setDireccion('d');
            //actor.getCuerpo().applyForceToCenter(Constantes.fuerzaLanzamientoX,actor.getCuerpo().getLinearVelocity().y,true);
        }else{
            actor.getCuerpo().setLinearVelocity(Constantes.fuerzaLanzamientoX*-1,actor.getCuerpo().getLinearVelocity().y);
            actor.setDireccion('i');
            //actor.getCuerpo().applyForceToCenter(Constantes.fuerzaLanzamientoX*-1,actor.getCuerpo().getLinearVelocity().y,true);
        }

        checkCollision(actor, objetos);

        return false;
    }

    /**
     * Sobreescribir la funcion de pantalla tactil al soltar
     * @param screenX Posicion x
     * @param screenY Posicion y
     * @param pointer Pointer
     * @param button Boton
     * @return
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenY>Gdx.graphics.getHeight()/3) {
            if(screenX>Gdx.graphics.getWidth()/2){
                actor.getCuerpo().setLinearVelocity(1f,actor.getCuerpo().getLinearVelocity().y);
            }else{
                actor.getCuerpo().setLinearVelocity(-1f,actor.getCuerpo().getLinearVelocity().y);
            }

        }


        checkCollision(actor, objetos);
        actor.setDireccion('p');
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    /**
     * Funcion para ver si un objeto colisiona con el vago
     * @param principal el vago
     * @param objeto el objeto
     */
    public void checkCollision(Vago principal, Objeto objeto) {
        if(Intersector.overlaps(principal.getSprite().getBoundingRectangle(), objeto.getSprite().getBoundingRectangle())){
            if(objeto.getPalabra().equalsIgnoreCase("puertaDungeon") && principal.buscarInventario("espada")){
                principal.getBaseDeDatos().guardar("Envidia de Indiana Jones");
                principal.setTitulo("Dungeon Hunter");
            }else if(objeto.getPalabra().equalsIgnoreCase("puertaDungeon") && !principal.buscarInventario("espada")){

                Gdx.app.exit();
            }else{
                principal.addObjeto(objeto.getPalabra());
                objeto.setMostrar(false);
            }
        }

    }

    /**
     * Funcion que itera en cada objeto
     * @param principal vago
     * @param objetos objeto
     */
    public void checkCollision(Vago principal, Objeto[] objetos) {
        for(Objeto spriteGroup : objetos) {
            checkCollision(principal, spriteGroup);
        }
    }
}
