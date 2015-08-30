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

    @Test
    public void vendingMachineIdentifiesValidCoins() {
        VendingMachine machine = new VendingMachine();
        assertEquals( false, machine.isValidCoin(VendingMachine.CoinType.UNKNOWN_COIN));
        assertEquals( false, machine.isValidCoin(VendingMachine.CoinType.PENNY));
        assertEquals( true, machine.isValidCoin(VendingMachine.CoinType.NICKEL));
        assertEquals( true, machine.isValidCoin(VendingMachine.CoinType.DIME));
        assertEquals( true, machine.isValidCoin(VendingMachine.CoinType.QUARTER));
    }

    @Test
    public void vendingMachineAddsValidCoinsValuetoBalance() {
        VendingMachine machine = new VendingMachine();
        assertEquals( "0.05", machine.AddCoin(VendingMachine.CoinType.NICKEL));
        assertEquals( "0.15", machine.AddCoin(VendingMachine.CoinType.DIME));
        assertEquals( "0.40", machine.AddCoin(VendingMachine.CoinType.QUARTER));
    }

    @Test
    public void vendingMachineAcceptsCoins() {
        VendingMachine machine = new VendingMachine();
        assertEquals( true, machine.insertCoin(14));
        assertEquals( "0.05", machine.getDisplay());
        assertEquals( false, machine.insertCoin(20));
        assertEquals( "0.05", machine.getDisplay());
        assertEquals( true, machine.insertCoin(4));
        assertEquals( "0.15", machine.getDisplay());
        assertEquals( false, machine.insertCoin(9));
        assertEquals( "0.15", machine.getDisplay());
        assertEquals( true, machine.insertCoin(19));
        assertEquals( "0.40", machine.getDisplay());
    }
}
