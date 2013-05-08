package eel.seprphase4.gui.zlist;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author David
 */
public class FIter<E> implements Iterator<E> {

    private Iterator<Pair<E>> iterator;

    public FIter(ArrayList<Pair<E>> list) {
        this.iterator = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public void remove() {
        iterator.remove();
    }

    @Override
    public E next() {
        return iterator.next().element;
    }
}
