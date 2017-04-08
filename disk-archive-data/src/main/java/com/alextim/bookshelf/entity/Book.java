package com.alextim.bookshelf.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.alextim.entity.IEntity;
import com.alextim.entity.IEntityWithTimestampColumns;
import com.alextim.entity.TimestampColumns;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="BOOKS")
public class Book implements IEntity, IEntityWithTimestampColumns {
    public final static String NEW_NAME = "new name";
    public final static String NEW_DESCRIPTION = "new description";

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOOKS_SEQUENCE")
    @SequenceGenerator(name="BOOKS_SEQUENCE", sequenceName="SEQ_BOOKS")
    @Column(name="ID")
    private Long id;

    @Column(name="NAME", nullable=false)
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="VOLUME")
    private Integer volume;

    @OneToMany(fetch=FetchType.EAGER)
    @Cascade({CascadeType.ALL})
    @JoinColumn(name="BOOK_ID")
    private Set<BookAuthor> authors;

    @OneToOne
    @JoinColumn(name="GROUP_ID")
    private BookGroup bookGroup;

    @OneToOne
    @Cascade({CascadeType.ALL})
    @JoinColumn(name="COMPLETE_WORK_ID")
    private CompleteWork completeWork;

    @Column(name="YEAR_OF_PUBLICATION")
    private Integer yearOfPublication;

    @Embedded
    private TimestampColumns timestampColumns;

    @Column(name="UPDATED_FROM_UI")
    private boolean updatedFromUI;

    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public CompleteWork getCompleteWork() {
        return completeWork;
    }
    public void setCompleteWork(CompleteWork completeWork) {
        this.completeWork = completeWork;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }
    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public BookGroup getBookGroup() {
        return bookGroup;
    }
    public void setBookGroup(BookGroup bookGroup) {
        this.bookGroup = bookGroup;
    }

    public Set<BookAuthor> getAuthors() {
        return authors;
    }
    public void setAuthors(Set<BookAuthor> authors) {
        this.authors = authors;
    }

    @Override
    public TimestampColumns getTimestampColumns() {
        return timestampColumns;
    }
    @Override
    public void setTimestampColumns(final TimestampColumns timestampColumns) {
        this.timestampColumns = timestampColumns;
    }

    public boolean isUpdatedFromUI() {
        return updatedFromUI;
    }
    public void setUpdatedFromUI(boolean updatedFromUI) {
        this.updatedFromUI = updatedFromUI;
    }

    @Override
    public boolean equals(final Object bookObj) {
        if (!(bookObj instanceof Book)) {
            return false;
        }
        if (this == bookObj) {
            return true;
        }

        final Book book = (Book)bookObj;

        return (book.yearOfPublication == null ? this.yearOfPublication == null : book.yearOfPublication.equals(this.yearOfPublication)
                && book.authors == null ? this.authors == null : book.authors.equals(this.authors)
        );
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + this.getYearOfPublication().hashCode();
        result = 31 * result + this.getAuthors().hashCode();

        return result;
    }
}
