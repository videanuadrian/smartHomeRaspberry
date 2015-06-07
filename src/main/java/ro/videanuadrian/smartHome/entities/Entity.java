package ro.videanuadrian.smartHome.entities;

import java.io.Serializable;

import jodd.db.oom.meta.DbId;

public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@DbId
    protected Long id;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPersistent() {
        return id != null;
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return System.identityHashCode(this);
        }
        return 31 * id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Entity == false) {
            return false;
        }
        Entity entity = (Entity) o;
        if (id == null && entity.id == null) {
            return this == o;
        }
        if ((id == null) || (entity.id == null)) {
            return false;
        }
        return id.equals(entity.id);
    }

    @Override
    public String toString() {
        return "Entity{" + this.getClass().getSimpleName() + ':' + id +    '}';
    }
}