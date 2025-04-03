package uk.ac.tees.mad.eventspot.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.ac.tees.mad.eventspot.data.EventDao
import uk.ac.tees.mad.eventspot.data.EventDatabase
import uk.ac.tees.mad.eventspot.data.Repository
import uk.ac.tees.mad.eventspot.data.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):EventDatabase{
        return  EventDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideEventDao(db:EventDatabase):EventDao{
        return db.eventDao()
    }

    @Provides
    @Singleton
    fun provideRepository(eventDao: EventDao):Repository{
        return RepositoryImpl(eventDao)
    }
}