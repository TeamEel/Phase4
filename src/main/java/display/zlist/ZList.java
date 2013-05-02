package display.zlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Provide a list of items with Z-orders
 *
 * @author David
 */
public class ZList<E> implements Iterable<E> {

    private ArrayList<Pair<E>> elements;

    public class ReverseZList<E> implements Iterable<E> {

        private ZList<E> zlist;

        public ReverseZList(ZList<E> zlist) {
            this.zlist = zlist;
        }

        @Override
        public Iterator<E> iterator() {
            return new RIter<E>(zlist.elements);
        }
    }

    public ZList() {
        this.elements = new ArrayList<Pair<E>>();
    }

    public ReverseZList<E> backwards() {
        return new ReverseZList<E>(this);
    }

    public void add(E element, int z) {
        elements.add(new Pair(element, z));
        Collections.sort(elements);
    }

    @Override
    public Iterator<E> iterator() {
        return new FIter(elements);
    }
}
