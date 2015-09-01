import java.text.DecimalFormat;

public class VendingMachine {
    public enum CoinType {
        PENNY,
        NICKEL,
        DIME,
        QUARTER,
        UNKNOWN_COIN
    }

    public enum DisplayState {
        INSERT_COINS,
        BALANCE,
        PRICE,
        THANK_YOU
    }

    private Float balance;
    private Float price;
    private DisplayState displayState;

    public VendingMachine() {

        balance = 0.0f;
        price = 0.0f;
        displayState = DisplayState.INSERT_COINS;
    }

    public String getDisplay() {
        String display = null;
        DecimalFormat format = new DecimalFormat("0.00");

        switch ( displayState ) {
            case INSERT_COINS:
                display = "INSERT COINS";
                break;

            case PRICE:
                display = "PRICE " + format.format(price);
                if ( balance > 0.0f ) {
                    displayState = DisplayState.BALANCE;
                }
                else {
                    displayState = DisplayState.INSERT_COINS;
                }
                break;

            case BALANCE:
                display = format.format( balance );
                break;

            case THANK_YOU:
                display = "THANK YOU";
                displayState = DisplayState.INSERT_COINS;
                break;
        }

        return display;
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
                displayState = DisplayState.BALANCE;
                balance += 0.05f;
                break;

            case DIME:
                displayState = DisplayState.BALANCE;
                balance += 0.10f;
                break;

            case QUARTER:
                displayState = DisplayState.BALANCE;
                balance += 0.25f;
                break;

            default:
                break;
        }

        return getDisplay();
    }

    public enum ButtonType {
        COLA,
        CHIPS,
        CANDY
    }

    public String buttonPress(ButtonType button) {
        switch( button ) {
            case COLA:
                price = 1.0f;
                break;

            case CHIPS:
                price = 0.5f;
                break;

            case CANDY:
                price = 0.65f;
                break;
        }

        if( price > balance ) {
            displayState = DisplayState.PRICE;
        }
        else {
            displayState = DisplayState.THANK_YOU;
            balance = 0.0f;
        }

        DecimalFormat format = new DecimalFormat("0.00");

        return format.format( price );
    }
}
