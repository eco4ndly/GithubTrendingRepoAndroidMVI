package com.eco4ndly.githubtrendingrepo.main.module

import com.eco4ndly.githubtrendingrepo.main.MainState
import com.eco4ndly.githubtrendingrepo.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * A Sayan Porya code on 14/05/20
 */
val mainActivityModule = module {
  viewModel { (initialState: MainState) -> MainViewModel(initialState) }
}