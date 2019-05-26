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
public class CurencyInBTCDTO {

    @NonNull
    @SerializedName("last")
    private final float lastPriceOfBTC;

    @NonNull
    @SerializedName("symbol")
    private final char symbol;



}
