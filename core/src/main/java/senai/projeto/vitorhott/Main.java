package senai.projeto.vitorhott;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Vector;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

     Texture backgroundTexture;
     Texture bucketTexture;
     Texture dropTexture;
     Sound dropSound;
     Music music;

     SpriteBatch spriteBatch;
     FitViewport viewport;

    Sprite buckerSprite;

    Vector2 touchPos;

    Array<Sprite> dropSprites;

    float dropTime;

    Rectangle bucketRentangle;
    Rectangle dropRectangle;

    @Override
    public void create() {
        // Inicializa os recursos
        spriteBatch = new SpriteBatch();

        backgroundTexture = new Texture("background.png");
        bucketTexture = new Texture("bucket.png");
        dropTexture = new Texture("drop.png");

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.play();

        viewport = new FitViewport(8, 5);

        buckerSprite = new Sprite(bucketTexture);
        buckerSprite.setSize( 1, 1);

        touchPos = new Vector2();

        dropSprites = new Array<>();

        bucketRentangle = new Rectangle();
        dropRectangle = new Rectangle();

        music.setLooping(true);
        music.setVolume(.5f);
        music.play();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    }


    private void input() {
        float speed = 4f;
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            buckerSprite.translateX(speed * delta);
        } else   if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            buckerSprite.translateX(-speed * delta);
        }

        if (Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            buckerSprite.setCenterX(touchPos.x);
        }
    }

    private void logic() {
        // Atualização da lógica do jogo vai aqui

        float worldWidht = viewport.getWorldHeight();
        float  worldHeight = viewport.getWorldHeight();

        float bucketWidth = bucketTexture.getWidth();
        float bucketHeigt = buckerSprite.getHeight();

        buckerSprite.setX(MathUtils.clamp(buckerSprite.getX(),0, worldWidht - bucketHeigt));

        float delta  =  Gdx.graphics.getDeltaTime();

        bucketRentangle.set(buckerSprite.getX(), buckerSprite.getY(), bucketWidth,bucketHeigt);

        for(int i = dropSprites.size - 1; i >= 0; i--){
            Sprite dropeSprite =  dropSprites.get(i);
            float dropWidth = dropeSprite.getWidth();
            float  dropHeight = dropeSprite.getHeight();


            dropeSprite.translateY((-2 * delta));
            dropRectangle.set(dropeSprite.getX(), dropeSprite.getY(), dropWidth, dropHeight);

            if (dropeSprite.getY() < - dropHeight) dropSprites.removeIndex(i);
            else if (bucketRentangle.overlaps(dropRectangle)){
                dropSprites.removeIndex(i);
                dropSound.play();
            }


        }

        dropTime += delta;
        if (dropTime > 1f) {
            dropTime = 0;
            cretaDroplet();
        }

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        float  worldWidth  = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();


        spriteBatch.draw(backgroundTexture, 0, 0, worldWidth,worldHeight);
        spriteBatch.draw(bucketTexture,0, 0, 1, 1);

        for(Sprite dropSprite: dropSprites){
            dropSprite.draw(spriteBatch);
        }

        spriteBatch.end();

    }
    private  void cretaDroplet(){
        float dropWidht = 1;
        float dropHeight = 1;
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();


        Sprite dropSprite = new Sprite(dropTexture);
        dropSprite.setSize(dropWidht, dropHeight);
        dropSprite.setX(MathUtils.random(0f, worldWidth - worldHeight));
        dropSprite.setY(worldHeight);
        dropSprites.add(dropSprite);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        backgroundTexture.dispose();
        bucketTexture.dispose();
        dropTexture.dispose();
        dropSound.dispose();
        music.dispose();
    }
}
