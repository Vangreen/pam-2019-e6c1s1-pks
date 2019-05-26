package pl.wat.pks.models.dto.curiencies;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class USD  implements Currency{

    @NonNull
    @SerializedName("15m")
    private float _15m;
    @NonNull
    @SerializedName("last")
    private float last;
    @NonNull
    @SerializedName("buy")
    private float buy;
    @NonNull
    @SerializedName("sell")
    private float sell;
    @NonNull
    @SerializedName("symbol")
    private String symbol;
}
