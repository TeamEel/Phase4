package cichlid.seprphase3.TextInterface;

import cichlid.seprphase3.TextInterface.TerminalReader;
import java.io.ByteArrayInputStream;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author David
 */
public class TerminalReaderTest {

    private void setInputToString(String s) {
        System.setIn(new ByteArrayInputStream(s.getBytes()));
    }

    /**
     * Test of readLine method, of class TerminalReader.
     */
    @Test
    public void testReadLine() {
        TerminalReader tr = new TerminalReader();
        setInputToString("test line\n");
        assertEquals("test line", tr.readLine());
    }
}
