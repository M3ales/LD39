package org.epoxide.ld39.input;

import org.epoxide.commons.registry.Identifier;
import org.epoxide.commons.registry.NamedRegistry;
import org.epoxide.ld39.LD39;
import org.epoxide.ld39.input.keybind.KeyBind;
import org.epoxide.ld39.input.keybind.KeyBindDebug;
import org.epoxide.ld39.input.keybind.KeyBindPause;
import org.epoxide.ld39.input.keybind.KeyMovement;
import org.epoxide.ld39.util.Direction;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {

    // TODO Centralize registries
    public static final NamedRegistry<KeyBind> REGISTRY = new NamedRegistry<>();

    public static final KeyBind UP = registerKeyBind("up", new KeyMovement(Direction.UP, Keys.W, Keys.UP));
    public static final KeyBind DOWN = registerKeyBind("down", new KeyMovement(Direction.DOWN, Keys.S, Keys.DOWN));
    public static final KeyBind LEFT = registerKeyBind("left", new KeyMovement(Direction.LEFT, Keys.A, Keys.LEFT));
    public static final KeyBind RIGHT = registerKeyBind("right", new KeyMovement(Direction.RIGHT, Keys.D, Keys.RIGHT));

    public static final KeyBind DEBUG = registerKeyBind("debug", new KeyBindDebug(Keys.TAB));
    public static final KeyBind PAUSE = registerKeyBind("pause", new KeyBindPause(Keys.E));

    public static KeyBind registerKeyBind (String id, KeyBind bind) {

        bind.setIdentifier(new Identifier(LD39.ID, id));
        REGISTRY.registerValue(bind);
        return bind;
    }

    public void onUpdate (float delta) {

        for (final KeyBind key : REGISTRY) {

            if (key.isPressed()) {

                key.onUpdate(delta);
            }
        }
    }

    @Override
    public boolean keyDown (int keycode) {

        for (final KeyBind key : REGISTRY) {

            if (key.isListeningForCode(keycode)) {

                key.onPressed();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyUp (int keycode) {

        for (final KeyBind key : REGISTRY) {

            if (key.isListeningForCode(keycode)) {

                key.onReleased();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean keyTyped (char character) {

        return false;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved (int screenX, int screenY) {

        return false;
    }

    @Override
    public boolean scrolled (int amount) {

        return false;
    }
}