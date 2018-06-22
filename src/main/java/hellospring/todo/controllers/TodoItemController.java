package hellospring.todo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hellospring.todo.models.TodoItem;
import hellospring.todo.params.TodoItemParams;
import hellospring.todo.services.TodoItemService;

@RestController
@RequestMapping("/todos/{todoListId}/todo_items")
public class TodoItemController {
	private TodoItemService todoItemService;

    public TodoItemController(TodoItemService todoListService) {
        this.todoItemService = todoListService;
    }

    @PostMapping(path = "", consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItem createTodoItem(
        @PathVariable("todoListId") UUID todoListId,
        @RequestBody() TodoItemParams params
    ) {
        params.todoListId = params.todoListId = todoListId;
        return todoItemService.create(params);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoItem(
        @PathVariable("id") UUID id,
        @PathVariable("todoListId") UUID todoListId
    ) {
        todoItemService.delete(id, todoListId);
    }

    @GetMapping("")
    public List<TodoItem> getTodoItemPage(
        @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
        @RequestParam(name = "pageSize", defaultValue = "25", required = false) int pageSize,
        @PathVariable("todoListId") UUID todoListId
    ) {
        return todoItemService.getPage(pageNumber, pageSize, todoListId).getContent();
    }

    @GetMapping("/{id}")
    public TodoItem getTodoItem(
        @PathVariable("id") UUID id,
        @PathVariable("todoListId") UUID todoListId
    ) {
        return todoItemService.get(id, todoListId);
    }

    @PutMapping("/{id}")
    public TodoItem updateTodoItem(
        @PathVariable("id") UUID id,
        @RequestBody() TodoItemParams params
    ) {
        return todoItemService.update(id, params);
    }
}