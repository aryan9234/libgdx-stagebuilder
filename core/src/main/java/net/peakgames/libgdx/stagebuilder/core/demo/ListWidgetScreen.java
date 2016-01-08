package net.peakgames.libgdx.stagebuilder.core.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.peakgames.libgdx.stagebuilder.core.AbstractGame;
import net.peakgames.libgdx.stagebuilder.core.demo.widgets.listwidget.ComplexListAdapter;
import net.peakgames.libgdx.stagebuilder.core.demo.widgets.listwidget.HorizontalListAdapter;
import net.peakgames.libgdx.stagebuilder.core.demo.widgets.listwidget.LabelListAdapter;
import net.peakgames.libgdx.stagebuilder.core.demo.widgets.listwidget.ListItem;
import net.peakgames.libgdx.stagebuilder.core.widgets.listwidget.ListWidget;
import net.peakgames.libgdx.stagebuilder.core.widgets.listwidget.OnItemClickedListener;

import java.util.ArrayList;
import java.util.List;

public class ListWidgetScreen extends DemoScreen {
    public ListWidgetScreen(AbstractGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        List<String> items = new ArrayList<String>();
        for (int j = 1; j <= 15; j++) {
            items.add("list_item " + j);
        }
        final LabelListAdapter listAdapter = new LabelListAdapter(getStageBuilder());
        listAdapter.initialize(items);
        final ListWidget labelListWidget = (ListWidget) findActor("label_list_widget");
        labelListWidget.setListAdapter(listAdapter);



        labelListWidget.setOnItemClickListener(new OnItemClickedListener() {
            @Override
            public void onItemClicked(Object item, Actor view, int position) {
                listAdapter.removeActorAt(position);
                listAdapter.notifyDataSetChanged(false);
            }
        });


        findButton("addLabelItemButton").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                listAdapter.addItem("test " + System.currentTimeMillis());
                listAdapter.notifyDataSetChanged(false);
            }
        });

        findButton("removeLabelItemButton").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                listAdapter.removeTopActor();
                listAdapter.notifyDataSetChanged(false);
            }
        });

        findButton("removeLabelItemButtonFromBottom").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                listAdapter.removeBottomActor();
                listAdapter.notifyDataSetChanged(false);
            }
        });

        final ListWidget complexListWidget = (ListWidget) findActor("complex_list_widget");
        final ComplexListAdapter complexListAdapter = new ComplexListAdapter(getStageBuilder());

        final List<ListItem> complexItemList = new ArrayList<ListItem>();
        for (int i = 0; i < 20; i++) {
            complexItemList.add(ListItem.generateRandom());

        }
        complexListAdapter.initialize(complexItemList);
        complexListWidget.setListAdapter(complexListAdapter);

        findButton("addComplexItemButton").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                complexListAdapter.addItem(ListItem.generateRandom());
                complexListAdapter.notifyDataSetChanged(false);
            }
        });

        findButton("removeComplexItemButton").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                complexListAdapter.removeTopActor();
                complexListAdapter.notifyDataSetChanged(false);
            }
        });

        findButton("removeAllComplexItemsButton").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                complexItemList.clear();
                complexListAdapter.notifyDataSetChanged(false);
            }
        });

        complexListWidget.setOnItemClickListener(new OnItemClickedListener() {
            @Override
            public void onItemClicked(Object item, Actor view, int position) {
                Gdx.app.log(TAG, item + " clicked postion : " + position + " actor " + view);
            }
        });
        
        addHorizontalList();
    }

    private void addHorizontalList() {
        final ListWidget horizontalListWidget = (ListWidget) findActor("horizontal_list_widget");
        final HorizontalListAdapter horizontalListAdapter = new HorizontalListAdapter(getStageBuilder());

        final List<ListItem> horizontalListItems = new ArrayList<ListItem>();
        for (int i = 0; i < 20; i++) {
            horizontalListItems.add(ListItem.generateRandom());

        }
        horizontalListAdapter.initialize(horizontalListItems);
        horizontalListWidget.setListAdapter(horizontalListAdapter);

        findButton("removeTopHorizontalItem").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                horizontalListAdapter.removeTopActor();
                horizontalListAdapter.notifyDataSetChanged(false);
            }
        });

        findButton("removeBotHorizontalItem").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                horizontalListAdapter.removeBottomActor();
                horizontalListAdapter.notifyDataSetChanged(false);
            }
        });

        findButton("addHorizontalItem").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                horizontalListAdapter.addItem(ListItem.generateRandom());
                horizontalListAdapter.notifyDataSetChanged(false);
            }
        });
    }

    float sumDelta = 0;

    @Override
    public void render(float delta) {
        super.render(delta);
        sumDelta += delta;
        if (sumDelta > 1) {
            sumDelta = 0;
            Label fpsLabel = findLabel("fps");
            fpsLabel.setText("FPS : " + Gdx.graphics.getFramesPerSecond());
        }
    }
}
