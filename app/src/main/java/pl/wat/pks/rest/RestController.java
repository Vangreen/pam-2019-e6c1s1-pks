package pl.wat.pks.rest;

import android.util.Log;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.Observable;
import pl.wat.pks.models.dto.BTCCurencyListDTO;
import pl.wat.pks.models.dto.CryptoDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestController {

    public static Observable<CryptoDTO> getValuesForCharts() {
        Retrofit retrofit = createClient();
        return getValuesForCharts(retrofit);
    }

    public static Observable<BTCCurencyListDTO> getActualValuesInCurrencies(){
        Retrofit retrofit = createClient();
        Observable<BTCCurencyListDTO> btcCurencyListDTO = RestController.getActualValuesInCurrencies(retrofit);
        return btcCurencyListDTO;
    }

    private static Observable<BTCCurencyListDTO> getActualValuesInCurrencies(Retrofit retrofit) {
        BlockchainApiService blockchainApiService = retrofit.create(BlockchainApiService.class);
        Observable<BTCCurencyListDTO> btcCurencyListDTOObservable = blockchainApiService.getPriceInCurencies();
        return btcCurencyListDTOObservable;
    }

    private static Observable<CryptoDTO> getValuesForCharts(Retrofit retrofit) {
        BlockchainApiService blockchainApiService = retrofit.create(BlockchainApiService.class);
        Observable<CryptoDTO> cryptoDTOObservable = blockchainApiService.getCrypto();
        return cryptoDTOObservable;
    }

    private static Retrofit createClient() {
        return new Retrofit.Builder()
                    .baseUrl("https://api.blockchain.info")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
    }

}
