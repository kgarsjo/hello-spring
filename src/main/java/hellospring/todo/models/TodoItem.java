package hellospring.todo.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import hellospring.common.models.AuditableModel;

@Entity
@Table(name = "todo_item")
public class TodoItem extends AuditableModel {
    private static final long serialVersionUID = -6771056529072026030L;

	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private boolean completed;

    @Column(nullable = false)
    private int position;

    @Column(nullable = false)
    private String summary;

    @JoinColumn(name = "todo_list_id", nullable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TodoList todoList;

    public UUID getId() {
        return this.id;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public int getPosition() {
        return this.position;
    }

    public String getSummary() {
        return this.summary;
    }
    
    public TodoList getTodoList() {
        return this.todoList;
    }

    /**
     * Used on hydration from datasource
     */
    @SuppressWarnings("unused")
    private void setId(UUID id) {
        this.id = id;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }
}