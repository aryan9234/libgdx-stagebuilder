package net.peakgames.libgdx.stagebuilder.core.builder;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import net.peakgames.libgdx.stagebuilder.core.assets.AssetsInterface;
import net.peakgames.libgdx.stagebuilder.core.assets.ResolutionHelper;
import net.peakgames.libgdx.stagebuilder.core.model.BaseModel;
import net.peakgames.libgdx.stagebuilder.core.model.ImageModel;
import net.peakgames.libgdx.stagebuilder.core.services.LocalizationService;

public class ImageBuilder extends ActorBuilder {

    private static final int MIN_VISIBLE_AREA_FOR_PATCH_REGION = 2;

    public ImageBuilder(AssetsInterface assets, ResolutionHelper resolutionHelper, LocalizationService localizationService) {
        super(assets, resolutionHelper, localizationService);
    }

    @Override
    public Actor build(BaseModel model) {
        ImageModel imageModel = (ImageModel) model;
        Image image;
        if (imageModel.getTextureSrc() != null) {
            image = createFromTexture(imageModel);
        } else {
            image = createFromTextureAtlas(imageModel);
        }

        normalizeModelSize(imageModel,
                image.getDrawable().getMinWidth(),
                image.getDrawable().getMinHeight());

        setBasicProperties(model, image);

        if (ImageModel.TYPE_BACKGROUND.equals(imageModel.getType())) {
            updateBackgroundImagePosition(image);
        }

        return image;
    }

    private void updateBackgroundImagePosition(Image image) {
        Vector2 selectedResolution = assets.findBestResolution();
        Vector2 backGroundSize = resolutionHelper.calculateBackgroundSize(selectedResolution.x, selectedResolution.y);
        image.setWidth(backGroundSize.x);
        image.setHeight(backGroundSize.y);

        Vector2 backGroundPosition = resolutionHelper.calculateBackgroundPosition(image.getWidth(), image.getHeight());
        Vector2 gameAreaPosition = resolutionHelper.getGameAreaPosition();
          /*
  		 * stage root position is always set to gameAreaPosition.
  		 * Since the bg image is also inside the root group, bg image position should be updated.
		 */
        image.setPosition(backGroundPosition.x - gameAreaPosition.x, backGroundPosition.y - gameAreaPosition.y);
    }

    private Image createFromTexture(ImageModel imageModel) {
        if(imageModel.isNinepatch()){
            NinePatchDrawable ninePatchDrawable = new NinePatchDrawable();

            NinePatch patch;
            if (imageModel.getNinepatchOffset() == 0) {

                patch = new NinePatch(new TextureRegion(assets.getTexture(getLocalizedString(imageModel.getTextureSrc()))),
                        imageModel.getNinepatchOffsetLeft(), imageModel.getNinepatchOffsetRight(), imageModel.getNinepatchOffsetTop(), imageModel.getNinepatchOffsetBottom());
            } else {
                patch = new NinePatch(new TextureRegion(assets.getTexture(getLocalizedString(imageModel.getTextureSrc()))),
                        imageModel.getNinepatchOffset(), imageModel.getNinepatchOffset(), imageModel.getNinepatchOffset(), imageModel.getNinepatchOffset());
            }

            ninePatchDrawable.setPatch(patch);
            if(imageModel.getMinFilter() != null && imageModel.getMagFilter() != null) {
                patch.getTexture().setFilter(Texture.TextureFilter.valueOf(imageModel.getMinFilter()), Texture.TextureFilter.valueOf(imageModel.getMagFilter()));
            }
            return new Image(patch);
        }else{
            TextureRegion textureRegion = new TextureRegion(assets.getTexture(getLocalizedString(imageModel.getTextureSrc())));
            if(imageModel.getMinFilter() != null && imageModel.getMagFilter() != null) {
                textureRegion.getTexture().setFilter(Texture.TextureFilter.valueOf(imageModel.getMinFilter()), Texture.TextureFilter.valueOf(imageModel.getMagFilter()));
            }
            if (imageModel.isFlipX() || imageModel.isFlipY()) {
                textureRegion = new TextureRegion(textureRegion);
                textureRegion.flip(imageModel.isFlipX(), imageModel.isFlipY());
            }
            return new Image(textureRegion);
        }
    }

