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

import hellospring.todo.models.TodoList;
import hellospring.todo.params.TodoListParams;
import hellospring.todo.services.TodoListService;

@RestController
@RequestMapping("/todos")
public class TodoListController {
	private TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping(path = "", consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public TodoList createTodoList(
        @RequestBody() TodoListParams params
    ) {
        return todoListService.create(params.name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoList(
        @PathVariable("id") UUID id
    ) {
        todoListService.delete(id);
    }

    @GetMapping("")
    public List<TodoList> getTodoListPage(
        @RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
        @RequestParam(name = "pageSize", defaultValue = "25", required = false) int pageSize
    ) {
        return todoListService.getPage(pageNumber, pageSize).getContent();
    }

    @GetMapping("/{id}")
    public TodoList getTodoList(
        @PathVariable("id") UUID id
    ) {
        return todoListService.get(id);
    }

    @PutMapping("/{id}")
    public TodoList updateTodoList(
        @PathVariable("id") UUID id,
        @RequestBody() TodoListParams params
    ) {
        return todoListService.update(id, params.name);
    }
}