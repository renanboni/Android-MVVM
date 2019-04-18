package com.boni.neon.di.historic

import com.boni.neon.ui.historic.HistoryFragment
import dagger.Subcomponent

@HistoricScope
@Subcomponent(modules = [HistoricModule::class])
interface HistoricSubComponent {
    fun inject(historyFragment: HistoryFragment)
}