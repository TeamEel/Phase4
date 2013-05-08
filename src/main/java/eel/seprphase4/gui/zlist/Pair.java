package eel.seprphase4.gui.zlist;

/**
 *
 * @author David
 */
class Pair<E> implements Comparable<Pair> {

    public E element;
    private Integer z;

    public Pair(E element, int z) {
        this.element = element;
        this.z = z;
    }

    @Override
    public int compareTo(Pair other) {
        return z.compareTo(other.z);
    }
}
