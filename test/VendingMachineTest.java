import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {
    @Test
    public void vendingMachineShowsInsertCoinByDefault(){
        VendingMachine machine = new VendingMachine();
        assertEquals( "INSERT COIN", machine.getDisplay());
    }

    @Test
    public void vendingMachineIdentifiesCoinsByWeight() {
        VendingMachine machine = new VendingMachine();
        assertEquals( VendingMachine.CoinType.UNKNOWN_COIN, machine.detectCoin(0));
        assertEquals( VendingMachine.CoinType.PENNY, machine.detectCoin(9));
        assertEquals( VendingMachine.CoinType.NICKEL, machine.detectCoin(14));
        assertEquals( VendingMachine.CoinType.DIME, machine.detectCoin(4));
        assertEquals( VendingMachine.CoinType.QUARTER, machine.detectCoin(19));
    }
}
