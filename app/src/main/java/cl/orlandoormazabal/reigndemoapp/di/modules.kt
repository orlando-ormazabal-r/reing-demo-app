package cl.orlandoormazabal.reigndemoapp.di

import android.content.Context
import androidx.room.Room
import cl.orlandoormazabal.reigndemoapp.base.connection.NetworkState
import cl.orlandoormazabal.reigndemoapp.base.connection.NetworkStateImp
import cl.orlandoormazabal.reigndemoapp.data.LocalDataSourceImp
import cl.orlandoormazabal.reigndemoapp.data.RemoteDataSourceImp
import cl.orlandoormazabal.reigndemoapp.base.database.AppDataBase
import cl.orlandoormazabal.reigndemoapp.domain.*
import cl.orlandoormazabal.reigndemoapp.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    fun provideDataBase(context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, "data_base").build()

    fun provideHitDao(context: Context) = provideDataBase(context).hitDao()
    fun provideHitIdDao(context: Context) = provideDataBase(context).hitIdDao()

    single { provideHitDao(androidContext()) }

    single { provideHitIdDao(androidContext()) }

    single<NetworkState> {
        NetworkStateImp(androidContext())
    }

    single<LocalDataSource> {
        LocalDataSourceImp()
    }

    single<RemoteDataSource> {
        RemoteDataSourceImp()
    }

    single<Repo> {
        RepoImp(get(), get(), get(), get(), get())
    }

    viewModel {
        MainViewModel(get())
    }
}