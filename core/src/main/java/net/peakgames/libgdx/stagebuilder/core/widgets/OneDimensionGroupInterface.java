package net.peakgames.libgdx.stagebuilder.core.widgets;

import com.badlogic.gdx.scenes.scene2d.Group;

public interface OneDimensionGroupInterface {
    OneDimensionGroupInterface padLeft(float left);
    OneDimensionGroupInterface padRight(float right);
    OneDimensionGroupInterface padBottom(float bottom);
    OneDimensionGroupInterface padTop(float top);
    OneDimensionGroupInterface pad(float top, float left, float bottom, float right);
    OneDimensionGroupInterface space(float spacing);
    OneDimensionGroupInterface align(int align);
    OneDimensionGroupInterface reverse();
    OneDimensionGroupInterface fill();
    Group getGroup();
}
