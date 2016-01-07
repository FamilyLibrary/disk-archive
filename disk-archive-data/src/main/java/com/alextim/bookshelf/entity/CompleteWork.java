package com.alextim.bookshelf.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.alextim.entity.IEntity;

@Entity
@Table(name="COMPLETE_WORKS")
public class CompleteWork implements IEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMPLETE_WORKS_SEQUENCE")
    @SequenceGenerator(name="COMPLETE_WORKS_SEQUENCE", sequenceName="SEQ_COMPLETE_WORKS")
    @Column(name="ID")
    private Long id;

    @Column(name="FIRST_VOLUME_IN_YEAR")
    private Integer firstVolumeInYear;

    @Column(name="LAST_VOLUME_IN_YEAR")
    private Integer lastVolumeInYear;

    @Column(name="TOTAL_VOLUMES")
    private Integer totalVolumes;

    @OneToMany(mappedBy="completeWork", fetch=FetchType.EAGER)
    @Cascade(value={CascadeType.ALL})
    private List<Book> books;

    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalVolumes() {
        return totalVolumes;
    }
    public void setTotalVolumes(Integer totalVolumes) {
        this.totalVolumes = totalVolumes;
    }

    public Integer getFirstVolumeInYear() {
        return firstVolumeInYear;
    }
    public void setFirstVolumeInYear(Integer firstVolumeInYear) {
        this.firstVolumeInYear = firstVolumeInYear;
    }

    public Integer getLastVolumeInYear() {
        return lastVolumeInYear;
    }
    public void setLastVolumeInYear(Integer lastVolumeInYear) {
        this.lastVolumeInYear = lastVolumeInYear;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
