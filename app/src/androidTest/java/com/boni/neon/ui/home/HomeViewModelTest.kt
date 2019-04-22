package com.boni.neon.ui.home

import com.boni.domain.usecases.GetUser
import com.boni.neon.mapper.UserViewMapper
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    lateinit var getUser: GetUser

    var userViewMapper: UserViewMapper = UserViewMapper()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        homeViewModel = HomeViewModel(
            getUser,
            userViewMapper
        )
    }
}