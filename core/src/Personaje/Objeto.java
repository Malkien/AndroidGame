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

public class Objeto {

    protected Sprite sprite;
    private World mundo;
    private BodyDef propiedadesCuerpo;
    private Body cuerpo;
    private FixtureDef propiedadesFisicasCuerpo;
    private String palabra;
    private boolean mostrar;

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


    public float getX(){
        return this.cuerpo.getPosition().x;
    }


    public float getY(){
        return this.cuerpo.getPosition().y;
    }

    public Body getCuerpo(){
        return cuerpo;
    }

    public Sprite getSprite(){ return sprite;}

    public void setMostrar(boolean m){ mostrar = m;}
    public String getPalabra(){
        return palabra;
    }

    public void draw(SpriteBatch batch, float parentAlpha) {
        //Esta cuenta hace falta por lo de la media altura. Ese absurdo cálculo...
        if(mostrar){
            sprite.setPosition(cuerpo.getPosition().x-sprite.getWidth()/2,cuerpo.getPosition().y-sprite.getHeight()/2);
            sprite.draw(batch);
        }
    }
}
