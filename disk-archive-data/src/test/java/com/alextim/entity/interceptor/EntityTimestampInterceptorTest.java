package com.alextim.entity.interceptor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.Interceptor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import com.alextim.bookshelf.entity.Book;
import com.alextim.diskarchive.entity.Author;
import com.alextim.entity.IEntity;

public class EntityTimestampInterceptorTest {
    @Spy
    private Interceptor interceptor = new EntityTimestampInterceptor();

    private Book book;
    private Author author;

    @Before
    public void setUp() {
        this.book = new Book();
        this.author = new Author();
    }

    @Test
    public void shouldNotModifyStateOnFlushEventForNullValueEntity() {
        final boolean isStateModified = onFlushDirty(null);
        assertFalse(isStateModified);
    }

    @Test
    public void shouldNotModifyStateOnSaveEventForNullValueEntity() {
        final boolean isStateModified = onSave(null);
        assertFalse(isStateModified);
    }

    @Test
    public void shouldModifyStateOnFlushDirtyEventForEntityWithTSColumns() {
        final boolean isStateModified = onFlushDirty(book);
        assertTrue(isStateModified);
        assertNotNull(book.getTimestampColumns());
        assertNotNull(book.getTimestampColumns().getUpdated());
        assertNull(book.getTimestampColumns().getCreated());
    }

    @Test
    public void shouldNotModifyStateOnFlushDirtyEventForEntityWithoutTSColumns() {
        final boolean isStateModified = onFlushDirty(author);
        assertFalse(isStateModified);
    }

    @Test
    public void shouldModifyStateOnSaveEventForEntityWithTSColumns() {
        final boolean isStateModified = onSave(book);

        assertTrue(isStateModified);
        assertNotNull(book.getTimestampColumns());
        assertNotNull(book.getTimestampColumns().getCreated());
        assertNull(book.getTimestampColumns().getUpdated());
    }

    @Test
    public void shouldNotModifyStateOnSaveEventForEntityWithoutTSColumns() {
        final boolean isStateModified = onSave(author);
        assertFalse(isStateModified);
    }

    private boolean onFlushDirty(IEntity entity) {
        return interceptor.onFlushDirty(entity, null, null, null, null, null);
    }
    private boolean onSave(IEntity entity) {
        return interceptor.onSave(entity, null, null, null, null);
    }
}
