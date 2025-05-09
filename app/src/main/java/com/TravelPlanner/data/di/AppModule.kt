package com.TravelPlanner.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.TravelPlanner.data.database.DAO.DAO_Activity
import com.TravelPlanner.data.database.DAO.DAO_Travel
import com.TravelPlanner.data.database.DAO.UserDao
import com.TravelPlanner.data.database.database
import com.TravelPlanner.data.repository.User_Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Proveer SharedPreferences
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    // Proveer la base de datos Room
    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext appContext: Context): database {
        return Room.databaseBuilder(appContext, database::class.java, "DB_TravelPlanner")

            .build()
    }

    // Proveer DAO_Travel
    @Provides
    @Singleton
    fun provideTravelDao(db: database): DAO_Travel {
        return db.ObtenerDao()
    }

    // Proveer DAO_Activity
    @Provides
    @Singleton
    fun provideActivityDao(db: database): DAO_Activity {
        return db.ObtenerActivityDao()
    }



    @Provides
    @Singleton
    fun provideUserDao(db: database): UserDao {
        return db.userDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): User_Repo {
        return User_Repo(userDao)
    }
}
