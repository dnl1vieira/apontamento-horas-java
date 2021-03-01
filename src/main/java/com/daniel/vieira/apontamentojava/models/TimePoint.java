package com.daniel.vieira.apontamentojava.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_HOUR_POINT")
@Data
public class TimePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "CUSTOMER")
    private String customer;

    @Column(name = "HOUR")
    private String hour;

    @Column(name = "PROJECT")
    private String project;

    @Column(name = "SERVICE")
    private String service;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MANAGER")
    private String manager;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
