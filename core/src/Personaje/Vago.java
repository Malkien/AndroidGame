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

import basededatos.BaseDeDatos;

/**
 * Clase Vago
 */
public class Vago {
    /**
     * EL sprite
     */
    protected Sprite sprite;
    /**
     * El mundo
     */
    private World mundo;
    /**
     * La base de datos
     */
    private BaseDeDatos baseDeDatos;
    /**
     * Las propiedades del cuerpo BodyDef
     */
    private BodyDef propiedadesCuerpo;
    /**
     * El cuerpo Body
     */
    private Body cuerpo;
    /**
     * Las propiedades fisicas del cuerpo FixtureDef
     */
    private FixtureDef propiedadesFisicasCuerpo;
    /**
     * El inventario
     */
    private HashSet<String> inventario;
    /**
     * La direccion
     */
    private char direccion;
    /**
     * Si salta
     */
    private boolean saltando;
    /**
     * EL titulo
     */
    private String titulo;

    /**
     * Constructor de vago
     * @param m el mundo
     * @param suelos arraylist de body con todos los objetos con los que colisiona el cuerpo
     * @param lagoAgua El lago de agua
     * @param bd la base de datos
     */
    public Vago(World m, final ArrayList<Body> suelos, final Body lagoAgua, BaseDeDatos bd){
        direccion = 'p';
        baseDeDatos = bd;
        inventario = new HashSet<String>();
        mundo=m;
        titulo = "Vago";
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
            /**
             * Cuando comienza el contacto
             * @param contact el contacto
             */
            @Override
            public void beginContact(Contact contact) {
                System.out.println("Vuelo a estar en el suelo");
                Body a=contact.getFixtureA().getBody();
                Body b=contact.getFixtureB().getBody();
                // Mira si colisionan los objetos, y si ha colision, puedes saltar
                if( suelos.contains(a) && b == cuerpo){
                    saltando = false;
                    if(a == lagoAgua && !buscarInventario("anillo")){
                        baseDeDatos.guardar("Estaba viendo el lago y me entro sueño...");
                        Gdx.app.exit();
                    }
                }else if(suelos.contains(b) && a == cuerpo){
                    saltando = false;
                }

            }

            /**
             * Cuando termina el contacto
             * @param contact el contacto
             */
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

    /**
     * Getter de direccion
     * @return la direccion
     */
    public char getDireccion() {
        return direccion;
    }

    /**
     * Setter de la direccion
     * @param direccion la direccion
     */
    public void setDireccion(char direccion) {
        this.direccion = direccion;
    }

    /**
     * Getter de salto
     * @return si salta
     */
    public boolean getSaltando(){ return saltando;}

    /**
     * Funcio que devuelve la posicion x del cuerpo
     * @return la posicion del cuerpo en x
     */
    public float getX(){
        return this.cuerpo.getPosition().x;
    }

    /**
     * Getter de titulo
     * @return el titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Setter de titulo
     * @param titulo el titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Getter de basededatos
     * @return la basededatos
     */
    public BaseDeDatos getBaseDeDatos() {
        return baseDeDatos;
    }

    /**
     * Funcion que devuelve la posicion en y del cuerpo
     * @return la posicion en y
     */
    public float getY(){
        return this.cuerpo.getPosition().y;
    }

    /**
     * Getter de cuerpo
     * @return el cuerpo
     */
    public Body getCuerpo(){
        return cuerpo;
    }

    /**
     * Getter de sprite
     * @return el sprite
     */
    public Sprite getSprite(){ return sprite; }

    /**
     * Funcion Draw
     * @param batch el batch
     * @param parentAlpha el parentAlpha
     */
    public void draw(SpriteBatch batch, float parentAlpha) {
        //Esta cuenta hace falta por lo de la media altura. Ese absurdo cálculo...
        sprite.setPosition(cuerpo.getPosition().x-sprite.getWidth()/2,cuerpo.getPosition().y-sprite.getHeight()/2);
        //Sprite quiere la rotación en grados, el cuerpo la da en radianes. Esta constante convierte de uno a otro.
        //sprite.setRotation(MathUtils.radiansToDegrees*cuerpo.getAngle());
        sprite.draw(batch);
    }

    /**
     * Funcion para agregar un objeto al inventario
     * @param p el objeto
     */
    public void addObjeto(String p){
        inventario.add(p);
    }

    /**
     * Funcion para ver el inventario
     * @return el inventario
     */
    public HashSet<String> verInventario(){
        return inventario;
    }

    /**
     * Funcion para buscar en el inventario si tiene el objeto
     * @param p el objeto(String)
     * @return si esta o no
     */
    public boolean buscarInventario(String p){
        if(inventario.contains(p)){
            return true;
        }
        return false;
    }

    /**
     * Funcion para el seguimiento de la camara
     * @param camara la camara
     */
    public void seguir(OrthographicCamera camara){
        camara.position.x=this.cuerpo.getPosition().x;
        camara.position.y=this.cuerpo.getPosition().y;
    }
}
