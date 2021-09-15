package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TasksViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    //To avoid repeat the same code
    private lateinit var tasksViewModel: TasksViewModel
    @Before
    fun setupViewModel(){
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }


    //Normal liveData Assertions
    /*@Test
    fun addNewTask_setsNewTaskEvent(){
        //Given a fresh TaskViewModel
        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        //Create an Observer
        val observer = Observer<Event<Unit>> {}
        try {

            // Observe the LiveData forever
            tasksViewModel.newTaskEvent.observeForever(observer)

            // When adding a new task
            tasksViewModel.addNewTask()

            // Then the new task event is triggered
            val value = tasksViewModel.newTaskEvent.value
            assertNotEquals(value?.getContentIfNotHandled(), null)
            //assertThat(value?.getContentIfNotHandled(), (not(nullValue())))

        } finally {
            // Whatever happens, don't forget to remove the observer!
            tasksViewModel.newTaskEvent.removeObserver(observer)
        }
    }*/

    //LiveData Assertions using the LiveDataTestUtil file
    //check that when you call the addNewTask method, the Event for opening the new task window is fired.
    @Test
    fun addNewTask_setsNewTaskEvent(){
        //Given a fresh TaskViewModel
        //val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        // When adding a new task
        tasksViewModel.addNewTask()

        // Then the new task event is triggered
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertNotEquals(value.getContentIfNotHandled(), null)
    }


    //This test should check that if you've set your filter type to show all tasks, that the Add task button is visible.
    @Test
    fun setFilterAllTasks_tasksAddViewVisible(){
        // Given a fresh ViewModel
        //val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        val value = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertEquals(value, true)
    }
}