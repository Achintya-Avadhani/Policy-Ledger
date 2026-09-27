package com.hdfclife.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "riders")
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rider_code", nullable = false, unique = true, length = 50)
    private String riderCode;

    @Column(nullable = false, length = 100)
    private String name;

    protected Rider() {
    }

    public Rider(String riderCode, String name) {
        this.riderCode = riderCode;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getRiderCode() {
        return riderCode;
    }

    public void setRiderCode(String riderCode) {
        this.riderCode = riderCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
