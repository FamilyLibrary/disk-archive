package com.alextim.bookshelf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alextim.diskarchive.entity.IEntity;

@Entity
@Table(name="COMPLETE_WORKS")
public class CompleteWork implements IEntity {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="TOTAL_VOLUMES")
    private Integer totalVolumes;

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
}
