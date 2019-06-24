/**
 * Dodatkowa obsługa bd
 * metody do obsługi
 */
package pl.wat.pks.currency.settings;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import pl.wat.pks.database.PksAppDatabase;

public class CurrencySettingRepository {

    private CurrencySettingDao currencySettingDao;
    private LiveData<List<CurrencySetting>> allCurrencySettings;

    CurrencySettingRepository(Application application) {
        PksAppDatabase db = PksAppDatabase.getDatabase(application);
        currencySettingDao = db.currencySettingDao();
        allCurrencySettings = currencySettingDao.getAllCurrencySettings();
    }

    LiveData<List<CurrencySetting>> getAllCurrencySettings() {
        return allCurrencySettings;
    }

    public void delete(final CurrencySetting word) {
        new Thread(() -> currencySettingDao.delete(word)).start();
    }

    public void insert(final CurrencySetting word) {
        new Thread(() -> currencySettingDao.insert(word)).start();
    }

    public void update(final CurrencySetting word) {
        new Thread(() -> currencySettingDao.update(word)).start();
    }

    public void updateAll(final List<CurrencySetting> words) {
        new Thread(() -> currencySettingDao.updateAll(words.toArray(new CurrencySetting[words.size()]))).start();
    }
}
