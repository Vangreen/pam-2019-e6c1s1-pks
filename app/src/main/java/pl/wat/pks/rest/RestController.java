package pl.wat.pks.rest;

import android.util.Log;
import com.google.gson.Gson;
import pl.wat.pks.models.dto.CryptoDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestController {

    public CryptoDTO getResponse() {
        Retrofit retrofit = createClient();
        return getResponse(retrofit);
    }

    private CryptoDTO getResponse(Retrofit retrofit) {
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
