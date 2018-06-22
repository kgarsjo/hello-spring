package hellospring.todo.models;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import hellospring.common.models.AuditableModel;

@Entity
@Table(name = "todo_list")
public class TodoList extends AuditableModel {
    private static final long serialVersionUID = -6771056529072026030L;

	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany
    private Set<TodoItem> todoItems;

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Used on hydration from datasource
     */
    @SuppressWarnings("unused")
    private void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}