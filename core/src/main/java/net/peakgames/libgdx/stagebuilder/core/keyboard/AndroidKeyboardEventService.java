package net.peakgames.libgdx.stagebuilder.core.keyboard;

import android.graphics.Rect;
import android.view.View;
import com.badlogic.gdx.backends.android.AndroidGraphics;
import java.util.Timer;
import java.util.TimerTask;

public class AndroidKeyboardEventService implements SoftKeyboardEventInterface{

	private static final long LAYOUT_CHANGE_CHECK_PERIOD = 500; //ms
	private static final int MIN_KEYBOARD_HEIGHT = 120;
	private AndroidGraphics graphics;
	private SoftKeyboardEventListener softKeyboardEventListener;
	private int heightDifference;
	private View root;
	private KeyboardState keyboardState = KeyboardState.CLOSED;
	private Rect visibleAreaRectangle;
	private Timer layoutCheckTimer;
	private TimerTask layoutCheckTimerTask;
	private volatile boolean isActive;

	public AndroidKeyboardEventService(AndroidGraphics graphics) {
		this.graphics = graphics;
	}

	public enum KeyboardState {
		OPENED, CLOSED
	}

	@Override
	public void initialize() {
		root = graphics.getView();
		visibleAreaRectangle = new Rect();
		initializeTimer();
	}

	private void initializeTimer() {
		layoutCheckTimer = new Timer();
	}

	private void createTimerTask() {
		layoutCheckTimerTask = new TimerTask() {
			@Override
			public void run() {
				checkLayoutChange();
			}
		};
	}

	private void checkLayoutChange() {
		root.getWindowVisibleDisplayFrame(visibleAreaRectangle);
		int screenHeight = root.getRootView().getHeight();
		int visibleAreaHeight = visibleAreaRectangle.bottom - visibleAreaRectangle.top;
		heightDifference = screenHeight - visibleAreaHeight;

		if (heightDifference > MIN_KEYBOARD_HEIGHT) {
            if(keyboardState == KeyboardState.CLOSED) {
                softKeyboardEventListener.softKeyboardOpened(heightDifference);
                keyboardState = KeyboardState.OPENED;
            }
        } else {
            if(keyboardState == KeyboardState.OPENED) {
                softKeyboardEventListener.softKeyboardClosed(heightDifference);
                keyboardState = KeyboardState.CLOSED;
                isActive = false;
                layoutCheckTimerTask.cancel();
            }
        }
	}

	@Override
	public void setSoftKeyboardEventListener(
			SoftKeyboardEventListener eventListener) {
		this.softKeyboardEventListener = eventListener;
	}

	@Override
	public void focusChanged() {
		if(!isActive) {
			isActive = true;
			createTimerTask();
			layoutCheckTimer.scheduleAtFixedRate(layoutCheckTimerTask, 0, LAYOUT_CHANGE_CHECK_PERIOD);
		}
	}

}
