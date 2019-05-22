package pl.wat.pks.models.dto;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class CryptoDTO {

    @NonNull
    @SerializedName("status")
    private final String status;
    @NonNull
    @SerializedName("name")
    private final String name;
    @NonNull
    @SerializedName("unit")
    private final String unit;
    @NonNull
    @SerializedName("period")
    private final String period;
    @NonNull
    @SerializedName("values")
    private final List<XYCorordinates> coordniateList = new ArrayList<>();

}
