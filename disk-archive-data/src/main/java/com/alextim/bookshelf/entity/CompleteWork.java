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
    @Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name="ID")
    private Long id;

    @Column(name="FIRST_VOLUME_IN_YEAR")
    private Integer firstVolumeInYear;

    @Column(name="LAST_VOLUME_IN_YEAR")
    private Integer lastVolumeInYear;

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
}
