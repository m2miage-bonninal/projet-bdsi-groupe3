/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.api;

/**
 *
 * @author emerik
 */
import java.util.List;

/**
 * A base repository contract for CRUD operations on entities.
 * @param <T> the entity type
 * @param <I> the id type
 */
public interface Repository<T, I> {

    /**
     * Save or update an entity.
     * @param entity the entity to save
     */
    void save(T entity);

    /**
     * Delete an entity.
     * @param entity  entity to remove
     */
    void delete(T entity);

    /**
     * Retrieve an entity by id.
     * @param entityClass class of the entity to retrieve
     * @param id id of the entity to remove                                                                                          
     * @return the entity of null                                                                                                    
     */                                                                                                                              
    T findById(I id);                                                                                                                
                                                                                                                                     
    /**                                                                                                                              
     * Retrieve all entities of a given type.                                                                                        
     * @param entityClass class of the entity to retrieve                                                                            
     * @return all entities of a given type or an empty collection                                                                   
     */                                                                                                                              
    List<T> getAll();                                                                                                                
                                                                                                                                     
}  
