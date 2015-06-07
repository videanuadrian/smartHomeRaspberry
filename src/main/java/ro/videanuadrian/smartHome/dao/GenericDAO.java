package ro.videanuadrian.smartHome.dao;
import java.util.List;
import ro.videanuadrian.smartHome.entities.Entity;

public interface GenericDAO<E extends Entity> {

    public E saveOrUpdate(E entity);

    public E findById(Class<E> entityType, Long id);

    public E findOne(E criteria); 

    public List<E> findAll(E criteria);

    public void deleteById(Class entityType, Long id);

	
	
}
