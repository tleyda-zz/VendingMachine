/**
 * Created by tleyda on 8/29/2015.
 */
public class VendingMachine {
    public enum CoinType {
        PENNY,
        NICKEL,
        DIME,
        QUARTER,
        UNKNOWN_COIN
    }

    public String getDisplay() {
        return "INSERT COIN";
    }

    public CoinType detectCoin(Integer coinWeight) {
        if ( coinWeight > 0 ) {
            if ( coinWeight < 5 )
                return CoinType.DIME;

            if ( coinWeight < 10 )
                return CoinType.PENNY;

            if ( coinWeight < 15 )
                return CoinType.NICKEL;

            if ( coinWeight < 20 )
                return CoinType.QUARTER;
        }

        return CoinType.UNKNOWN_COIN;
    }
}
