package pl.wat.pks.recylerModels;

import lombok.Data;



@Data public class Setting {
    private  String currencyName;
    private  boolean notifficationBool;
    private  double exchangeRateMax;
    private  double exchangeRateMin;

    public Setting() {
        this.currencyName = "unknown";
        this.notifficationBool = False;
        this.exchangeRateMax = 0;
        this.exchangeRateMin = 0;
    }
}
