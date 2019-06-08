package pl.wat.pks.models.recycler;

import android.graphics.drawable.Drawable;

import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CurrencySettingModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public   String currencyName;
    public  boolean notificationBool;
    public  double exchangeRateMax;
    public  double exchangeRateMin;
    public Drawable currencyIcon;

}
