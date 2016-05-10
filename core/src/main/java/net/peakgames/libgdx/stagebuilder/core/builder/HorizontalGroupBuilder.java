package net.peakgames.libgdx.stagebuilder.core.builder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import net.peakgames.libgdx.stagebuilder.core.assets.AssetsInterface;
import net.peakgames.libgdx.stagebuilder.core.assets.ResolutionHelper;
import net.peakgames.libgdx.stagebuilder.core.model.BaseModel;
import net.peakgames.libgdx.stagebuilder.core.model.HorizontalGroupModel;
import net.peakgames.libgdx.stagebuilder.core.model.OneDimensionGroupModel;
import net.peakgames.libgdx.stagebuilder.core.services.LocalizationService;

import java.util.List;
import java.util.Map;

public class HorizontalGroupBuilder extends ActorBuilder {

    private final Map<Class<? extends BaseModel>, ActorBuilder> builders;

    public HorizontalGroupBuilder(Map<Class<? extends BaseModel>, ActorBuilder> builders, AssetsInterface assets,
                                  ResolutionHelper resolutionHelper, LocalizationService localizationService) {
        super(assets, resolutionHelper, localizationService);
        this.builders = builders;
    }

    @Override
    public Actor build(BaseModel model) {
        HorizontalGroupModel horizontalModel = (HorizontalGroupModel)model;
        HorizontalGroup horizontalGroup = new HorizontalGroup();
        normalizeModelSize(model, model.getWidth(), model.getHeight());
        setBasicProperties(model, horizontalGroup);
        horizontalModel.normalize(resolutionHelper);
        
        if (horizontalModel.getAlign() != OneDimensionGroupModel.DEFAULT_ALIGNMENT) {
            horizontalGroup.align(horizontalModel.getAlign());
        }
        horizontalGroup.space(horizontalModel.getSpacing());

        horizontalGroup.pad(horizontalModel.getPadTop(), horizontalModel.getPadLeft(),
                horizontalModel.getPadBottom(), horizontalModel.getPadRight());
        if (horizontalModel.getPads() != null) {
            float[] pads = horizontalModel.getPads();
            horizontalGroup.pad(pads[0], pads[1], pads[2], pads[3]);
        }
        
        if (horizontalModel.isReverse()) horizontalGroup.reverse();
        if (horizontalModel.isFill()) horizontalGroup.fill();
        
        List<BaseModel> children = horizontalModel.getChildren();
        
        for(BaseModel childModel: children) {
            Actor actor = builders.get(childModel.getClass()).build(childModel);
            horizontalGroup.addActor(actor);
        }
        
        return horizontalGroup;
    }
}
