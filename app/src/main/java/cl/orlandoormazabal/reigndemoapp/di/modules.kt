package cl.orlandoormazabal.reigndemoapp.di

import cl.orlandoormazabal.reigndemoapp.data.RemoteDataSourceImp
import cl.orlandoormazabal.reigndemoapp.domain.RemoteDataSource
import cl.orlandoormazabal.reigndemoapp.domain.Repo
import cl.orlandoormazabal.reigndemoapp.domain.RepoImp
import cl.orlandoormazabal.reigndemoapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<RemoteDataSource> {
        RemoteDataSourceImp()
    }

    single<Repo> {
        RepoImp(get())
    }

    viewModel {
        MainViewModel(get())
    }
}