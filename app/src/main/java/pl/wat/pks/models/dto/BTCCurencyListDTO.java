package pl.wat.pks.models.dto;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class BTCCurencyListDTO {

    @NonNull
    @SerializedName("USD")
    private final CurencyInCryptoDTO usd;

    @NonNull
    @SerializedName("PLN")
    private final CurencyInCryptoDTO pln;

    @NonNull
    @SerializedName("EUR")
    private final CurencyInCryptoDTO eur;

}
