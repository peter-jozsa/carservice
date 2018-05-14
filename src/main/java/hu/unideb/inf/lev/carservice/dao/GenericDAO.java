package hu.unideb.inf.lev.carservice.dao;

import java.io.Serializable;
import java.util.List;

/**
 * This generic interface describes the look of all the methods that should be implemented by every children.
 * @param <T> This DAO should work with this type of entities.
 * @param <ID> The type of unique identifier of the entities.
 */
public interface GenericDAO<T, ID extends Serializable> {
    /**
     * Creates an entity in the database.
     * @param entity The entity to be stored persistently.
     */
    void create(T entity);

    /**
     * Updates an existing entity in the database.
     * @param entity The entity to be updated.
     */
    void update(T entity);

    /**
     * Finds an entity by its unique identifier.
     * @param id The unique identifier of the entity.
     * @return The found entity or <code>null</code>.
     */
    T findById(ID id);

    /**
     * Deletes an entity from the database.
     * @param entity The entity to be deleted.
     */
    void delete(T entity);

    /**
     * Gets all of the entities of this type.
     * @return A list of entities.
     */
    List<T> getAll();
}
