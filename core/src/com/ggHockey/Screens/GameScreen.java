package com.ggHockey.Screens;

import com.ggHockey.game.GGHockey;
import com.ggHockey.GameObjects.Paddle;
import com.ggHockey.GameObjects.Player;
import com.ggHockey.GameObjects.Puck;
import com.ggHockey.Helpers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

public class GameScreen extends InputAdapter implements Screen {
	
	static final float WORLD_TO_BOX=0.01f;
	static final float BOX_WORLD_TO=100f;
	
    static final float BOX_STEP=1/60f;  
    static final int BOX_VELOCITY_ITERATIONS=6;  
    static final int BOX_POSITION_ITERATIONS=2;  

    static final float RATIO = 3f;
	static final float STAGE_W = 480f;
	static final float STAGE_H = 800f;
	
	private SpriteBatch batch;
	//private Player player = new Player();
	private Puck puck;
	private Paddle paddle;
	private TextureRegion puckRegion;	
	private World world;
	private BodyDef bd, ballBodyDef;
	private Body puckBody, paddleBody;
	private Body groundBody;
    private Fixture fixPaddle;
    private CircleShape circle, circle2;  
    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private Camera camera;
    private MouseJoint mouseJoint;
    
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		delta = Gdx.graphics.getDeltaTime();
		
		/*
		if (touchPosition.x > position.x){
			position.x += moveSpeed * delta;
		}	
		else if (touchPosition.x < position.x){
			position.x -= moveSpeed * delta;
		}	
		if (touchPosition.y > position.y){
			position.y += moveSpeed * delta;
		}	
		else if (touchPosition.y < position.y){
			position.y -= moveSpeed * delta;
		}	
		
		if (Math.abs(touchPosition.x - position.x) < 5){
			position.x = touchPosition.x;
		}	
		if (Math.abs(touchPosition.y - position.y) < 5){
			position.y = touchPosition.y;
		}	
		*/
		
		
		batch.begin();
		//batch.draw(paddleRegion, touchPosition.x, 800 - touchPosition.y);
		//batch.draw(puckRegion, 240, 400);
		//batch.draw(AssetLoader.puck, puck.pos.x, 800 - puck.pos.y);
		//batch.draw(AssetLoader.paddle, paddle.pos.x, 800 - paddle.pos.y);
		//batch.draw(player.paddle.paddleRegion, 240, 400);
		//batch.draw(puckRegion, 240, 400);
		batch.end();
		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
		debugRenderer.render(world, camera.combined);

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
        camera = new OrthographicCamera();  
        camera.viewportHeight = 800;  
        camera.viewportWidth = 480;  
        camera.position.set(240f, 400f, 0f);  
        camera.update();  
		puck = new Puck(240, 400);
		paddle = new Paddle(240, 200);
		puckRegion = 
				new TextureRegion(new Texture(Gdx.files.internal("img/taiji.png")));
		
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		world = new World(new Vector2(0, -100f), true);
		
        bd = new BodyDef();
        bd.position.set(0, 0);
        
        groundBody = world.createBody(bd);
        
        createEdge(new Vector2(0f, 0f), new Vector2(480f, 0f));
        createEdge(new Vector2(0f, 0f), new Vector2(0f, 800f));
        createEdge(new Vector2(0f, 800f), new Vector2(480, 800f));
        createEdge(new Vector2(480f, 800f), new Vector2(480f, 0f));
        
        
        //puckBody = createBody(world, new Vector2(340f, 200f), 0, puckBody);
        /*
        body.setUserData("ball");
        CircleShape circle = new CircleShape();
        circle.setRadius(2f);
        createShape(new Vector2(240f, 400f), circle);
        */
        
        //puckBody.setUserData("ball");
        //circle = new CircleShape();
        //circle.setRadius(50f);
        //circle.setPosition(new Vector2(240f, 600f));
        //puckBody.createFixture(ballShapeDef);
        
        paddleBody = createBody(world, new Vector2(140f, 600f), 0, paddleBody);
        paddleBody.setUserData("ball");
        circle2 = new CircleShape();
        circle2.setRadius(80f);
        circle2.setPosition(new Vector2(240f, 200f));
        FixtureDef ballShapeDef = new FixtureDef();
        ballShapeDef.shape = circle2;  
        ballShapeDef.density = 1.0f;
        ballShapeDef.friction = 0.2f;
        ballShapeDef.restitution = 0.8f;
        fixPaddle = paddleBody.createFixture(ballShapeDef);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	
	float ConvertToBox(float x){
	    return x*WORLD_TO_BOX;
	}
	
	public Body createBody(World world,Vector2 pos,float angle, Body body){
	    BodyDef bodyDef = new BodyDef(); 	    
	    bodyDef.type = BodyType.DynamicBody;
	    bodyDef.position.set(ConvertToBox(pos.x),ConvertToBox(pos.y));
	    bodyDef.angle = angle;
	    body = world.createBody(bodyDef);
	    return body;
	}
	
	public void createEdge(Vector2 pos1, Vector2 pos2){
		EdgeShape edge = new EdgeShape();
        edge.set(pos1, pos2);
        FixtureDef boxShapeDef = new FixtureDef();
        boxShapeDef.shape = edge;
        groundBody.createFixture(boxShapeDef);		
	}
	
	public void createShape(Vector2 pos, Body body, Shape shape){      
        FixtureDef shapeDef = new FixtureDef();
        shapeDef.shape = shape;
        shapeDef.density = 1.0f;
        shapeDef.friction = 0.2f;
        shapeDef.restitution = 0.8f;
        body.createFixture(shapeDef);	
	}

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
    	
    	System.out.println("3");
        if (mouseJoint == null) {
        	System.out.println("1");
            
            //if (fixPaddle.testPoint(x, y)) {
                System.out.println("1");
                
                MouseJointDef mouseJointDef = new MouseJointDef();
                mouseJointDef.bodyA = groundBody;
                mouseJointDef.bodyB = paddleBody;

                mouseJointDef.collideConnected = true;

                mouseJointDef.maxForce = 1000.0f * paddleBody.getMass();

                mouseJoint = (MouseJoint) world.createJoint(mouseJointDef);

                mouseJoint.setTarget(new Vector2(x, STAGE_H - y));
            //}
        }
        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchDragged(int x, int y, int p) {
        if (mouseJoint != null) {

            mouseJoint.setTarget(new Vector2(x, STAGE_H - y));
        }
        return super.touchDragged(x, y, p);
    }
    
    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (mouseJoint != null) {

            world.destroyJoint(mouseJoint);
            mouseJoint = null;
        }
        return super.touchUp(x, y, pointer, button);
    }
    

}

