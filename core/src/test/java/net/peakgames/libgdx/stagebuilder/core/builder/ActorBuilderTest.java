package net.peakgames.libgdx.stagebuilder.core.builder;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import net.peakgames.libgdx.stagebuilder.core.assets.AssetsInterface;
import net.peakgames.libgdx.stagebuilder.core.assets.ResolutionHelper;
import net.peakgames.libgdx.stagebuilder.core.model.BaseModel;
import net.peakgames.libgdx.stagebuilder.core.model.ImageModel;
import net.peakgames.libgdx.stagebuilder.core.services.LocalizationService;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;

public class ActorBuilderTest {

    private AssetsInterface assetsInterface = Mockito.mock(AssetsInterface.class);
    private LocalizationService localizationService = Mockito.mock(LocalizationService.class);

    @Test
    public void testCalculateScreenAlignmentPosition_tall_screen() {
        ResolutionHelper resolutionHelper = new ResolutionHelper(800, 480, 800, 500, 800);

        BaseModel model = new ImageModel();
        model.setX(100);
        model.setY(0);
        model.setWidth(200);
        model.setHeight(50);

        ImageBuilder imageBuilder = new ImageBuilder(assetsInterface, resolutionHelper, localizationService);

        Vector2 position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.TOP, model);
        assertEquals(100.0f, position.x);
        assertEquals(440.0f, position.y);

        position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.BOTTOM, model);
        assertEquals(100.0f, position.x);
        assertEquals(-10.0f, position.y);

        model.setY(150);
        position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.RIGHT, model);
        assertEquals(600f, position.x);
        assertEquals(150.0f, position.y);

        position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.LEFT, model);
        assertEquals(0.0f, Math.abs(position.x));
        assertEquals(150.0f, position.y);
    }

    @Test
    public void testCalculateScreenAlignmentPosition_wide_screen() {
        ResolutionHelper resolutionHelper = new ResolutionHelper(800, 480, 900, 480, 800);
        BaseModel model = new ImageModel();
        model.setX(100);
        model.setY(0);
        model.setWidth(200);
        model.setHeight(50);

        ImageBuilder imageBuilder = new ImageBuilder(assetsInterface, resolutionHelper, localizationService);

        Vector2 position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.TOP, model);
        assertEquals(100.0f, position.x);
        assertEquals(430.0f, position.y);

        position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.BOTTOM, model);
        assertEquals(100.0f, position.x);
        assertEquals(0.0f, Math.abs(position.y));

        model.setY(150);

        position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.RIGHT, model);
        assertEquals(650f, position.x);
        assertEquals(150.0f, position.y);

        position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.LEFT, model);
        assertEquals(-50.0f, position.x);
        assertEquals(150.0f, position.y);
    }


    @Test
    public void testScreenPadding () {
        ResolutionHelper resolutionHelper = new ResolutionHelper(800, 480, 900, 480, 800);
        BaseModel model = new ImageModel();
        model.setX(100);
        model.setY(0);
        model.setWidth(200);
        model.setHeight(50);
        model.setScreenPaddingTop(30);

        ImageBuilder imageBuilder = new ImageBuilder(assetsInterface, resolutionHelper, localizationService);

        Vector2 position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.TOP, model);
        assertEquals(100.0f, position.x);
        assertEquals(400.0f, position.y);

        model.setScreenPaddingBottom(40);
        position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.BOTTOM, model);
        assertEquals(100.0f, position.x);
        assertEquals(40.0f, Math.abs(position.y));

        model.setY(150);
        model.setScreenPaddingRight(20);

        position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.RIGHT, model);
        assertEquals(630f, position.x);
        assertEquals(150.0f, position.y);

        model.setScreenPaddingLeft(100);
        position = imageBuilder.calculateScreenPosition(BaseModel.ScreenAlign.LEFT, model);
        assertEquals(50.0f, position.x);
        assertEquals(150.0f, position.y);

    }
    
    @Test
    public void calculateAlignment() {
        assertEquals(Align.bottom, ActorBuilder.calculateAlignment("bottom"));
        assertEquals(Align.left, ActorBuilder.calculateAlignment("left"));
        assertEquals(Align.top, ActorBuilder.calculateAlignment("top"));
        assertEquals(Align.right, ActorBuilder.calculateAlignment("right"));
        assertEquals(Align.center, ActorBuilder.calculateAlignment("center", Align.left));

        assertEquals(Align.left, ActorBuilder.calculateAlignment("oi center oi"));
        assertEquals(Align.left, ActorBuilder.calculateAlignment("leftTop"));
        assertEquals(Align.center, ActorBuilder.calculateAlignment("", Align.center));
        assertEquals(Align.top, ActorBuilder.calculateAlignment(null, Align.top));

        assertEquals(Align.bottomLeft, ActorBuilder.calculateAlignment("bottom|left"));
        assertEquals(Align.bottomLeft, ActorBuilder.calculateAlignment("left|bottom"));
        assertEquals(Align.bottomLeft, ActorBuilder.calculateAlignment("left | bottom"));
        assertEquals(Align.topLeft, ActorBuilder.calculateAlignment(" top | left "));
        assertEquals(Align.bottom| Align.right | Align.left, ActorBuilder.calculateAlignment("left | bottom | right"));
        assertEquals(Align.topRight, ActorBuilder.calculateAlignment("top|right"));

        assertEquals(Align.bottom, ActorBuilder.calculateAlignment("topitop|bottom"));
    }

}
