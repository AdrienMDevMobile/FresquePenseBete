package com.micheladrien.android.fresquerappel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.micheladrien.fresquerappel.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class MainViewModelTest {

  //Regle : defini la manière dont les tests vont être menés
  //InstantTaskExecutorRule = force les tests à etre synchrones
  @Rule
  @JvmField
  var instantTaskExecutorRule = InstantTaskExecutorRule()

  //Simulation des objets necessaires pour la VM
  private val applicationMock:Application = mock(Application::class.java)

  //private val collageNameObserver: Observer<String> = mock()
  /*
  private val mockVictoryRepository: VictoryRepository = mock()
  */

  //3 : La vm a etre testée
  private val viewModel = MainViewModel(applicationMock)

  /*
  //Before : set up avant de faire les tests
  @Before
  fun setUpTaskDetailViewModel() {

    viewModel.name.observeForever(collageNameObserver)
    /*viewModel.repository = mockVictoryRepository*/
  }
*/
  //Un test
  @Test
  fun setVictoryTitleSavesTitle() {
    //Arrange–Act–Assert pattern

    val title = "New title"
    viewModel.notifyNewCollage(title)

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
