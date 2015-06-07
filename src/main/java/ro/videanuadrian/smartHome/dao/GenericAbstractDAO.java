package ro.videanuadrian.smartHome.dao;

import java.util.List;

import ro.videanuadrian.smartHome.entities.Entity;
import jodd.db.DbQuery;
import jodd.db.oom.sqlgen.DbEntitySql;
import jodd.petite.meta.PetiteBean;

import static jodd.db.oom.DbOomQuery.query;
import static jodd.db.oom.sqlgen.DbEntitySql.insert;
import static jodd.db.oom.sqlgen.DbEntitySql.updateAll;

@PetiteBean
public abstract class GenericAbstractDAO<E extends Entity> implements GenericDAO<E>{

	@Override
    public E saveOrUpdate(E entity) {
    	
        if (entity.isPersistent() == false) {
            DbQuery q = query(insert(entity));
            q.setGeneratedColumns();
            q.executeUpdate();
            long key = q.getGeneratedKey();
            entity.setId(Long.valueOf(key));
            q.close();
        } else {
            query(updateAll(entity)).executeUpdate();
        }
        return entity;
    }

	@Override
    public E findById(Class<E> entityType, Long id) {
        return query(DbEntitySql.findById(entityType, id)).autoClose().find(entityType);
    }

	@Override
    public E findOne(E criteria) {
        return query(DbEntitySql.find(criteria)).autoClose().find(criteria.getClass());
    }

	@Override
    public List<E> findAll(E criteria) {
        return query(DbEntitySql.find(criteria)).autoClose().list(criteria.getClass());
    }

	@Override
    public void deleteById(Class entityType, Long id) {    	
        query(DbEntitySql.deleteById(entityType, id)).autoClose().executeUpdate();        
    }
    
}