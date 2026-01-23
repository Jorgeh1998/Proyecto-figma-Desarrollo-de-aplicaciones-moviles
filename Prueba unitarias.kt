package com.example.app1.vistamodelo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.app1.datos.Emocion
import com.example.app1.datos.RepositorioVersiculos
import com.example.app1.datos.remote.BibliaApiVerse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EmocionesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repositorio: RepositorioVersiculos
    private lateinit var viewModel: EmocionesViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repositorio = mockk()
        viewModel = EmocionesViewModel(repositorio)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `cargarVersiculos actualiza el estado correctamente en caso de exito`() = runTest {
        val emocion = Emocion.ALEGRIA
        val listaFalsa = listOf(BibliaApiVerse("Genesis", 1, 1, "En el principio..."))
        coEvery { repositorio.obtenerVersiculosPorEmocion(emocion) } returns listaFalsa.shuffled()

        viewModel.cargarVersiculos(emocion)
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(false, viewModel.uiState.estaCargando)
        assertEquals(1, viewModel.uiState.versiculos.size)
        assertEquals(null, viewModel.uiState.error)
    }

    @Test
    fun `cargarVersiculos maneja el error correctamente`() = runTest {
        // 1. Preparación (Arrange)
        val emocion = Emocion.TRISTEZA
        val mensajeError = "Error de red simulado"
        // Le decimos al repositorio falso que lance una excepción
        coEvery { repositorio.obtenerVersiculosPorEmocion(emocion) } throws RuntimeException(mensajeError)

        // 2. Acción (Act)
        viewModel.cargarVersiculos(emocion)
        testDispatcher.scheduler.advanceUntilIdle()

        // 3. Verificación (Assert)
        assertEquals(false, viewModel.uiState.estaCargando)
        assertEquals(0, viewModel.uiState.versiculos.size)
        assertEquals(mensajeError, viewModel.uiState.error)
    }
}