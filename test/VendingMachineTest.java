import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {
    @Test
    public void vendingMachineShowsInsertCoinByDefault(){
        VendingMachine machine = new VendingMachine();
        assertEquals( "INSERT COIN", machine.getDisplay());
    }
}
