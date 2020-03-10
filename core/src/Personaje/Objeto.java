package Personaje;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.HashSet;

/**
 * Clase Objeto
 */
public class Objeto {
    /**
     * El sprite
     */
    protected Sprite sprite;
    /**
     * El mundo
     */
    private World mundo;
    /**
     * Las propiedades del cuerpo BodyDef
     */
    private BodyDef propiedadesCuerpo;
    /**
     * EL cuerpo Body
     */
    private Body cuerpo;
    /**
     * Las propiedades fisicas del cuerpo FixtureDef
     */
    private FixtureDef propiedadesFisicasCuerpo;
    /**
     * La palabra que idendifica el objeto para con el personaje y los logros
     */
    private String palabra;
    /**
     * Si se tiene que mostrar
     */
    private boolean mostrar;

    /**
     * Constructor de la clase
     * @param m el mundo
     * @param textura la textura
     * @param p la palabra
     * @param posicionX La posicion X
     * @param posicionY La posicion Y
     */
    public Objeto(World m, Texture textura, String p, int posicionX, float posicionY){
        mundo=m;
        sprite=new Sprite(textura);
        palabra = p;
        mostrar = true;
        int anchuraSprite=1; //Anchura y altura se expresan ahora en metros
        int alturaSprite=1;//Anchura y altura se expresan ahora en metros
        sprite.setBounds(posicionX,posicionY,
                anchuraSprite,alturaSprite); //La posición inicial también debe estar en metros

        this.propiedadesCuerpo= new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.type = BodyDef.BodyType.StaticBody;
        propiedadesCuerpo.position.set(sprite.getX(), sprite.getY());

        cuerpo = mundo.createBody(propiedadesCuerpo);


        sprite.setOrigin(this.sprite.getWidth()/2,
                this.sprite.getHeight()/2);


    }

    /**
     * Funcion que da la posicion x
     * @return Devuelve la poscion x
     */
    public float getX(){
        return this.cuerpo.getPosition().x;
    }

    /**
     * Funcion que da la posicion y
     * @return Devuelve la posicion y
     */
    public float getY(){
        return this.cuerpo.getPosition().y;
    }

    /**
     * Getter de cuerpo
     * @return El cuerpo
     */
    public Body getCuerpo(){
        return cuerpo;
    }

    /**
     * Getter de sprite
     * @return Devuelve el sprite
     */
    public Sprite getSprite(){ return sprite;}

    /**
     * Setter de mostrar
     * @param m si tiene que mostrarlo
     */
    public void setMostrar(boolean m){ mostrar = m;}

    /**
     * Getter de palabra
     * @return la palabra
     */
    public String getPalabra(){
        return palabra;
    }

    /**
     * Funcion Draw
     * @param batch el batch
     * @param parentAlpha el parentAlpha
     */
    public void draw(SpriteBatch batch, float parentAlpha) {
        //Esta cuenta hace falta por lo de la media altura. Ese absurdo cálculo...
        if(mostrar){
            sprite.setPosition(cuerpo.getPosition().x-sprite.getWidth()/2,cuerpo.getPosition().y-sprite.getHeight()/2);
            sprite.draw(batch);
        }
    }
}
