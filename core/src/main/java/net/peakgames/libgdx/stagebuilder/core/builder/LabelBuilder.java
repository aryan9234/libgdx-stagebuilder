package net.peakgames.libgdx.stagebuilder.core.builder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.TextView;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import net.peakgames.libgdx.stagebuilder.core.assets.AssetsInterface;
import net.peakgames.libgdx.stagebuilder.core.assets.ResolutionHelper;
import net.peakgames.libgdx.stagebuilder.core.model.BaseModel;
import net.peakgames.libgdx.stagebuilder.core.model.LabelModel;
import net.peakgames.libgdx.stagebuilder.core.services.LocalizationService;
import net.peakgames.libgdx.stagebuilder.core.util.GdxUtils;

import java.io.ByteArrayOutputStream;

public class LabelBuilder extends ActorBuilder {

    public static final Color DEFAULT_LABEL_COLOR = Color.WHITE;
    private Activity activity;

    public LabelBuilder(Activity activity, AssetsInterface assets, ResolutionHelper resolutionHelper, LocalizationService localizationService) {
        super(assets, resolutionHelper, localizationService);
        this.activity = activity;
    }

    @Override
    public Actor build(BaseModel model) {
        LabelModel labelModel = (LabelModel) model;
        Color color = labelModel.getFontColor() == null ? DEFAULT_LABEL_COLOR : Color.valueOf(labelModel.getFontColor());
        Label.LabelStyle style = new Label.LabelStyle(assets.getFont(labelModel.getFontName()), color);
        Label label = new Label(getLocalizedString(labelModel.getText()).replace("\\n", String.format("%n")), style);
        normalizeModelSize(labelModel, 0, 0);
        setBasicProperties(model, label);

        label.setAlignment(calculateAlignment(labelModel.getAlignment()));
        label.setWrap(labelModel.isWrap());



        if (labelModel.isFontAutoScale()) {
            autoScaleLabel(label);
        } else if (labelModel.getFontScale() != 1) {
            label.setFontScale(label.getStyle().font.getScaleX() * labelModel.getFontScale());
        } else if (labelModel.getLabelScale() != 0) {
            float scaleLabelWidth = labelModel.getLabelScale() * resolutionHelper.getPositionMultiplier();
            scaleLabel(label, scaleLabelWidth);
        }

        style.background = getImageDrawable(label, labelModel);
        label.setText("");

        return label;
    }

    private Drawable getImageDrawable(Label label, LabelModel labelModel) {
        TextView textView = new TextView(this.activity);
        textView.setText(getLocalizedString(labelModel.getText()).replace("\\n", String.format("%n")));
        textView.setDrawingCacheEnabled(true);
        textView.setTextColor(label.getStyle().fontColor.toIntBits());
        textView.setBackgroundColor(android.graphics.Color.TRANSPARENT);
        textView.measure((int) label.getWidth(), (int)label.getPrefHeight());
        textView.layout(0, 0, (int) label.getWidth(), (int)label.getPrefHeight());
        textView.buildDrawingCache(true);

        Bitmap mb = Bitmap.createBitmap((int) label.getWidth(), (int)label.getPrefHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(mb);
        textView.draw(c);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        mb.compress(Bitmap.CompressFormat.PNG, 50, bos);
        byte[] compressedData = bos.toByteArray();
        Pixmap pixmap = new Pixmap(compressedData, 0, compressedData.length);
        Image image = new Image(new Texture(pixmap));
        textView.setDrawingCacheEnabled(false);
        return image.getDrawable();
    }

    private void autoScaleLabel(Label label) {
        scaleLabel(label, label.getWidth());
    }

    private static void scaleLabel(Label label, float labelWidth){

        float labelTextWidth = GdxUtils.getTextWidth(label) /label.getFontScaleX();
        float scaleDownFactor = labelWidth / labelTextWidth;
        if (labelTextWidth > labelWidth) {
            label.setFontScale(label.getStyle().font.getScaleX() * scaleDownFactor);
        }
    }

}
