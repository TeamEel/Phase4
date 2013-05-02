package display.zlist;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author David
 */
public class ZListTest {
    
    private ZList<String> zlist;
    
    @Before
    public void createZlist() {
        zlist = new ZList<String>();
    }
    
    @Test
    public void shouldSort() {
        zlist.add("first", 1);
        zlist.add("third", 3);
        zlist.add("second", 2);
        String expected[] = {"first", "second", "third"};
        iterationShouldYield(zlist, expected);
    }
    
    @Test
    public void shouldReverse() {
        zlist.add("first", 1);
        zlist.add("second", 2);
        zlist.add("third", 3);
        String expected[] = {"third", "second", "first"};
        iterationShouldYield(zlist.backwards(), expected);
    }
    
    private void iterationShouldYield(Iterable<String> list, String[] expected) {
        int i = 0;
        for (String s : list) {
            assertEquals(expected[i++], s);
        }
    }
}