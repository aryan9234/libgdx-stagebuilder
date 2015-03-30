package net.peakgames.libgdx.stagebuilder.core.model;

public class SelectBoxModel extends BaseModel {
    private String name;

    private String value;
    private String fontName;
    private String fontColor;
    private String fontColorSelected;
    private String fontColorUnselected;

    private String atlasName;
    private String background;
    private String selection;
    private String selectionBackground;


    private int backgroundNinePatchSizeLeft;
    private int backgroundNinePatchSizeRight;
    private int backgroundNinePatchSizeTop;
    private int backgroundNinePatchSizeBottom;

    private int selectionBackgroundNinePatchSizeLeft;
    private int selectionBackgroundNinePatchSizeRight;
    private int selectionBackgroundNinePatchSizeTop;
    private int selectionBackgroundNinePatchSizeBottom;

    private boolean horizontalScrollDisabled;
    private boolean verticalScrollDisabled;

    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;
    private int patchSize;
    private int maxTextWidth;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFontColorSelected() {
        return fontColorSelected;
    }

    public void setFontColorSelected(String fontColorSelected) {
        this.fontColorSelected = fontColorSelected;
    }

    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getSelectionBackground() {
        return selectionBackground;
    }

    public void setSelectionBackground(String selectionBackground) {
        this.selectionBackground = selectionBackground;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public String getFontColorUnselected() {
        return fontColorUnselected;
    }

    public void setFontColorUnselected(String fontColorUnselected) {
        this.fontColorUnselected = fontColorUnselected;
    }

    public int getPatchSize() {
        return patchSize;
    }

    public void setPatchSize(int patchSize) {
        this.patchSize = patchSize;
    }

    public int getMaxTextWidth() {
        return maxTextWidth;
    }

    public void setMaxTextWidth(int maxTextWidth) {
        this.maxTextWidth = maxTextWidth;
    }

    public boolean isVerticalScrollDisabled() {
        return verticalScrollDisabled;
    }

    public void setVerticalScrollDisabled(boolean verticalScrollDisabled) {
        this.verticalScrollDisabled = verticalScrollDisabled;
    }

    public boolean isHorizontalScrollDisabled() {
        return horizontalScrollDisabled;
    }

    public void setHorizontalScrollDisabled(boolean horizontalScrollDisabled) {
        this.horizontalScrollDisabled = horizontalScrollDisabled;
    }

    public int getSelectionBackgroundNinePatchSizeLeft() {
        return selectionBackgroundNinePatchSizeLeft;
    }

    public void setSelectionBackgroundNinePatchSizeLeft(int selectionBackgroundNinePatchSizeLeft) {
        this.selectionBackgroundNinePatchSizeLeft = selectionBackgroundNinePatchSizeLeft;
    }

    public int getSelectionBackgroundNinePatchSizeRight() {
        return selectionBackgroundNinePatchSizeRight;
    }

    public void setSelectionBackgroundNinePatchSizeRight(int selectionBackgroundNinePatchSizeRight) {
        this.selectionBackgroundNinePatchSizeRight = selectionBackgroundNinePatchSizeRight;
    }

    public int getSelectionBackgroundNinePatchSizeTop() {
        return selectionBackgroundNinePatchSizeTop;
    }

    public void setSelectionBackgroundNinePatchSizeTop(int selectionBackgroundNinePatchSizeTop) {
        this.selectionBackgroundNinePatchSizeTop = selectionBackgroundNinePatchSizeTop;
    }

    public int getSelectionBackgroundNinePatchSizeBottom() {
        return selectionBackgroundNinePatchSizeBottom;
    }

    public void setSelectionBackgroundNinePatchSizeBottom(int selectionBackgroundNinePatchSizeBottom) {
        this.selectionBackgroundNinePatchSizeBottom = selectionBackgroundNinePatchSizeBottom;
    }


    public int getBackgroundNinePatchSizeLeft() {
        return backgroundNinePatchSizeLeft;
    }

    public void setBackgroundNinePatchSizeLeft(int backgroundNinePatchSizeLeft) {
        this.backgroundNinePatchSizeLeft = backgroundNinePatchSizeLeft;
    }

    public int getBackgroundNinePatchSizeRight() {
        return backgroundNinePatchSizeRight;
    }

    public void setBackgroundNinePatchSizeRight(int backgroundNinePatchSizeRight) {
        this.backgroundNinePatchSizeRight = backgroundNinePatchSizeRight;
    }

    public int getBackgroundNinePatchSizeTop() {
        return backgroundNinePatchSizeTop;
    }

    public void setBackgroundNinePatchSizeTop(int backgroundNinePatchSizeTop) {
        this.backgroundNinePatchSizeTop = backgroundNinePatchSizeTop;
    }

    public int getBackgroundNinePatchSizeBottom() {
        return backgroundNinePatchSizeBottom;
    }

    public void setBackgroundNinePatchSizeBottom(int backgroundNinePatchSizeBottom) {
        this.backgroundNinePatchSizeBottom = backgroundNinePatchSizeBottom;
    }

}
