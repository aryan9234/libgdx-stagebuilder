package net.peakgames.libgdx.stagebuilder.core.model;

public class ButtonModel extends BaseModel {
    private String atlasName;
    private String frameUp;
    private String frameDown;
    private String frameDisabled;
    private String frameChecked;
    private String textureSrcUp;
    private String textureSrcDown;
    private String textureSrcDisabled;
    private String textureSrcChecked;
    
    private String tintColorUp;
    private String tintColorDown;
    private String tintColorDisabled;
    private String tintColorChecked;
    
    private boolean isNinePatch;
    private int npTop;
    private int npBottom;
    private int npLeft;
    private int npRight;

    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }

    public String getFrameUp() {
        return frameUp;
    }

    public void setFrameUp(String frameUp) {
        this.frameUp = frameUp;
    }

    public String getFrameDown() {
        return frameDown;
    }

    public void setFrameDown(String frameDown) {
        this.frameDown = frameDown;
    }

    public String getFrameDisabled() {
        return frameDisabled;
    }

    public void setFrameDisabled(String frameDisabled) {
        this.frameDisabled = frameDisabled;
    }

    public String getTextureSrcUp() {
        return textureSrcUp;
    }

    public void setTextureSrcUp(String textureSrcUp) {
        this.textureSrcUp = textureSrcUp;
    }

    public String getTextureSrcDown() {
        return textureSrcDown;
    }

    public void setTextureSrcDown(String textureSrcDown) {
        this.textureSrcDown = textureSrcDown;
    }

    public String getTextureSrcDisabled() {
        return textureSrcDisabled;
    }

    public void setTextureSrcDisabled(String textureSrcDisabled) {
        this.textureSrcDisabled = textureSrcDisabled;
    }

    public String getFrameChecked() {
        return frameChecked;
    }

    public void setFrameChecked(String frameChecked) {
        this.frameChecked = frameChecked;
    }

    public String getTextureSrcChecked() {
        return textureSrcChecked;
    }

    public void setTextureSrcChecked(String textureSrcChecked) {
        this.textureSrcChecked = textureSrcChecked;
    }

    public String getTintColorUp() {
        return tintColorUp;
    }

    public void setTintColorUp(String tintColorUp) {
        this.tintColorUp = tintColorUp;
    }

    public String getTintColorDown() {
        return tintColorDown;
    }

    public void setTintColorDown(String tintColorDown) {
        this.tintColorDown = tintColorDown;
    }

    public String getTintColorDisabled() {
        return tintColorDisabled;
    }

    public void setTintColorDisabled(String tintColorDisabled) {
        this.tintColorDisabled = tintColorDisabled;
    }

    public String getTintColorChecked() {
        return tintColorChecked;
    }

    public void setTintColorChecked(String tintColorChecked) {
        this.tintColorChecked = tintColorChecked;
    }

    public int getNpTop() {
        return npTop;
    }

    public void setNpTop(int npTop) {
        this.npTop = npTop;
    }

    public int getNpBottom() {
        return npBottom;
    }

    public void setNpBottom(int npBottom) {
        this.npBottom = npBottom;
    }

    public int getNpLeft() {
        return npLeft;
    }

    public void setNpLeft(int npLeft) {
        this.npLeft = npLeft;
    }

    public int getNpRight() {
        return npRight;
    }

    public void setNpRight(int npRight) {
        this.npRight = npRight;
    }

    public boolean isNinePatch() {
        return isNinePatch;
    }

    public void setNinePatch(boolean ninePatch) {
        isNinePatch = ninePatch;
    }
}
