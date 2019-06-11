package pl.wat.pks.currency.settings;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;
import pl.wat.pks.R;

import java.util.Random;

@Data
@Entity
public class CurrencySetting {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String currencyName;
    public boolean notificationBool;
    public double exchangeRateMax;
    public double exchangeRateMin;
    public int currencyIcon;
    public double balance;
    public String account;

    public CurrencySetting(int id, String currencyName, boolean notificationBool, double exchangeRateMax, double exchangeRateMin, int currencyIcon, double balance, String account) {
        this.id = id;
        this.currencyName = currencyName;
        this.notificationBool = notificationBool;
        this.exchangeRateMax = exchangeRateMax;
        this.exchangeRateMin = exchangeRateMin;
        this.currencyIcon = currencyIcon;
        this.balance = balance;
        this.account = account;
    }

    public CurrencySetting(String currencyName, boolean notificationBool, double exchangeRateMax, double exchangeRateMin, int currencyIcon, double balance) {
        this.currencyName = currencyName;
        this.notificationBool = notificationBool;
        this.exchangeRateMax = exchangeRateMax;
        this.exchangeRateMin = exchangeRateMin;
        this.currencyIcon = currencyIcon;
        this.balance = balance;
        this.account = "";
    }

    public CurrencySetting() {
    }

    public static CurrencySetting[] populateData() {
        Random random = new Random();
        return new CurrencySetting[]{
                new CurrencySetting("bitocin", false, 0, 0, R.drawable.ic_btc, random.nextDouble() / 1000),
                new CurrencySetting("litecoin", false, 0, 0, R.drawable.ic_ltc, random.nextDouble() / 1000),
                new CurrencySetting("dogecoin", false, 0, 0, R.drawable.ic_doge, random.nextDouble() / 1000),
                new CurrencySetting("etherum", false, 0, 0, R.drawable.ic_eth, random.nextDouble() / 1000)
        };
    }
}
