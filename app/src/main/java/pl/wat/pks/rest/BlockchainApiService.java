package pl.wat.pks.rest;

import io.reactivex.Observable;
import pl.wat.pks.models.dto.BTCCurencyListDTO;
import pl.wat.pks.models.dto.CryptoDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BlockchainApiService {

    @GET("charts/market-price?timespan=8weeks&unit=eur&rollingAverage=8hours&format=json")
    Observable<CryptoDTO> getCrypto();

    @GET("/ticker&format=json")
    Call<BTCCurencyListDTO> getPriceInCurencies();

}
