package com.alextim.bookshelf.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.alextim.diskarchive.entity.IEntity;

@Entity
@Table(name="BOOKS")
public class Book implements IEntity {
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

    @OneToMany
    @Cascade({CascadeType.MERGE, CascadeType.SAVE_UPDATE})
    @JoinColumn(name="AUTHOR_ID")
    private Set<BookAuthor> authors;

    @OneToOne
    @JoinColumn(name="GROUP_ID")
    private BookGroup bookGroup;

    @OneToOne
    @Cascade({CascadeType.MERGE, CascadeType.SAVE_UPDATE})
    @JoinColumn(name="COMPLETE_WORK_ID")
    private CompleteWork completeWork;

    @Column(name="YEAR_OF_PUBLICATION")
    private Integer yearOfPublication;

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
}
