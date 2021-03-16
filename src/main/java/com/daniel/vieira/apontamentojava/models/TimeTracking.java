package com.daniel.vieira.apontamentojava.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.daniel.vieira.apontamentojava.exceptions.RequiredFiledsIsMissing;
import lombok.Data;

@Entity
@Table(name = "TB_HOUR_POINT")
@Data
public class TimeTracking {

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

    public TimeTracking (String customer, String hour, String project, String service, String description, String manager, LocalDateTime date){
        this.customer = customer;
        this.hour = hour;
        this.project = project;
        this.service = service;
        this.description = description;
        this.manager = manager;
        this.date = date;
    }

    public boolean validate() throws RequiredFiledsIsMissing {
        if (this.customer == null || this.customer.isEmpty()) {
            throw new RequiredFiledsIsMissing("Customer is expected to Save/Update this object");
        } else if (this.project == null || this.project.isEmpty()) {
            throw new RequiredFiledsIsMissing("Project is expected to Save/Update this object");
        } else if (this.hour == null || this.hour.isEmpty()) {
            throw new RequiredFiledsIsMissing("How many hours you worked is expected to Save/Update this object");
        } else if (this.service == null || this.service.isEmpty()) {
            throw new RequiredFiledsIsMissing("Service is expected to Save/Update this object");
        } else if (this.description == null || this.description.isEmpty()) {
            throw new RequiredFiledsIsMissing("Description is expected to Save/Update this object");
        } else if (this.date == null) {
            throw new RequiredFiledsIsMissing("Date is expected to Save/Update this object");
        } else {
            return true;
        }
    }

}
