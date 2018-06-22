package hellospring.todo.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hellospring.todo.models.TodoItem;

public interface ITodoItemRepository extends JpaRepository<TodoItem, UUID> {
    @Query("select ti from TodoItem ti join ti.todoList tl where tl.id = ?1")
    public Page<TodoItem> findAllByTodoList(UUID todoListId, Pageable pageable);

    /*
    @Query("select ti from TodoItem ti join TodoList tl where ti.id = ?1 and tl.id = ?2")
    public Optional<TodoItem> findByIdAndTodoList(UUID id, UUID todoListId);

    @Query("delete from TodoItem ti join TodiList tl where ti.id = ?1 and tl.id = ?2")
    public void deleteById(UUID id, UUID todoListId);
    */
}