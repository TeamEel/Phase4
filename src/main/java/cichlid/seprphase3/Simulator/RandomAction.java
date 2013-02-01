package cichlid.seprphase3.Simulator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public enum RandomAction {
    Nothing,
    reactorControlRodsRaise, reactorControlRodsLower,
    valve1Close,
    coolantPumpOff,
    pumpOff;
    
    private static final List<RandomAction> randomActionList = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int size = randomActionList.size();
    private static final Random random = new Random();

    public static RandomAction pickRandom() {
        return randomActionList.get(random.nextInt(size));
    }
}
