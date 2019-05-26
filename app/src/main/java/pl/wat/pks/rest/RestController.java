package pl.wat.pks.rest;

import android.util.Log;
import pl.wat.pks.models.dto.BTCCurencyListDTO;
import pl.wat.pks.models.dto.CryptoDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestController {

    public CryptoDTO getValuesForCharts() {
        Retrofit retrofit = createClient();
        return getValuesForCharts(retrofit);
    }

    public static BTCCurencyListDTO getActualValuesInCurrencies(){
        Retrofit retrofit = createClient();
        BTCCurencyListDTO btcCurencyListDTO = RestController.getActualValuesInCurrencies(retrofit);
        return btcCurencyListDTO;
    }

    private static BTCCurencyListDTO getActualValuesInCurrencies(Retrofit retrofit) {
        BlockchainApiService blockchainApiService = retrofit.create(BlockchainApiService.class);
        Call<BTCCurencyListDTO> btcCurencyListDTOcall = blockchainApiService.getPriceInCurencies();
        final BTCCurencyListDTO[] btcCurencyListDTO = {null};
        btcCurencyListDTOcall.enqueue(new Callback<BTCCurencyListDTO>() {
            @Override
            public void onResponse(Call<BTCCurencyListDTO> call, Response<BTCCurencyListDTO> response) {
                btcCurencyListDTO[0] = response.body();
                Log.d("Ustawienia", response.body().toString());
            }

            @Override
            public void onFailure(Call<BTCCurencyListDTO> call, Throwable t) {
                Log.e(getClass().getSimpleName(), "Błąd w pobraniu aktualnej wartości");
            }
        });
        return btcCurencyListDTO[0];
    }

    private CryptoDTO getValuesForCharts(Retrofit retrofit) {
        BlockchainApiService blockchainApiService = retrofit.create(BlockchainApiService.class);
        Call<CryptoDTO> cryptoDTOCall = blockchainApiService.getCrypto();
        final CryptoDTO[] cryptoDTO = {null};
        cryptoDTOCall.enqueue(new Callback<CryptoDTO>() {
            @Override
            public void onResponse(Call<CryptoDTO> call, Response<CryptoDTO> response) {
                cryptoDTO[0] = response.body();
                            }

            @Override
            public void onFailure(Call<CryptoDTO> call, Throwable t) {
                Log.e(getClass().getSimpleName(), "Błąd w pobraniu API");
            }
        });
        return cryptoDTO[0];

    }

    private static Retrofit createClient() {
        return new Retrofit.Builder()
                    .baseUrl("https://api.blockchain.info")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }

}
