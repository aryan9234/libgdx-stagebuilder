package net.peakgames.libgdx.stagebuilder.core.model;

import com.badlogic.gdx.utils.Align;
import net.peakgames.libgdx.stagebuilder.core.assets.ResolutionHelper;

import java.util.ArrayList;
import java.util.List;

public class OneDimensionGroupModel extends BaseModel {
    public enum Orientation {
        VERTICAL, HORIZONTAL
    }
    
    public static final int DEFAULT_ALIGNMENT = Align.center;
    
    private Orientation orientation = Orientation.VERTICAL; 
    private int align;
    private boolean reverse;
    private boolean fill;
    private float spacing;
    private float padTop, padLeft, padBottom, padRight;
    private float[] pads; //0: top, 1: left, 2: bottom, 3: right
    private List<BaseModel> children = new ArrayList<BaseModel>();
    
    public OneDimensionGroupModel(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<BaseModel> getChildren() {
        return children;
    }

    public void setChildren(List<BaseModel> children) {
        this.children = children;
    }

    public int getAlign() {
        return align;
    }

    public void setAlign(int align) {
        this.align = align;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public float getSpacing() {
        return spacing;
    }

    public void setSpacing(float spacing) {
        this.spacing = spacing;
    }

    public float getPadTop() {
        return padTop;
    }

    public void setPadTop(float padTop) {
        this.padTop = padTop;
    }

    public float getPadLeft() {
        return padLeft;
    }

    public void setPadLeft(float padLeft) {
        this.padLeft = padLeft;
    }

    public float getPadBottom() {
        return padBottom;
    }

    public void setPadBottom(float padBottom) {
        this.padBottom = padBottom;
    }

    public float getPadRight() {
        return padRight;
    }

    public void setPadRight(float padRight) {
        this.padRight = padRight;
    }

    public float[] getPads() {
        return pads;
    }

    public void setPads(float[] pads) {
        this.pads = pads;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void normalize(ResolutionHelper helper) {
        float pos = helper.getPositionMultiplier();

        setSpacing(getSpacing() * pos);

        setPadLeft(getPadLeft() * pos);
        setPadTop(getPadTop() * pos);
        setPadRight(getPadRight() * pos);
        setPadBottom(getPadBottom() * pos);
        float[] pads = getPads();
        if (pads != null) {
            for (int i = 0; i < pads.length; i++) {
                pads[i] *= pos;
            }
        }
    }
}
