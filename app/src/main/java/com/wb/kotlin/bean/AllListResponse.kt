package com.wb.kotlin.bean

/**
 * Created by 文博 on 2018/9/21
 */
data class AllListResponse(
        val type: Int,
        val doneList: List<DoneOrTodoList>,
        val todoList: List<DoneOrTodoList>
) {
    data class DoneOrTodoList(
            val date: Long,
            val todoList: List<TodoDetail>
    )
}