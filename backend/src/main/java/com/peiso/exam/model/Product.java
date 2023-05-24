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
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "code", nullable = false, columnDefinition = "TEXT")
    private String code;

    @Column(name = "number", nullable = false, columnDefinition = "TEXT")
    private String number;

    @Column(name = "is_modifier", nullable = false, columnDefinition = "BOOLEAN")
    private boolean isModifier;

    @Column(name = "show_online", nullable = false, columnDefinition = "BOOLEAN")
    private boolean showOnline;

    @Column(name = "friendly_name", nullable = false, columnDefinition = "TEXT")
    private String friendlyName;

    @Column(name = "always_prompt", nullable = false, columnDefinition = "BOOLEAN")
    private boolean alwaysPrompt;
}
