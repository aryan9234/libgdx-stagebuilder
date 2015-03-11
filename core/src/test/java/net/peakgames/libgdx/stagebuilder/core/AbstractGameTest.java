package net.peakgames.libgdx.stagebuilder.core;

import com.badlogic.gdx.math.Vector2;
import com.binarytweed.test.DelegateRunningTo;
import com.binarytweed.test.Quarantine;
import com.binarytweed.test.QuarantiningRunner;
import junit.framework.Assert;
import net.peakgames.libgdx.stagebuilder.core.services.LocalizationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

@RunWith(QuarantiningRunner.class)
@Quarantine({"com.badlogic", "com.binarytweed.libgdx.test", "net.peakgames.libgdx.stagebuilder.core"})
@DelegateRunningTo(LibGdxTestRunner.class)
public class AbstractGameTest {

    AbstractGame game;
    ScreenTestMock screenTop;
    ScreenTestMock screenBack;

    class ScreenTestMock extends AbstractScreen{
        public ScreenTestMock(AbstractGame game) {
            super(game);
        }

        @Override
        public void unloadAssets() {

        }

        @Override
        public void onStageReloaded() {

        }
    }

    @Before
    public void setUp() {
        game = new AbstractGame() {
            @Override
            public List<Vector2> getSupportedResolutions() {
                List<Vector2> resolutions = new ArrayList<Vector2>();
                resolutions.add(new Vector2(800, 480));
                return resolutions;
            }

            @Override
            public LocalizationService getLocalizationService() {
                return null;
            }
        };
        game.initialize(800, 480);

        screenBack = new ScreenTestMock(game);
        screenTop = new ScreenTestMock(game);

        screenBack.setParameters(Collections.EMPTY_MAP);
        screenTop.setParameters(Collections.EMPTY_MAP);

        game.addScreen(screenBack);
        game.addScreen(screenTop);
    }


    @Test
    public void test_addParametersOnCollectionsEMPTY_MAP() {
        // -- //
        game.backToPreviousScreen(Collections.EMPTY_MAP);
        Assert.assertEquals(0, ((AbstractScreen) game.getScreen()).parameters.size());

        game.addScreen(screenTop);
        game.backToPreviousScreen(null);
        Assert.assertEquals(0, ((AbstractScreen)game.getScreen()).parameters.size());

        // -- //
        game.addScreen(screenTop);

        Map<String, String> newMap = new HashMap<String, String>();
        newMap.put("Key0", "Value0");
        game.backToPreviousScreen(newMap);

        Assert.assertEquals("Value0", ((AbstractScreen)game.getScreen()).parameters.get("Key0"));

        // -- //
        game.addScreen(screenTop);
        screenBack.setParameters(Collections.EMPTY_MAP);
        game.backToPreviousScreen(Collections.EMPTY_MAP);

        Assert.assertEquals(0, ((AbstractScreen) game.getScreen()).parameters.size());

        // -- //
        game.addScreen(screenTop);
        screenBack.setParameters(new HashMap<String, String>());
        game.backToPreviousScreen(Collections.EMPTY_MAP);

        Assert.assertTrue(((AbstractScreen) game.getScreen()).parameters instanceof HashMap);
        Assert.assertEquals(0, ((AbstractScreen)game.getScreen()).parameters.size());
    }

    @Test
    public void test_addParametersOnNonEmptyMaps() {
        // -- //
        Map<String, String> backScreenPreviouslyHadThisMap = new HashMap<String, String>();
        backScreenPreviouslyHadThisMap.put("SomeKey", "SomeValue");
        backScreenPreviouslyHadThisMap.put("SomeOtherKey", "SomeOtherValue");
        screenBack.setParameters(backScreenPreviouslyHadThisMap);

        Map<String, String> newMap = new HashMap<String, String>();
        newMap.put("SomeKey", "AfterValue");
        newMap.put("SomeNewKey", "AfterNewValue");

        game.backToPreviousScreen(newMap);
        Assert.assertEquals("AfterValue", ((AbstractScreen) game.getScreen()).parameters.get("SomeKey"));
        Assert.assertEquals("SomeOtherValue", ((AbstractScreen) game.getScreen()).parameters.get("SomeOtherKey"));
        Assert.assertEquals("AfterNewValue", ((AbstractScreen) game.getScreen()).parameters.get("SomeNewKey"));

        // -- //
        screenTop = new ScreenTestMock(game);
        screenBack.setParameters(backScreenPreviouslyHadThisMap);
        game.addScreen(screenTop);
        game.backToPreviousScreen(null);
        Assert.assertNull(((AbstractScreen) game.getScreen()).parameters.get("SomeNewKey"));
        Assert.assertEquals("SomeValue", ((AbstractScreen) game.getScreen()).parameters.get("SomeKey"));

        // -- //
        screenTop = new ScreenTestMock(game);
        screenBack.setParameters(backScreenPreviouslyHadThisMap);
        game.addScreen(screenTop);
        game.backToPreviousScreen(new HashMap<String, String>());
        Assert.assertNull(((AbstractScreen) game.getScreen()).parameters.get("SomeNewKey"));
        Assert.assertEquals("SomeValue", ((AbstractScreen) game.getScreen()).parameters.get("SomeKey"));
    }
}
