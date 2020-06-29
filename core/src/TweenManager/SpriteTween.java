package TweenManager;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteTween implements TweenAccessor<Sprite> {

    public static final int alpha = 1;
    @Override
    public int getValues(Sprite sprite, int i, float[] floats) {

        switch (i){
            case alpha: floats[0] = sprite.getColor().a; return 1;
            default: return 0;
        }

    }

    @Override
    public void setValues(Sprite sprite, int i, float[] floats) {
        switch (i){
            case alpha: 
                sprite.setColor(1, 1, 1, floats[0]);
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }
}
