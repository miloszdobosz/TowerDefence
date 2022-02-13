package entities;

import engine.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class Entities<E extends Element> {
    public Collection<E> collection;
    protected ArrayList<E> toAdd = new ArrayList<>();
    protected ArrayList<E> toRemove = new ArrayList<>();

    public Entities(Collection collection) {
        this.collection = collection;
    }

    public void update() {
        collection.forEach(element -> element.update());
        collection.removeAll(toRemove);
        collection.addAll(toAdd);
        toRemove = new ArrayList<>();
        toAdd = new ArrayList<>();
    }

    public void add(E element) {
        element.setParentCollection(this);
        toAdd.add(element);
    }

    public void remove(E element) {
        toRemove.add(element);
    }

    public Stream<E> stream() {
        return collection.stream();
    }
}
