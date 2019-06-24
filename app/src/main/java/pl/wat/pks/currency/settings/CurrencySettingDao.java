/**
 * Obługa bazy danych
 */
package pl.wat.pks.currency.settings;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CurrencySettingDao {
    @Insert
    void insert(CurrencySetting word);

    @Delete
    void delete(CurrencySetting word);

    @Update
    void update(CurrencySetting word);

    @Query("SELECT * from CurrencySetting")
    LiveData<List<CurrencySetting>> getAllCurrencySettings();

    @Insert
    void insertAll(CurrencySetting... currencySettings);

    @Update
    void  updateAll(CurrencySetting... currencySettings);
}
