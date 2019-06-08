package pl.wat.pks.currency.settings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CurrencySettingViewModel extends AndroidViewModel {

    private CurrencySettingRepository repository;

    private LiveData<List<CurrencySetting>> allCurrencySettings;

    public CurrencySettingViewModel(@NonNull Application application) {
        super(application);

        repository = new CurrencySettingRepository(application);
        allCurrencySettings = repository.getAllCurrencySettings();
    }

    public LiveData<List<CurrencySetting>> getAllCurrencySettings() {
        return allCurrencySettings;
    }

    public void insert(CurrencySetting word) {
        repository.insert(word);
    }

    public void delete(CurrencySetting word) {
        repository.delete(word);
    }

    public void update(CurrencySetting word) {
        repository.update(word);
    }
}
