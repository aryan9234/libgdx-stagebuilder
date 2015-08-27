package net.peakgames.libgdx.stagebuilder.core.model;

/**
 * An image's texture can be loaded in two different ways:
 * 1. From an atlas file with the specified frame name
 * 2. From file system (individual file)
 */
public class ImageModel extends BaseModel {

    public static final String TYPE_BACKGROUND = "background";
    private String atlasName;
    private String frame;
    //TODO textureSrc relative mi? relative ise nereye gore relative? javadoc'u netlestir.
    private String textureSrc;
    private String type;

    private boolean ninepatch;
    private int ninepatchOffset;
    private int ninepatchOffsetLeft;
    private int ninepatchOffsetRight;
    private int ninepatchOffsetTop;
    private int ninepatchOffsetBottom;

    private String minFilter;
    private String magFilter;

    public String getAtlasName() {
        return atlasName;
    }

    public void setAtlasName(String atlasName) {
        this.atlasName = atlasName;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getTextureSrc() {
        return textureSrc;
    }

    public void setTextureSrc(String textureSrc) {
        this.textureSrc = textureSrc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNinepatch() {return ninepatch; }

    public void setNinepatch(boolean ninepatch) { this.ninepatch = ninepatch; }

    public int getNinepatchOffset() { return ninepatchOffset;  }

    public void setNinepatchOffset(int ninepatchOffset) {  this.ninepatchOffset = ninepatchOffset;    }

    public int getNinepatchOffsetLeft() {
        return ninepatchOffsetLeft;
    }

    public void setNinepatchOffsetLeft(int ninepatchOffsetLeft) {
        this.ninepatchOffsetLeft = ninepatchOffsetLeft;
    }

    public int getNinepatchOffsetRight() {
        return ninepatchOffsetRight;
    }

    public void setNinepatchOffsetRight(int ninepatchOffsetRight) {
        this.ninepatchOffsetRight = ninepatchOffsetRight;
    }

    public int getNinepatchOffsetTop() {
        return ninepatchOffsetTop;
    }

    public void setNinepatchOffsetTop(int ninepatchOffsetTop) {
        this.ninepatchOffsetTop = ninepatchOffsetTop;
    }

    public int getNinepatchOffsetBottom() {
        return ninepatchOffsetBottom;
    }

    public void setNinepatchOffsetBottom(int ninepatchOffsetBottom) {
        this.ninepatchOffsetBottom = ninepatchOffsetBottom;
    }

    public void setMinFilter(String minFilter) {
        this.minFilter = minFilter;
    }

    public void setMagFilter(String magFilter) {
        this.magFilter = magFilter;
    }

    public String getMinFilter() {
        return minFilter;
    }

    public String getMagFilter() {
        return magFilter;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "atlasName='" + atlasName + '\'' +
                ", frame='" + frame + '\'' +
                ", textureSrc='" + textureSrc + '\'' +
                ", type='" + type + '\'' +
                ", ninepatch=" + ninepatch +
                ", ninepatchOffset=" + ninepatchOffset +
                ", ninepatchOffsetLeft=" + ninepatchOffsetLeft +
                ", ninepatchOffsetRight=" + ninepatchOffsetRight +
                ", ninepatchOffsetTop=" + ninepatchOffsetTop +
                ", ninepatchOffsetBottom=" + ninepatchOffsetBottom +
                ", minFilter='" + minFilter + '\'' +
                ", magFilter='" + magFilter + '\'' +
                '}';
    }

    public void applyNinepatchValueForAllParts(int ninepatchOffset) {
        setNinepatchOffsetLeft(ninepatchOffset);
        setNinepatchOffsetRight(ninepatchOffset);
        setNinepatchOffsetTop(ninepatchOffset);
        setNinepatchOffsetBottom(ninepatchOffset);
    }
}
