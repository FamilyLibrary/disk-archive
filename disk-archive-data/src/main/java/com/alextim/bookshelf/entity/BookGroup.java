package com.alextim.bookshelf.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.alextim.entity.Group;

@Entity
@Table(name="BOOK_GROUPS")
@PrimaryKeyJoinColumn(name="ID")
public class BookGroup extends Group {
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_id")
    private BookGroup bookGroup;

    @OneToMany(mappedBy="bookGroup", fetch=FetchType.EAGER)
    private List<BookGroup> subCategories;

    public List<BookGroup> getSubCategories() {
        return subCategories;
    }

    public void setSubCateories(List<BookGroup> subCategories) {
        this.subCategories = subCategories;
    }
}