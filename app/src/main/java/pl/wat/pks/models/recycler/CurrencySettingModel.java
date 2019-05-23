package pl.wat.pks.models.recycler;

import android.graphics.drawable.Drawable;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CurrencySettingModel {

    private  String currencyName;
    private  boolean notificationBool;
    private  double exchangeRateMax;
    private  double exchangeRateMin;
    private Drawable currencyIcon;

//    public CurrencySettingModel() {
//        this.currencyName = "unknown";
//        this.notificationBool = false;
//        this.exchangeRateMax = 0;
//        this.exchangeRateMin = 0;
//    }
}
