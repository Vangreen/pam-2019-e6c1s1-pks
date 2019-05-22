package pl.wat.pks.recylerModels;

import lombok.Data;
import lombok.Getter;


@Data
public class Setting {
    @Getter
    private  String currencyName;
    private  boolean notificationBool;
    @Getter
    private  double exchangeRateMax;
    private  double exchangeRateMin;

    public Setting() {
        this.currencyName = "unknown";
        this.notificationBool = false;
        this.exchangeRateMax = 0;
        this.exchangeRateMin = 0;
    }
}
