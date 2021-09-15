package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest{

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero(){
        //Create an Active task list
        val activeTask = Task("Task one", "Is an active test", false)
        val tasks = listOf<Task>(activeTask)
        //Call function
        val result = getActiveAndCompletedStats(tasks)
        //Check result
        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 100f)
    }

    @Test
    fun getActiveAndCompletedStats_noActive_returnsHundredZero(){
        //Create an No Active task list
        val activeTask = Task("Task one", "Is an no active test", true)
        val tasks = listOf<Task>(activeTask)
        //Call function
        val result = getActiveAndCompletedStats(tasks)
        //Check result
        assertEquals(result.completedTasksPercent, 100f)
        assertEquals(result.activeTasksPercent, 0f)
    }

    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty(){
        //Given 3 completed tasks and 2 active tasks
        val tasks = listOf<Task>(
            Task("Task one", "Is an no active test", true),
            Task("Task two", "Is an no active test", true),
            Task("Task three", "Is an no active test", true),
            Task("Task four", "Is an active test", false),
            Task("Task five ", "Is an active test", false)
        )
        // When the list of tasks is computed
        val result = getActiveAndCompletedStats(tasks)
        // Then the result is 40-60
        assertEquals(result.activeTasksPercent, 40f)
        assertEquals(result.completedTasksPercent, 60f)
    }

    @Test
    fun getActiveAndCompletedStats_empty_returnZeros(){
        //Give
        val emptyList = emptyList<Task>()
        //When
        val result = getActiveAndCompletedStats(emptyList)
        //Then
        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 0f)
    }

    @Test
    fun getActiveAndCompletedStats_error_returnZeros(){
        //Give
        val nullList = null
        //When
        val result = getActiveAndCompletedStats(nullList)
        //Then
        assertEquals(result.completedTasksPercent, 0f)
        assertEquals(result.activeTasksPercent, 0f)
    }
}