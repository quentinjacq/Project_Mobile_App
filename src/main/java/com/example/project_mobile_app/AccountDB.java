package com.example.project_mobile_app;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Account.class}, version = 3, exportSchema = false)
public abstract class AccountDB extends RoomDatabase {
    private static AccountDB instance;
    public abstract AccountDao accountDao();

    public static synchronized AccountDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AccountDB.class, "account_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private AccountDao accountDao;
        private PopulateDbAsyncTask(AccountDB db) {
            accountDao = db.accountDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
