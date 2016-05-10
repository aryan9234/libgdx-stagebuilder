package net.peakgames.libgdx.stagebuilder.core.builder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import net.peakgames.libgdx.stagebuilder.core.assets.AssetsInterface;
import net.peakgames.libgdx.stagebuilder.core.assets.ResolutionHelper;
import net.peakgames.libgdx.stagebuilder.core.model.BaseModel;
import net.peakgames.libgdx.stagebuilder.core.model.OneDimensionGroupModel;
import net.peakgames.libgdx.stagebuilder.core.model.VerticalGroupModel;
import net.peakgames.libgdx.stagebuilder.core.services.LocalizationService;

import java.util.List;
import java.util.Map;

public class VerticalGroupBuilder extends ActorBuilder {

    private final Map<Class<? extends BaseModel>, ActorBuilder> builders;

    public VerticalGroupBuilder(Map<Class<? extends BaseModel>, ActorBuilder> builders, AssetsInterface assets,
                                ResolutionHelper resolutionHelper, LocalizationService localizationService) {
        super(assets, resolutionHelper, localizationService);
        this.builders = builders;
    }

    @Override
    public Actor build(BaseModel model) {
        VerticalGroupModel verticalModel = (VerticalGroupModel)model;
        VerticalGroup verticalGroup = new VerticalGroup();
        normalizeModelSize(model, model.getWidth(), model.getHeight());
        setBasicProperties(model, verticalGroup);
        verticalModel.normalize(resolutionHelper);
        
        if (verticalModel.getAlign() != OneDimensionGroupModel.DEFAULT_ALIGNMENT) {
            verticalGroup.align(verticalModel.getAlign());
        }
        verticalGroup.space(verticalModel.getSpacing());

        verticalGroup.pad(verticalModel.getPadTop(), verticalModel.getPadLeft(),
                verticalModel.getPadBottom(), verticalModel.getPadRight());
        if (verticalModel.getPads() != null) {
            float[] pads = verticalModel.getPads();
            verticalGroup.pad(pads[0], pads[1], pads[2], pads[3]);
        }
        
        if (verticalModel.isReverse()) verticalGroup.reverse();
        if (verticalModel.isFill()) verticalGroup.fill();

        List<BaseModel> children = verticalModel.getChildren();

        for(BaseModel childModel: children) {
            Actor actor = builders.get(childModel.getClass()).build(childModel);
            verticalGroup.addActor(actor);
        }
        
        return verticalGroup;
    }
}
