package hellospring.todo.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import hellospring.common.exceptions.ResourceNotFoundException;
import hellospring.todo.models.TodoItem;
import hellospring.todo.params.TodoItemParams;
import hellospring.todo.repositories.ITodoItemRepository;
import hellospring.todo.repositories.ITodoListRepository;

@Service
public class TodoItemService {
    private ITodoItemRepository todoItemRepository;
    private ITodoListRepository todoListRepository;

    public TodoItemService(
        ITodoItemRepository todoItemRepository,
        ITodoListRepository todoListRepository
    ) {
        this.todoItemRepository = todoItemRepository;
        this.todoListRepository = todoListRepository;
    }

    public TodoItem create(TodoItemParams params) {
        TodoItem todoItem = populate(new TodoItem(), params);
        return todoItemRepository.save(todoItem);
    }

    public void delete(UUID id, UUID todoListId) {
        todoItemRepository.deleteById(id);
    }

    public TodoItem get(UUID id, UUID todoListId) {
        return todoItemRepository.findById(id)
            .orElseThrow(() -> (
                new ResourceNotFoundException(String.format("No TodoItem with id '%s'", id))
            ));
    }

    public Page<TodoItem> getPage(int pageNumber, int pageSize, UUID todoListId) {
        return todoItemRepository.findAllByTodoList(
            todoListId,
            PageRequest.of(pageNumber, pageSize)
        );
    }

    public TodoItem update(UUID id, TodoItemParams params) {
        return todoItemRepository.findById(id)
            .map(todoItem -> {
                populate(todoItem, params);
                return todoItemRepository.save(todoItem);
            })
            .orElseThrow(() -> (
                new ResourceNotFoundException(String.format("No TodoItem with id '%s'", id))
            ));
    }

    private TodoItem populate(TodoItem model, TodoItemParams params) {
        model.setCompleted(params.completed);
        model.setPosition(params.position);
        model.setSummary(params.summary);
        model.setTodoList(
            todoListRepository.findById(params.todoListId)
                .orElseThrow(() -> (
                    new ResourceNotFoundException(String.format("No TodoList with id '%s'", params.todoListId))
                ))
        );
        return model;
    }
}