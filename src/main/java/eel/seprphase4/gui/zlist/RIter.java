package eel.seprphase4.gui.zlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author David
 */
class RIter<E> implements Iterator<E> {

    private ListIterator<Pair<E>> iterator;

    public RIter(ArrayList<Pair<E>> list) {
        this.iterator = list.listIterator(list.size());
    }

    @Override
    public boolean hasNext() {
        return iterator.hasPrevious();
    }

    @Override
    public E next() {
        return iterator.previous().element;
    }

    @Override
    public void remove() {
        iterator.remove();
    }
}
