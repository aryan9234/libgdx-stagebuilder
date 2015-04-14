package net.peakgames.libgdx.stagebuilder.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.peakgames.libgdx.stagebuilder.core.demo.StageBuilderDemo;
import net.peakgames.libgdx.stagebuilder.core.keyboard.DummyKeyboardEventService;
import net.peakgames.libgdx.stagebuilder.core.keyboard.SoftKeyboardEventInterface;
import net.peakgames.libgdx.stagebuilder.core.keyboard.SoftKeyboardEventListener;

public class StageBuilderTestDesktop {
    public static void main(String[] args) {
        int width = 1280;
        int height = 800;

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = width;
        config.height = height;

        StageBuilderDemo demo = new StageBuilderDemo();
        demo.initialize(width, height);
        demo.setSoftKeyboardEventInterface(new DummyKeyboardEventService());

        new LwjglApplication(demo, config);
    }
}
