package cl.orlandoormazabal.reigndemoapp.di

import cl.orlandoormazabal.reigndemoapp.connection.NetworkState
import cl.orlandoormazabal.reigndemoapp.connection.NetworkStateImp
import cl.orlandoormazabal.reigndemoapp.data.LocalDataSourceImp
import cl.orlandoormazabal.reigndemoapp.data.RemoteDataSourceImp
import cl.orlandoormazabal.reigndemoapp.domain.LocalDataSource
import cl.orlandoormazabal.reigndemoapp.domain.RemoteDataSource
import cl.orlandoormazabal.reigndemoapp.domain.Repo
import cl.orlandoormazabal.reigndemoapp.domain.RepoImp
import cl.orlandoormazabal.reigndemoapp.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

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
        RepoImp(get(), get(), get())
    }

    viewModel {
        MainViewModel(get())
    }
}