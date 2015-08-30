import java.text.DecimalFormat;

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

    private Float balance;

    public VendingMachine() {
        balance = 0.0f;
    }

    public String getDisplay() {

        if( balance > 0.0f ) {
            DecimalFormat format = new DecimalFormat("0.00");

            return format.format( balance );
        }

        return "INSERT COIN";
    }

    public boolean insertCoin(Integer coinWeight) {
        CoinType coin = detectCoin(coinWeight);

        if( isValidCoin(coin)) {
            AddCoin(coin);
            return true;
        }

        return false;
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

    public boolean isValidCoin(CoinType coin) {
        if ((coin == CoinType.UNKNOWN_COIN) || (coin == CoinType.PENNY))
            return false;

        return true;
    }

    public String AddCoin(CoinType coin) {
        switch (coin) {
            case NICKEL:
                balance += 0.05f;
                break;

            case DIME:
                balance += 0.10f;
                break;

            case QUARTER:
                balance += 0.25f;
                break;

            default:
                break;
        }

        DecimalFormat format = new DecimalFormat("0.00");

        return format.format( balance );
    }
}
