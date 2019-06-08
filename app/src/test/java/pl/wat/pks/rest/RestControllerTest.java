package pl.wat.pks.rest;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import pl.wat.pks.models.dto.BTCCurencyListDTO;
import pl.wat.pks.models.dto.CryptoDTO;
import pl.wat.pks.rest.BlockchainApiService;
import pl.wat.pks.rest.RestController;

import static org.junit.Assert.assertEquals;


public class RestControllerTest {

    @Test
    public void getValueForChartTest(){
        Observable<CryptoDTO>cryptoDTOObservable = RestController.getValuesForCharts();
        cryptoDTOObservable
                .subscribeOn(Schedulers.io())
                .subscribe(s -> assertEquals("ok",s.status()));

    }


    @Test
    public void getActualValuesInUSD(){
        Observable<BTCCurencyListDTO> btcCurencyListDTOObservable = RestController.getActualValuesInCurrencies();
        btcCurencyListDTOObservable
                .subscribeOn(Schedulers.io())
                .subscribe(s -> assertEquals("$",s.usd().symbol()));
    }

    @Test
    public void getActualValuesInPLN(){
        Observable<BTCCurencyListDTO> btcCurencyListDTOObservable = RestController.getActualValuesInCurrencies();
        btcCurencyListDTOObservable
                .subscribeOn(Schedulers.io())
                .subscribe(s -> assertEquals("zł",s.usd().symbol()));
    }

    @Test
    public void getActualValuesInEUR(){
        Observable<BTCCurencyListDTO> btcCurencyListDTOObservable = RestController.getActualValuesInCurrencies();
        btcCurencyListDTOObservable
                .subscribeOn(Schedulers.io())
                .subscribe(s -> assertEquals("€",s.usd().symbol()));
    }



}
