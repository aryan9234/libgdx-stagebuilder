package net.peakgames.libgdx.stagebuilder.core.util;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.peakgames.libgdx.stagebuilder.core.assets.ResolutionHelper;

public class NinePatchUtils {

    public static NinePatchDrawable createNinePatchDrawableFromAtlas(ResolutionHelper resolutionHelper, 
                                                                     String imageName, TextureAtlas textureAtlas,
                                                                     int patchOffsetLeft, int patchOffsetRight,
                                                                     int patchOffsetTop, int patchOffsetBottom) {
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable();
        TextureAtlas.AtlasRegion region = textureAtlas.findRegion(imageName);
        NinePatch patch = new NinePatch(region, patchOffsetLeft, patchOffsetRight, patchOffsetTop, patchOffsetBottom);
        patch.scale(resolutionHelper.getSizeMultiplier(), resolutionHelper.getSizeMultiplier());
        ninePatchDrawable.setPatch(patch);
        return ninePatchDrawable;
    }
}
