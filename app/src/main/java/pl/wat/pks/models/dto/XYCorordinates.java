package pl.wat.pks.models.dto;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class XYCorordinates {

    @NonNull
    @SerializedName("x")
    private final long time;
    @NonNull
    @SerializedName("y")
    private final float price;

}
