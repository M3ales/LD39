package org.epoxide.ld39.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.epoxide.ld39.GameManager;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.vSyncEnabled = false;
        config.foregroundFPS = 0;
        config.backgroundFPS = 0;
        LwjglApplication app = new LwjglApplication(new GameManager(), config);
    }
}
