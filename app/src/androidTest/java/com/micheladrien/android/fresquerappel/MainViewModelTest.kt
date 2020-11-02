package com.micheladrien.android.fresquerappel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.micheladrien.fresquerappel.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

  //Regle : defini la manière dont les tests vont être menés
  //InstantTaskExecutorRule = force les tests à etre synchrones
  @Rule
  @JvmField
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  @Mock
  val applicationMock: Application = mock(Application::class.java)
  /*
  val applicationMock : Application */

  //Before : set up avant de faire les tests
  @Before
  fun setUpTaskDetailViewModel() {

    //Pas necessaire car cela est geré par RunWith(MockitoJUnitRunner... initMocks(this)

    //val applicationMock : Application = mock(Application::class.java)
    `when`(applicationMock.getString(R.string.collage_climat)).thenReturn("Climat")

    //Simulation des objets necessaires pour la VM. Plus besoin : l'annotation mock le fait


    //private val collageNameObserver: Observer<String> = mock()
    /*
    private val mockVictoryRepository: VictoryRepository = mock()
    */

    //private lateinit var viewModel:MainViewModel

    //viewModel.name.observeForever(collageNameObserver)
    /*viewModel.repository = mockVictoryRepository*/


    //3 : La vm a etre testée
    //viewModel = MainViewModel(applicationMock)

  }

  //Un test
  @Test
  fun setVictoryTitleSavesTitle() {


    //Arrange–Act–Assert pattern

    /*
    val title = "New title"
    viewModel.notifyNewCollage(title)
     */

    //Verify permet de vérifier qu’une méthode a été bien appelée et que que les interactions avec le mock sont celles attendues.
    //Verifie
    //verify(collageNameObserver).onChanged( "New title")

  }

  /* Cette fonction vérifie que la fonction setVictoryTitle a bien été appelée
    @Test
  fun setVictoryTitleSavesTitle() {
    val title = "New title"
    viewModel.setVictoryTitle(title)

    verify(mockVictoryRepository).setVictoryTitle(title)
  }
   */

  /* Stub, a voir plus tard
  private fun stubVictoryRepositoryGetVictoryTitleAndCount(titleAndCount: Pair<String, Int>) {
    stubVictoryRepositoryGetVictoryTitle(titleAndCount.first)
    stubVictoryRepositoryGetVictoryCount(titleAndCount.second)
    whenever(mockVictoryRepository.getVictoryTitleAndCount())
        .thenReturn(titleAndCount)
  }

  private fun stubVictoryRepositoryGetVictoryTitle(title: String) {
    whenever(mockVictoryRepository.getVictoryTitle())
        .thenReturn(title)
  }

  private fun stubVictoryRepositoryGetVictoryCount(count: Int) {
    whenever(mockVictoryRepository.getVictoryCount())
        .thenReturn(count)
  } */
}
