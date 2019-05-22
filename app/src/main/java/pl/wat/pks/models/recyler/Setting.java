package pl.wat.pks.models.recyler;

import android.graphics.drawable.Drawable;

import lombok.Data;


@Data
public class Setting {

    private  String currencyName;
    private  boolean notificationBool;
    private  double exchangeRateMax;
    private  double exchangeRateMin;
    private Drawable currencyIcon;

    public Setting() {
        this.currencyName = "unknown";
        this.notificationBool = false;
        this.exchangeRateMax = 0;
        this.exchangeRateMin = 0;
    }
}
