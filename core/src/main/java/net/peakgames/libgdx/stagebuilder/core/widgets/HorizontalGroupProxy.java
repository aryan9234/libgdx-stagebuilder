package net.peakgames.libgdx.stagebuilder.core.widgets;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;

public class HorizontalGroupProxy implements OneDimensionGroupInterface {
    HorizontalGroup group;
    
    public HorizontalGroupProxy() {
        group = new HorizontalGroup();
    }

    @Override
    public OneDimensionGroupInterface padLeft(float left) {
        group.padLeft(left);
        return this;
    }

    @Override
    public OneDimensionGroupInterface padRight(float right) {
        group.padRight(right);
        return this;
    }

    @Override
    public OneDimensionGroupInterface padBottom(float bottom) {
        group.padBottom(bottom);
        return this;
    }

    @Override
    public OneDimensionGroupInterface padTop(float top) {
        group.padTop(top);
        return this;
    }

    @Override
    public OneDimensionGroupInterface pad(float top, float left, float bottom, float right) {
        group.pad(top, left, bottom, right);
        return this;
    }

    @Override
    public OneDimensionGroupInterface space(float spacing) {
        group.space(spacing);
        return this;
    }

    @Override
    public OneDimensionGroupInterface align(int align) {
        group.align(align);
        return this;
    }

    @Override
    public OneDimensionGroupInterface reverse() {
        group.reverse();
        return this;
    }

    @Override
    public OneDimensionGroupInterface fill() {
        group.fill();
        return this;
    }

    @Override
    public Group getGroup() {
        return group;
    }
}
