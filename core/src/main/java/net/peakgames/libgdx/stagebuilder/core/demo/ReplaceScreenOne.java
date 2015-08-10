package net.peakgames.libgdx.stagebuilder.core.demo;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.peakgames.libgdx.stagebuilder.core.AbstractGame;
import net.peakgames.libgdx.stagebuilder.core.builder.StageBuilder;

public class ReplaceScreenOne extends DemoScreen {

    public ReplaceScreenOne(final AbstractGame game) {
        super(game);
        initializeListeners();
    }

    private void initializeListeners() {
        findButton("replaceScreen2").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ReplaceScreenTwo screenTwo = new ReplaceScreenTwo(game);
                game.replaceTopScreen(screenTwo);
            }
        });
    }


    @Override
    public void show() {
        super.show();
        StageBuilder.disableMultiTouch(this.stage);
    }
}
