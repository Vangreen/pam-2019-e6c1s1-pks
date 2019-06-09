package pl.wat.pks.currency.settings;

import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.wat.pks.R;

@Data
@Entity
public class CurrencySetting {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public   String currencyName;
    public  boolean notificationBool;
    public  double exchangeRateMax;
    public  double exchangeRateMin;
    public int currencyIcon;

    public CurrencySetting(int id, String currencyName, boolean notificationBool, double exchangeRateMax, double exchangeRateMin, int currencyIcon) {
        this.id = id;
        this.currencyName = currencyName;
        this.notificationBool = notificationBool;
        this.exchangeRateMax = exchangeRateMax;
        this.exchangeRateMin = exchangeRateMin;
        this.currencyIcon = currencyIcon;
    }

    public CurrencySetting(String currencyName, boolean notificationBool, double exchangeRateMax, double exchangeRateMin, int currencyIcon) {
        this.currencyName = currencyName;
        this.notificationBool = notificationBool;
        this.exchangeRateMax = exchangeRateMax;
        this.exchangeRateMin = exchangeRateMin;
        this.currencyIcon = currencyIcon;
    }

    public CurrencySetting() {}

    public static CurrencySetting[] populateData() {
        return new CurrencySetting[]{
                new CurrencySetting("bitocin", false, 0, 0, R.drawable.ic_btc),
                new CurrencySetting("litecoin", false, 0, 0, R.drawable.ic_ltc),
                new CurrencySetting("dogecoin", false, 0, 0, R.drawable.ic_doge),
                new CurrencySetting("etherum", false, 0, 0, R.drawable.ic_eth)
        };
    }
}
