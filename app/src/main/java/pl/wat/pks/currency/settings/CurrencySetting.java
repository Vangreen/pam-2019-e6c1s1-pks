package pl.wat.pks.currency.settings;

import android.graphics.drawable.Drawable;

import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CurrencySetting {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public   String currencyName;
    public  boolean notificationBool;
    public  double exchangeRateMax;
    public  double exchangeRateMin;
    public Drawable currencyIcon;

}
