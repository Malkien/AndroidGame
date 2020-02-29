package slayer.simulator.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Personaje.Objeto;
import Personaje.Vago;
import basededatos.BaseDeDatos;
import constantes.Constantes;
import input.Teclado;

public class SlakerSimulator extends ApplicationAdapter {
	private World world;
	private TiledMap mapa;
	private static final float pixelsPorCuadro=32f;
	private OrthogonalTiledMapRenderer renderer;
	private Vago vago;
	private Objeto objetoNinja;
	private Objeto objetoEspada;
	private Objeto objetoAnillo;
	private Teclado teclado;
	private Box2DDebugRenderer debugRenderer;
	private SpriteBatch batch;
	private OrthographicCamera camara;
	private Body paredFinal;
	BaseDeDatos baseDeDatos;
	private boolean esAndroid;
	private Body lagoAgua;
	private ArrayList<Body> suelos;
	private SpriteBatch batchTexto;
	private BitmapFont textoPantalla;

	public SlakerSimulator(BaseDeDatos bd, boolean android){
		baseDeDatos=bd;

		this.esAndroid=android;
		Constantes.init();
	}



	@Override
	public void create () {
		batch=new SpriteBatch();
		/////////////////////////////////////
		batchTexto = new SpriteBatch();
		textoPantalla = new BitmapFont();
		///////////////////////////////////
		baseDeDatos.completar("");
		////////////////////////////////
		world=new World(new Vector2(0,-9.8f),true);
		suelos = new ArrayList<Body>();


		mapa=new TmxMapLoader().load("mapa/map.tmx");
		renderer = new OrthogonalTiledMapRenderer(mapa, 1/pixelsPorCuadro);

		for (MapObject objeto:mapa.getLayers().get("objetos").getObjects()){
			BodyDef propiedadesRectangulo= new BodyDef(); //Establecemos las propiedades del cuerpo
			propiedadesRectangulo.type = BodyDef.BodyType.StaticBody;
			Body rectanguloSuelo = world.createBody(propiedadesRectangulo);
			suelos.add(rectanguloSuelo);
			FixtureDef propiedadesFisicasRectangulo=new FixtureDef();
			Shape formaRectanguloSuelo=getRectangle((RectangleMapObject)objeto);
			propiedadesFisicasRectangulo.shape = formaRectanguloSuelo;
			propiedadesFisicasRectangulo.density = 1f;
			rectanguloSuelo.createFixture(propiedadesFisicasRectangulo);
			//Cojo la pared final como objeto especial
			if(objeto.getName()!=null&&objeto.getName().equals("paredFinal")){
				paredFinal=rectanguloSuelo;
			}
		}
		BodyDef lago= new BodyDef(); //Establecemos las propiedades del cuerpo
		lago.position.set(new Vector2(70, 25.01f));
		lago.type = BodyDef.BodyType.StaticBody;
		lagoAgua = world.createBody(lago);
		PolygonShape lagoPoligono = new PolygonShape();
		lagoPoligono.setAsBox(15,1);
		lagoAgua.createFixture(lagoPoligono,0.0f);
		lagoPoligono.dispose();
		suelos.add(lagoAgua);

		vago=new Vago(world, suelos);
		objetoNinja = new Objeto(world,new Texture("espada.png"),"ninja", 1,27.5f);
		objetoEspada = new Objeto(world,new Texture("jungla/sprites/creatures/spr_ape_yeti.png"),"espada", 5,27.5f);
		objetoAnillo = new Objeto(world,new Texture("jungla/sprites/creatures/spr_ape_yeti.png"),"anillo", 35,36.5f);

		camara=new OrthographicCamera(10,10);
		this.debugRenderer=new Box2DDebugRenderer();

		camara.position.x = vago.getX();
		camara.position.y = vago.getY();

		teclado=new Teclado(vago, new Objeto[]{objetoNinja,objetoAnillo,objetoEspada} );
		Gdx.input.setInputProcessor(teclado);
		////////////////////////////////////////////////////////////COLISION////////////////////////////////////////////////

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		if(vago.getDireccion() == 'd'){
			vago.getCuerpo().setLinearVelocity(Constantes.fuerzaLanzamientoX,vago.getCuerpo().getLinearVelocity().y);
		}else if( vago.getDireccion() == 'i'){
			vago.getCuerpo().setLinearVelocity(Constantes.fuerzaLanzamientoX*-1,vago.getCuerpo().getLinearVelocity().y);
		}
		vago.seguir(camara);
		renderer.setView(camara);
		renderer.render();
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		vago.draw(batch,0);
		objetoNinja.draw(batch,0);
		objetoEspada.draw(batch,0);
		objetoAnillo.draw(batch,0);

		batchTexto.begin();
		textoPantalla.draw(batch,"PRUEBAAAAAAA",100,Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth(),1,false);
		batchTexto.end();

		batch.end();

		camara.update();
		debugRenderer.render(world, camara.combined);
	}

	@Override
	public void dispose () {
		world.dispose();
	}



	/**
	 * LOS POLÍGONOS DE MÁS DE 9 VÉRTICES DAN ERROR. Usa más polígonos más simples.
	 * @param polygonObject
	 * @return
	 */
	private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
		PolygonShape polygon = new PolygonShape();
		float[] vertices = polygonObject.getPolygon().getTransformedVertices();

		float[] worldVertices = new float[vertices.length];

		for (int i = 0; i < vertices.length; ++i) {
			worldVertices[i] = vertices[i] / pixelsPorCuadro;
		}

		polygon.set(worldVertices);
		return polygon;
	}

	private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
		Rectangle rectangle = rectangleObject.getRectangle();
		PolygonShape polygon = new PolygonShape();
		Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) /pixelsPorCuadro,
				(rectangle.y + rectangle.height * 0.5f ) / pixelsPorCuadro);
		polygon.setAsBox(rectangle.width * 0.5f /pixelsPorCuadro,
				rectangle.height * 0.5f / pixelsPorCuadro,
				size,
				0.0f);
		return polygon;
	}

	private static CircleShape getCircle(CircleMapObject circleObject) {
		Circle circle = circleObject.getCircle();
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(circle.radius / pixelsPorCuadro);
		circleShape.setPosition(new Vector2(circle.x / pixelsPorCuadro, circle.y /pixelsPorCuadro));
		return circleShape;
	}

	private static ChainShape getPolyline(PolylineMapObject polylineObject) {
		float[] vertices = polylineObject.getPolyline().getTransformedVertices();
		Vector2[] worldVertices = new Vector2[vertices.length / 2];

		for (int i = 0; i < vertices.length / 2; ++i) {
			worldVertices[i] = new Vector2();
			worldVertices[i].x = vertices[i * 2] / pixelsPorCuadro;
			worldVertices[i].y = vertices[i * 2 + 1] / pixelsPorCuadro;
		}

		ChainShape chain = new ChainShape();
		chain.createChain(worldVertices);
		return chain;
	}

}
