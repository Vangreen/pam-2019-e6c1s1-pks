package pl.wat.pks.models.dto;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class CrytpoDTO {

    @NonNull
    private final String status;
    @NonNull
    private final String name;
    @NonNull
    private final String unit;
    @NonNull
    private final String period;
    @NonNull
    private final List<XYCorordinates> coordniateList;

}
