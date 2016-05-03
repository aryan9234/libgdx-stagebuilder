package net.peakgames.libgdx.stagebuilder.core;

import java.util.*;

import android.app.Activity;
import android.widget.TextView;
import net.peakgames.libgdx.stagebuilder.core.assets.Assets;
import net.peakgames.libgdx.stagebuilder.core.assets.AssetsInterface;
import net.peakgames.libgdx.stagebuilder.core.assets.ResolutionHelper;
import net.peakgames.libgdx.stagebuilder.core.assets.StageBuilderFileHandleResolver;
import net.peakgames.libgdx.stagebuilder.core.keyboard.KeyboardManager;
import net.peakgames.libgdx.stagebuilder.core.keyboard.SoftKeyboardEventInterface;
import net.peakgames.libgdx.stagebuilder.core.services.LocalizationService;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGame implements ApplicationListener {

    public static final int TARGET_WIDTH = 800;
    public static final int TARGET_HEIGHT = 480;
    private static final String TAG = AbstractGame.class.getSimpleName();
    private final Screen NULL_SCREEN = new NullScreen();
    final private Stack<Screen> screens = new Stack<Screen>();
    private int width;
    private int height;
    private int targetWidth;
    private int targetHeight;
    private List<Vector2> supportedResolutions;
    private Screen topScreen = NULL_SCREEN;
    private ResolutionHelper resolutionHelper;
    private AssetsInterface assetsInterface;
    private StageBuilderFileHandleResolver fileHandleResolver;
    private SoftKeyboardEventInterface softKeyboardEventInterface;
    private KeyboardManager keyboardManager;
    private Activity activity;

    public abstract List<Vector2> getSupportedResolutions();
    public abstract LocalizationService getLocalizationService();

    public void initialize(int width, int height, int targetWidth, int targetHeight) {
        this.width = width;
        this.height = height;
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        this.supportedResolutions = getSupportedResolutions();
        fileHandleResolver = new StageBuilderFileHandleResolver(this.width, supportedResolutions);
        this.resolutionHelper = new ResolutionHelper(targetWidth, targetHeight, width, height, fileHandleResolver.findBestResolution().x);
        this.assetsInterface = new Assets(fileHandleResolver, resolutionHelper);
        this.keyboardManager = new KeyboardManager(height);
    }
    
    public void initialize(Activity activity, int width, int height) {
        this.activity = activity;
    	initialize(width, height, TARGET_WIDTH, TARGET_HEIGHT);
    }

    @Override
    public void create() {
        Gdx.app.log("Game", "create");
    }

    @Override
    public void resize(int newWidth, int newHeight) {
        if (this.width == newWidth && this.height == newHeight) {
            return;
        }
        this.width = newWidth;
        this.height = newHeight;
        int newTargetWidth = targetWidth;
        int newTargetHeight = targetHeight;
        fileHandleResolver = new StageBuilderFileHandleResolver(this.width, supportedResolutions);
        if (this.height > this.width) {
            newTargetWidth = targetHeight;
            newTargetHeight = targetWidth;
        }
        this.resolutionHelper.resize(newTargetWidth,
                newTargetHeight,
                this.width,
                this.height);
        this.topScreen.resize(this.width, this.height);
    }

    @Override
    public void render() {
        this.topScreen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void pause() {
        this.topScreen.pause();
    }

    @Override
    public void resume() {
        this.topScreen.resume();
    }

    @Override
    public void dispose() {
        clearScreens();
    }

    private void clearScreens() {
        for (Screen screen : screens) {
            screen.hide();
            screen.dispose();
            if (screen instanceof AbstractScreen) {
                ((AbstractScreen) screen).unloadAssets();
            }
        }
        this.topScreen = NULL_SCREEN;
        screens.clear();
    }
    
    public void addScreen(Screen screen, Map<String, String> parameters) {
    	((AbstractScreen) screen).setParameters(parameters);
    	addScreen(screen);
    }

    public void addScreen(Screen screen) {
        validateScreen(screen);
        getTopScreen().hide();

        screens.push(screen);
        this.topScreen = getTopScreen();
        displayTopScreen();
    }

    private void validateScreen(Screen screen) {
        if (screen == null) {
            throw new RuntimeException("Screen can not be null.");
        }
    }

    public void replaceTopScreen(Screen screen) {
        validateScreen(screen);
        if(screens.isEmpty()) {
            setScreen(screen);
        } else {
            Screen topScreen = getTopScreen();
            topScreen.hide();
            topScreen.dispose();
            screens.pop();
            screens.push(screen);
            this.topScreen = getTopScreen();
            displayTopScreen();
        }
    }

    public void replaceTopScreen(Screen screen, Map<String, String> parameters) {
        ((AbstractScreen) screen).setParameters(parameters);
        replaceTopScreen(screen);
    }

    private void unloadAssets() {
        ((AbstractScreen) getTopScreen()).unloadAssets();
    }

    private void displayTopScreen() {
    	updateKeyboardManagerStage();
        this.topScreen.show();
    }
    
	private void updateKeyboardManagerStage() {
		if (this.topScreen instanceof AbstractScreen) {        	
    		this.keyboardManager.setStage(((AbstractScreen) getTopScreen()).stage);
    	}
	}

    /**
     * Disposes top screen and shows previous screen.
     */
    public boolean backToPreviousScreen() {
        return goBackToPreviousScreen(null);
    }

    public boolean backToPreviousScreen(Map<String, String> parameters){
        return goBackToPreviousScreen(parameters);
    }

    private boolean goBackToPreviousScreen(Map<String, String> parameters) {
        if(getNumberScreens() <= 1) {
            return false;
        }
        try {
            removeScreen();
            updateTopScreen(parameters);
            return true;
        } catch (EmptyStackException e) {
            Gdx.app.log(TAG, "Can not switch to previous screen. ", e);
        }
        return false;
    }

    private void updateTopScreen(Map<String, String> parameters) {
        this.topScreen = getTopScreen();
        if(topScreen instanceof AbstractScreen){
            addParameters(parameters, (AbstractScreen) topScreen);
        }
        displayTopScreen();
    }

    public void backToPreviousScreenByScreenCount(int screenCountToGoBack, Map<String, String> parameters) {
        for(int i=0;i<screenCountToGoBack;i++) {
            if(getNumberScreens() <= 1) {
                return;
            }
            removeScreen();
        }
        updateTopScreen(parameters);
    }

    public void backToScreen(Class screenClass, Map<String, String> parameters) {
        if(hasScreenInStack(screenClass)) {
            while(!getTopScreen().getClass().getSimpleName().equals(screenClass.getSimpleName())) {
                removeScreen();
            }
            updateTopScreen(parameters);
        }
    }

    private void removeScreen() {
        unloadAssets();
        Screen top = screens.pop();
        top.hide();
        top.dispose();
    }

    private boolean hasScreenInStack(Class screenClass) {
        for(Screen screen : screens) {
            if(screen.getClass().getSimpleName().equals(screenClass.getSimpleName())) {
                return true;
            }
        }
        return false;
    }

    private void addParameters(Map<String, String> params, AbstractScreen screen){
        if(params != null && !params.isEmpty()){
            Map<String, String> mergedMap = new HashMap<String, String>();
            if (screen.parameters != null && !screen.parameters.isEmpty()) {
                mergedMap.putAll(screen.parameters);
            }
            mergedMap.putAll(params);
            screen.setParameters(mergedMap);
        }
    }

    public Screen getScreen() {
        return getTopScreen();
    }
    
    public void updateTopScreen() {
    	this.topScreen = getTopScreen();
    }

    public void setScreen(Screen screen) {
        validateScreen(screen);
        clearScreens();

        screens.push(screen);
        this.topScreen = getTopScreen();

        displayTopScreen();
    }

    public void setScreen(Screen screen, Map<String, String> parameters) {
        ((AbstractScreen) screen).setParameters(parameters);
        setScreen(screen);
    }

    public Stack<Screen> getScreens() {
        return this.screens;
    }

    private Screen getTopScreen() {
        try {
            return screens.peek();
        } catch (EmptyStackException e) {
            return new NullScreen();
        }
    }

    public Screen getPreviousScreen() {
        if (screens.size() > 1) {
            return screens.get(screens.size()-2);
        }

        return null;
    }

    public int getNumberScreens() {
        if (screens == null) {
            return 0;
        }
        return screens.size();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public AssetsInterface getAssetsInterface() {
        return this.assetsInterface;
    }

    public ResolutionHelper getResolutionHelper() {
        return this.resolutionHelper;
    }

    public Vector2 getBestResolution() {
        return this.fileHandleResolver.findBestResolution();
    }

    private static class NullScreen implements Screen {

        @Override
        public void render(float delta) {

        }

        @Override
        public void resize(int width, int height) {

        }

        @Override
        public void show() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void dispose() {

        }
    }

	public KeyboardManager getKeyboardManager() {
		return keyboardManager;
	}
	
	public void setKeyboardManager(KeyboardManager keyboardManager) {
		this.keyboardManager = keyboardManager;
	}
	
	public void setSoftKeyboardEventInterface(SoftKeyboardEventInterface softKeyboardEventInterface) {
		this.softKeyboardEventInterface = softKeyboardEventInterface;
		this.softKeyboardEventInterface.setSoftKeyboardEventListener(keyboardManager);
        keyboardManager.setKeyboardEventService(this.softKeyboardEventInterface);
	}

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}

