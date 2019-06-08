package pl.wat.pks.models.dto;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class CurencyInCryptoDTO {

    @NonNull
    @SerializedName("15m")
    private  final float _15m;
    @NonNull
    @SerializedName("last")
    private  final float last;
    @NonNull
    @SerializedName("buy")
    private final float buy;
    @NonNull
    @SerializedName("sell")
    private final float sell;
    @NonNull
    @SerializedName("symbol")
    private final String symbol;



}
