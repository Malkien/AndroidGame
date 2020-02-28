package Personaje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Vago {
    protected Sprite sprite;
    private World mundo;
    private BodyDef propiedadesCuerpo;
    private Body cuerpo;
    private FixtureDef propiedadesFisicasCuerpo;
    private HashSet<String> inventario;
    private char direccion;
    private boolean saltando;


    public Vago(World m, final ArrayList<Body> suelos){
        direccion = 'p';
        inventario = new HashSet<String>();
        mundo=m;
        sprite=new Sprite(new Texture("gothic/PNG/sprites/bearded-idle/bearded-idle-1.png"));
        float anchuraSprite=1.5f; //Anchura y altura se expresan ahora en metros
        float alturaSprite=1.5f;//Anchura y altura se expresan ahora en metros
        sprite.setBounds(5,27,
                anchuraSprite,alturaSprite); //La posición inicial también debe estar en metros

        this.propiedadesCuerpo= new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.fixedRotation = true;//Cuerpo no rote
        propiedadesCuerpo.type = BodyDef.BodyType.DynamicBody;
        propiedadesCuerpo.position.set(sprite.getX(), sprite.getY());

        cuerpo = mundo.createBody(propiedadesCuerpo);

        propiedadesFisicasCuerpo = new FixtureDef();
        propiedadesFisicasCuerpo.shape = new PolygonShape();
        ((PolygonShape)propiedadesFisicasCuerpo.shape).setAsBox(anchuraSprite/4f, alturaSprite/2f);
        propiedadesFisicasCuerpo.density = 1f;
        cuerpo.createFixture(propiedadesFisicasCuerpo);

        sprite.setOrigin(this.sprite.getWidth()/2,
                this.sprite.getHeight()/2);

        mundo.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                System.out.println("Vuelo a estar en el suelo");
                Body a=contact.getFixtureA().getBody();
                Body b=contact.getFixtureB().getBody();
                // Check to see if the collision is between the second sprite and the bottom of the screen
                // If so apply a random amount of upward force to both objects... just because
                if( suelos.contains(a) && b == cuerpo){
                    saltando = false;
                }else if(suelos.contains(b) && a == cuerpo){
                    saltando = false;
                }

            }

            @Override
            public void endContact(Contact contact) {
                saltando = true;
                System.out.println("Vuelo");
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }

    public char getDireccion() {
        return direccion;
    }

    public void setDireccion(char direccion) {
        this.direccion = direccion;
    }

    public boolean getSaltando(){ return saltando;}

    public float getX(){
        return this.cuerpo.getPosition().x;
    }


    public float getY(){
        return this.cuerpo.getPosition().y;
    }

    public Body getCuerpo(){
        return cuerpo;
    }

    public Sprite getSprite(){ return sprite; }

    public void draw(SpriteBatch batch, float parentAlpha) {
        //Esta cuenta hace falta por lo de la media altura. Ese absurdo cálculo...
        sprite.setPosition(cuerpo.getPosition().x-sprite.getWidth()/2,cuerpo.getPosition().y-sprite.getHeight()/2);
        //Sprite quiere la rotación en grados, el cuerpo la da en radianes. Esta constante convierte de uno a otro.
        //sprite.setRotation(MathUtils.radiansToDegrees*cuerpo.getAngle());
        sprite.draw(batch);
    }

    public void addObjeto(String p){
        inventario.add(p);
    }

    public HashSet<String> verInventario(){
        return inventario;
    }

    public boolean buscarInventario(String p){
        if(inventario.contains(p)){
            return true;
        }
        return false;
    }

    public void seguir(OrthographicCamera camara){
        camara.position.x=this.cuerpo.getPosition().x;
        camara.position.y=this.cuerpo.getPosition().y;
    }
}
