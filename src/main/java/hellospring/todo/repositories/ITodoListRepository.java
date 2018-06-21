package hellospring.todo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hellospring.todo.models.TodoList;

public interface ITodoListRepository extends JpaRepository<TodoList, UUID> {}