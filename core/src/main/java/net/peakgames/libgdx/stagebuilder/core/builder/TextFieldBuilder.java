package net.peakgames.libgdx.stagebuilder.core.builder;

import net.peakgames.libgdx.stagebuilder.core.assets.AssetsInterface;
import net.peakgames.libgdx.stagebuilder.core.assets.ResolutionHelper;
import net.peakgames.libgdx.stagebuilder.core.model.BaseModel;
import net.peakgames.libgdx.stagebuilder.core.model.TextFieldModel;
import net.peakgames.libgdx.stagebuilder.core.services.LocalizationService;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TextFieldBuilder extends ActorBuilder{

    public TextFieldBuilder(AssetsInterface assets, ResolutionHelper resolutionHelper, LocalizationService localizationService) {
        super(assets, resolutionHelper, localizationService);
    }

    @Override
    public Actor build(BaseModel model) {
        TextFieldModel textFieldModel = (TextFieldModel)model;
        
        BitmapFont font = assets.getFont(textFieldModel.getFontName());
        Color fontColor = Color.valueOf(textFieldModel.getFontColor());
        
        TextureAtlas textureAtlas = assets.getTextureAtlas(textFieldModel.getAtlasName());

        NinePatchDrawable cursor = convertTextureRegionToNinePatchDrawable(
                textureAtlas.findRegion(textFieldModel.getCursorImageName()),
                textFieldModel.getCursorOffset(),
                textFieldModel.getCursorOffset(),
                textFieldModel.getCursorOffset(),
                textFieldModel.getCursorOffset());
        cursor.getPatch().setColor(fontColor);

        NinePatchDrawable selection = convertTextureRegionToNinePatchDrawable(
                textureAtlas.findRegion(textFieldModel.getSelectionImageName()),
                textFieldModel.getSelectionOffset(),
                textFieldModel.getSelectionOffset(),
                textFieldModel.getSelectionOffset(),
                textFieldModel.getSelectionOffset());

        NinePatchDrawable background = null;

        if(textFieldModel.getBackgroundImageName() != null) {
            if (textFieldModel.isBackgroundUsingPatchSize()) {
                background = convertTextureRegionToNinePatchDrawable(
                        textureAtlas.findRegion(textFieldModel.getBackgroundImageName()),
                        textFieldModel.getBackgroundPatchSizeLeft(),
                        textFieldModel.getBackgroundPatchSizeRight(),
                        textFieldModel.getBackgroundPatchSizeTop(),
                        textFieldModel.getBackgroundPatchSizeBottom());
            } else {
                background = convertTextureRegionToNinePatchDrawable(
                        textureAtlas.findRegion(textFieldModel.getBackgroundImageName()),
                        textFieldModel.getBackgroundOffset(),
                        textFieldModel.getBackgroundOffset(),
                        textFieldModel.getBackgroundOffset(),
                        textFieldModel.getBackgroundOffset());
            }

            background.setLeftWidth(textFieldModel.getPadding());
            background.setRightWidth(textFieldModel.getPadding());
            background.setBottomHeight(textFieldModel.getPadding());
            background.setTopHeight(textFieldModel.getPadding());
        }
        
        TextFieldStyle textFieldStyle = new TextFieldStyle(font, fontColor, cursor, selection, background);
        TextField textField = new TextField(getLocalizedString(textFieldModel.getText()), textFieldStyle);
        textField.setPasswordMode(textFieldModel.isPassword());
        textField.setPasswordCharacter(textFieldModel.getPasswordChar().charAt(0));
        if(textFieldModel.getHint() != null) textField.setMessageText(getLocalizedString(textFieldModel.getHint()));
        normalizeModelSize(model, model.getWidth(), model.getHeight());
        setBasicProperties(model, textField);
        
        return textField;
    }

    protected void updateDrawableSize( TextureRegionDrawable textureRegionDrawable){
        float sizeMultiplier = resolutionHelper.getSizeMultiplier();
        textureRegionDrawable.setMinWidth( textureRegionDrawable.getMinWidth() * sizeMultiplier);
        textureRegionDrawable.setMinHeight( textureRegionDrawable.getMinHeight() * sizeMultiplier);
    }
}
