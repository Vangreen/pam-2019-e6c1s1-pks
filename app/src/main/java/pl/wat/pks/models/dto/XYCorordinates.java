package pl.wat.pks.models.dto;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class XYCorordinates {

    @NonNull
    private final long time;
    @NonNull
    private final float price;

}
