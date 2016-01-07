package com.alextim.entity.interceptor;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.alextim.entity.IEntityWithTimestampColumns;
import com.alextim.entity.TimestampColumns;

public class EntityTimestampInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = -1337684332928398293L;

    @Override
    public boolean onFlushDirty(final Object entity, final Serializable id,
            final Object[] currentState, final Object[] previousState,
            final String[] propertyNames, final Type[] types) {
        if (entity instanceof IEntityWithTimestampColumns) {
            final IEntityWithTimestampColumns entityWithTimestamp = (IEntityWithTimestampColumns)entity;
            getTimestampColumns(entityWithTimestamp).setUpdated(LocalDateTime.now());
            return true;
        } else {
            return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
        }
    }

    @Override
    public boolean onSave(final Object entity, final Serializable id,
            final Object[] state, final String[] propertyNames, 
            final Type[] types) {
        if (entity instanceof IEntityWithTimestampColumns) {
            final IEntityWithTimestampColumns entityWithTimestamp = (IEntityWithTimestampColumns)entity;
            getTimestampColumns(entityWithTimestamp).setCreated(LocalDateTime.now());
            return true;
        } else {
            return super.onSave(entity, id, state, propertyNames, types);
        }
    }

    private TimestampColumns getTimestampColumns(final IEntityWithTimestampColumns entityWithTimestamp) {
        if(entityWithTimestamp.getTimestampColumns() == null) {
            entityWithTimestamp.setTimestampColumns(new TimestampColumns());
        }
        return entityWithTimestamp.getTimestampColumns();
    }
}
