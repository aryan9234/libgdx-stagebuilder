package net.peakgames.libgdx.stagebuilder.core.demo.widgets.listwidget;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import net.peakgames.libgdx.stagebuilder.core.builder.StageBuilder;
import net.peakgames.libgdx.stagebuilder.core.widgets.listwidget.ListWidgetAdapter;

public class LabelListAdapter extends ListWidgetAdapter<String> {

    public LabelListAdapter(StageBuilder stageBuilder) {
        super(stageBuilder);
    }

    @Override
    public Actor getActor(int position, Actor reusableActor) {
        if (reusableActor == null) {
            try {
                Group group = stageBuilder.buildGroup("listwidget/list_item_simple.xml");
                String value = getItem(position);
                Label label =  group.findActor("list_item_label");
                label.setText(value);
                return group;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String value = getItem(position);
            Label label = ((Group)reusableActor).findActor("list_item_label");
            label.setText(value);
            return reusableActor;
        }
        return null;
    }

    //For testing dynamic removal
    public void removeTopActor() {
        removeActorAt(0);
    }

    public void removeBottomActor() {
        removeActorAt(this.items.size() - 1);
    }

    public void removeActorAt(int index) {
        if (index >= 0 && index < items.size()) {
            this.items.remove(index);
        }
    }
}
