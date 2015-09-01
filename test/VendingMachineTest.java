import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    VendingMachine machine;

    @Before
    public void setUp() {
        machine = new VendingMachine();
    }

    @Test
    public void vendingMachineShowsInsertCoinByDefault(){
        assertEquals( "INSERT COINS", machine.getDisplay());
    }

    @Test
    public void vendingMachineIdentifiesCoinsByWeight() {
        assertEquals( VendingMachine.CoinType.UNKNOWN_COIN, machine.detectCoin(0));
        assertEquals( VendingMachine.CoinType.PENNY, machine.detectCoin(9));
        assertEquals( VendingMachine.CoinType.NICKEL, machine.detectCoin(14));
        assertEquals( VendingMachine.CoinType.DIME, machine.detectCoin(4));
        assertEquals( VendingMachine.CoinType.QUARTER, machine.detectCoin(19));
    }

    @Test
    public void vendingMachineIdentifiesValidCoins() {
        assertEquals( false, machine.isValidCoin(VendingMachine.CoinType.UNKNOWN_COIN));
        assertEquals( false, machine.isValidCoin(VendingMachine.CoinType.PENNY));
        assertEquals( true, machine.isValidCoin(VendingMachine.CoinType.NICKEL));
        assertEquals( true, machine.isValidCoin(VendingMachine.CoinType.DIME));
        assertEquals( true, machine.isValidCoin(VendingMachine.CoinType.QUARTER));
    }

    @Test
    public void vendingMachineAddsValidCoinsValuetoBalance() {
        assertEquals( "0.05", machine.AddCoin(VendingMachine.CoinType.NICKEL));
        assertEquals( "0.15", machine.AddCoin(VendingMachine.CoinType.DIME));
        assertEquals( "0.40", machine.AddCoin(VendingMachine.CoinType.QUARTER));
    }

    @Test
    public void vendingMachineAcceptsCoins() {
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

    @Test
    public void vendingMachineSetsProductPriceBasedOnButtonPressed() {
        assertEquals( "1.00", machine.buttonPress(VendingMachine.ButtonType.COLA));
        assertEquals( "0.50", machine.buttonPress(VendingMachine.ButtonType.CHIPS));
        assertEquals( "0.65", machine.buttonPress(VendingMachine.ButtonType.CANDY));
    }

    @Test
    public void vendingMachineDisplaysPriceWhenNotEnoughMoneyDepositedAndButtonPressed() {
        machine.buttonPress(VendingMachine.ButtonType.COLA);
        assertEquals("PRICE 1.00", machine.getDisplay());
        machine.buttonPress(VendingMachine.ButtonType.CHIPS);
        assertEquals("PRICE 0.50", machine.getDisplay());
        machine.buttonPress(VendingMachine.ButtonType.CANDY);
        assertEquals("PRICE 0.65", machine.getDisplay());
    }

    @Test
    public void vendingMachineDisplaysInsertCoinsAndBalanceAfterDisplayingPrice() {
        machine.buttonPress(VendingMachine.ButtonType.COLA);
        assertEquals("PRICE 1.00", machine.getDisplay());
        assertEquals("INSERT COINS", machine.getDisplay());
        machine.AddCoin(VendingMachine.CoinType.QUARTER);
        machine.buttonPress(VendingMachine.ButtonType.COLA);
        assertEquals("PRICE 1.00", machine.getDisplay());
        assertEquals("0.25", machine.getDisplay());
    }
}
