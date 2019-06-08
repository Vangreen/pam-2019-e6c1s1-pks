import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import pl.wat.pks.currency.settings.CurrencySetting;
import pl.wat.pks.currency.settings.CurrencySettingDao;

@Database(entities = {CurrencySetting.class}, version = 1)
public abstract class PksAppDatabase extends RoomDatabase {

    private static PksAppDatabase INSTANCE;

    public static PksAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PksAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PksAppDatabase.class, "pks_app_database").build();
                }
            }
        }
        return INSTANCE;
    }
    public abstract CurrencySettingDao currencySettingDao();
}