    private Image createFromTextureAtlas(ImageModel imageModel) {
        TextureAtlas textureAtlas = assets.getTextureAtlas(imageModel.getAtlasName());
        if(imageModel.isNinepatch()){
            if(imageModel.getNinepatchOffset() != 0) {
                imageModel.applyNinepatchValueForAllParts(imageModel.getNinepatchOffset());
            }
            normalizeNinePatchValues(imageModel, textureAtlas);
            return new Image(createNinePatchDrawable(imageModel.getFrame(), textureAtlas, imageModel.getNinepatchOffsetLeft(),
                        imageModel.getNinepatchOffsetRight(), imageModel.getNinepatchOffsetTop(), imageModel.getNinepatchOffsetBottom()));

        }else{
            TextureAtlas.AtlasRegion atlasRegion = textureAtlas.findRegion(getLocalizedString(imageModel.getFrame()));
            if (imageModel.isFlipX() || imageModel.isFlipY()) {
                atlasRegion = new TextureAtlas.AtlasRegion(atlasRegion);
                atlasRegion.flip(imageModel.isFlipX(), imageModel.isFlipY());
            }
            return new Image(atlasRegion);
        }
    }

    private NinePatchDrawable createNinePatchDrawable(String imageName, TextureAtlas textureAtlas ,int patchOffsetLeft, int patchOffsetRight, int patchOffsetTop, int patchOffsetBottom) {
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable();
        TextureAtlas.AtlasRegion region = textureAtlas.findRegion(imageName);
        NinePatch patch = new NinePatch(region, patchOffsetLeft, patchOffsetRight, patchOffsetTop, patchOffsetBottom);
        patch.scale(resolutionHelper.getSizeMultiplier(), resolutionHelper.getSizeMultiplier());
        ninePatchDrawable.setPatch(patch);
        return ninePatchDrawable;
    }

    private void normalizeNinePatchValues(ImageModel imageModel, TextureAtlas textureAtlas) {
        TextureAtlas.AtlasRegion ninePatchRegion = textureAtlas.findRegion(imageModel.getFrame());
        imageModel.setNinepatchOffsetLeft((int) (imageModel.getNinepatchOffsetLeft() * resolutionHelper.getPositionMultiplier()));
        imageModel.setNinepatchOffsetRight((int)(imageModel.getNinepatchOffsetRight() * resolutionHelper.getPositionMultiplier()));
        imageModel.setNinepatchOffsetTop((int)(imageModel.getNinepatchOffsetTop() * resolutionHelper.getPositionMultiplier()));
        imageModel.setNinepatchOffsetBottom((int)(imageModel.getNinepatchOffsetBottom() * resolutionHelper.getPositionMultiplier()));
        normalizeHorizontalPatches(imageModel, ninePatchRegion);
        normalizeVerticalPatches(imageModel, ninePatchRegion);
    }

    private void normalizeHorizontalPatches(ImageModel imageModel, TextureAtlas.AtlasRegion ninePatchRegion) {
        int totalPatchWidth = imageModel.getNinepatchOffsetLeft() + imageModel.getNinepatchOffsetRight();
        if(totalPatchWidth >= ninePatchRegion.getRegionWidth()) {
            int usableAreaWidth = calculateMinUsableAreaSize(ninePatchRegion.getRegionWidth());
            imageModel.setNinepatchOffsetLeft(calculateNewPatchSize(usableAreaWidth, imageModel.getNinepatchOffsetLeft(), totalPatchWidth));
            imageModel.setNinepatchOffsetRight(calculateNewPatchSize(usableAreaWidth, imageModel.getNinepatchOffsetRight(), totalPatchWidth));
        }
    }

    private void normalizeVerticalPatches(ImageModel imageModel, TextureAtlas.AtlasRegion ninePatchRegion) {
        int totalPatchHeight = imageModel.getNinepatchOffsetTop() + imageModel.getNinepatchOffsetBottom();
        if(totalPatchHeight >= ninePatchRegion.getRegionHeight()) {
            int usableAreaHeight = calculateMinUsableAreaSize(ninePatchRegion.getRegionHeight());
            imageModel.setNinepatchOffsetTop(calculateNewPatchSize(usableAreaHeight, imageModel.getNinepatchOffsetTop(), totalPatchHeight ));
            imageModel.setNinepatchOffsetBottom(calculateNewPatchSize(usableAreaHeight, imageModel.getNinepatchOffsetBottom(), totalPatchHeight ));
        }
    }

    private static int calculateMinUsableAreaSize(int size) {
        return size - MIN_VISIBLE_AREA_FOR_PATCH_REGION;
    }

    private static int calculateNewPatchSize(int usableAreaSize, int patchSize, int totalPatchSize) {
        return (int)(usableAreaSize * (patchSize / (float)totalPatchSize));
    }

}
