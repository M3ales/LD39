package org.epoxide.ld39;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import org.epoxide.ld39.client.render.RenderManager;
import org.epoxide.ld39.client.render.lighting.LightMap;
import org.epoxide.ld39.entity.EntityPlayer;
import org.epoxide.ld39.world.World;

public class LD39 extends ApplicationAdapter {

    public static final float tileWidth = 32f;
    public static final String ID = "ld39";

    public static LD39 instance;

    private double STEP = 1d / 120d;
    private double prevTime;
    private double accumulator = 0;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private RenderManager renderManager;
    public EntityPlayer entityPlayer;
    private World world;
    private LightMap lightMap;

    @Override
    public void create() {
        LD39.instance = this;

        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.renderManager = new RenderManager();
        this.world = new World();
        this.entityPlayer = new EntityPlayer(this.world);
        this.lightMap = new LightMap();
        //this.lightMap.adjustSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render() {
        double currentTime = TimeUtils.millis() / 1000.0;
        double frameTime = Math.min(currentTime - prevTime, 0.25);
        final float delta = (float) frameTime;

        prevTime = currentTime;
        accumulator += frameTime;

        while (accumulator >= STEP) {
            accumulator -= STEP;
            updateGame(delta);
        }

        renderGame(delta);
    }

    private void renderGame(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.batch.setProjectionMatrix(this.camera.combined);
        this.lightMap.render(this.batch);

//        this.renderManager.renderGame(batch, delta);

    }

    private void updateGame(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        this.camera.setToOrtho(false, width, height);
    	this.lightMap.adjustSize(width, height);
    }

    @Override
    public void dispose() {
    }
}
