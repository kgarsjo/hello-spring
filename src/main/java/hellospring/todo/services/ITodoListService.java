package hellospring.todo.services;

import java.util.UUID;

import org.springframework.data.domain.Page;

import hellospring.todo.models.TodoList;

public interface ITodoListService {
    public TodoList createTodoList(String name);
    public void deleteTodoList(UUID id);
    public TodoList getTodoList(UUID id);
    public Page<TodoList> getTodoListsPage(int pageNumber, int pageSize);
    public TodoList updateTodoList(UUID id, String name);
}