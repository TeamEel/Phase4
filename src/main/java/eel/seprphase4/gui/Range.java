package eel.seprphase4.gui;

/**
 *
 * @author James
 */
public class Range {

    public static String[] formatted(String path, int from, int to) {
        int count = Math.abs(to - from) + 1;

        String[] paths = new String[count];

        if (to > from) {
            for (int i = 0; from + i <= to; i++) {
                paths[i] = String.format(path, from + i);
            }
        } else {
            for (int i = 0; from - i >= to; i++) {
                paths[i] = String.format(path, from - i);
            }
        }
        return paths;
    }
}
