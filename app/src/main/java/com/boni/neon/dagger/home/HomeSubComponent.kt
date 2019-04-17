package com.boni.neon.dagger.home

import com.boni.neon.ui.home.HomeFragment
import dagger.Subcomponent

@HomeScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent {
    fun inject(homeFragment: HomeFragment)
}