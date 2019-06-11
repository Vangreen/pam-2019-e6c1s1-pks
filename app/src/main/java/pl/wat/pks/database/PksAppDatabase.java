package pl.wat.pks.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

import pl.wat.pks.currency.settings.CurrencySetting;
import pl.wat.pks.currency.settings.CurrencySettingDao;

@Database(entities = {CurrencySetting.class}, version = 1)
public abstract class PksAppDatabase extends RoomDatabase {

    private static PksAppDatabase INSTANCE;

    public static PksAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PksAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }
    public abstract CurrencySettingDao currencySettingDao();

    private static PksAppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                PksAppDatabase.class,
                "pks_app_db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getDatabase(context).currencySettingDao().insertAll(CurrencySetting.populateData());
                            }
                        });
                    }
                })
                .build();
    }

}
