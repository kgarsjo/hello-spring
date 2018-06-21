package hellospring.todo.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import hellospring.common.exceptions.ResourceNotFoundException;
import hellospring.todo.models.TodoList;
import hellospring.todo.repositories.ITodoListRepository;

@Service
public class TodoListService implements ITodoListService {
    private ITodoListRepository todoListRepository;

    public TodoListService(ITodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public TodoList createTodoList(String name) {
        TodoList todoList = new TodoList();
        todoList.setName(name);
        return this.todoListRepository.save(todoList);
    }

    public void deleteTodoList(UUID id) {
        this.todoListRepository.deleteById(id);
    }

    public TodoList getTodoList(UUID id) {
        return this.todoListRepository.findById(id)
            .orElseThrow(ResourceNotFoundException::new);
    }

    public Page<TodoList> getTodoListsPage(int pageNumber, int pageSize) {
        return this.todoListRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public TodoList updateTodoList(UUID id, String name) {
        return this.todoListRepository.findById(id)
            .map(todoList -> {
                todoList.setName(name);
                return this.todoListRepository.save(todoList);
            })
            .orElseThrow(ResourceNotFoundException::new);
    }
}