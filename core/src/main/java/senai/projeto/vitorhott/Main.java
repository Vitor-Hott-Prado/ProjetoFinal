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
import com.badlogic.gdx.math.Vector2;
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

        buckerSprite.set(MathUtils.clamp(buckerSprite.getX(),0, worldWidht);
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        float  worldWidth  = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();


        spriteBatch.draw(backgroundTexture, 0, 0, worldWidth,worldHeight);
        buckerSprite.draw(spriteBatch);


        spriteBatch.end();
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
