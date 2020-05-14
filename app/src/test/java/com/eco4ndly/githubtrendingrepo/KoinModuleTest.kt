package com.eco4ndly.githubtrendingrepo

import com.eco4ndly.githubtrendingrepo.data.api.WebService
import com.eco4ndly.githubtrendingrepo.di.modules.networkModule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.core.logger.Level
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

/**
 * A Sayan Porya code on 13/05/20
 */
class KoinModuleTest: AutoCloseKoinTest() {

    @Test
    fun `koin module test`() {
        koinApplication {
            printLogger(Level.DEBUG)
            modules(listOf(networkModule))
        }.checkModules()
    }
}