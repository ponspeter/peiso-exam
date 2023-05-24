package com.peiso.exam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Column(name = "status", nullable = false, columnDefinition = "TEXT")
    private String status;

    @Column(name = "notes", nullable = false, columnDefinition = "TEXT")
    private String notes;

    @Column(name = "site_id", nullable = false)
    private int siteId;

    @Column(name = "register_id", nullable = false)
    private int registerId;

    @Column(name = "staff_id", nullable = false)
    private int staffId;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "total_tax", nullable = false)
    private double totalTax;

    @Column(name = "paid", nullable = false)
    private int paid;

    @Column(name = "tips", nullable = false)
    private int tips;

    @Column(name = "deleted", nullable = false, columnDefinition = "BOOLEAN")
    private boolean deleted;

    @Column(name = "created_at", nullable = false, columnDefinition = "TEXT")
    private String createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TEXT")
    private String updatedAt;
}
