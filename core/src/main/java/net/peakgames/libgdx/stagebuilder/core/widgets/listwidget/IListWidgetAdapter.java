package net.peakgames.libgdx.stagebuilder.core.widgets.listwidget;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;

public interface IListWidgetAdapter<T> {

    void initialize(List<T> items);

    int getCount();

    boolean isEmpty();

    T getItem(int position);

    void addItem(T item);

    /**
     *
     * @param position position of the list item in the adapter.
     * @param reusableActor if reusableActor is null you should create one. If it is not null update it and list widget will use updated list item.
     * @return an actor that all fields are populated with data at position
     */
    Actor getActor(int position, Actor reusableActor);

    void notifyDataSetChanged();

    void notifyDataSetChanged(boolean resetPosition);

    void registerDataSetChangeListener(ListWidgetDataSetChangeListener listener);

    void actorRemoved(Actor actor);

}
