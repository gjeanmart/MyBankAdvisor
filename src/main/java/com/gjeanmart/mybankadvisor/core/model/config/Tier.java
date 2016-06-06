package com.gjeanmart.mybankadvisor.core.model.config;

import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Proxy(lazy=false)
@Table(name = "TIER")
public class Tier extends BaseModel implements Serializable {


    @Id
    @Column(name="ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME", nullable = false, length = 20)
    private String name;

    @Column(name="DESCRIPTION", nullable = true, length = 100)
    private String description;

    private Category defaultCategory;

    private PaymentMethod defaultPaymentMethod;

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

    public Category getDefaultCategory() {
        return defaultCategory;
    }

    public void setDefaultCategory(Category defaultCategory) {
        this.defaultCategory = defaultCategory;
    }

    public PaymentMethod getDefaultPaymentMethod() {
        return defaultPaymentMethod;
    }

    public void setDefaultPaymentMethod(PaymentMethod defaultPaymentMethod) {
        this.defaultPaymentMethod = defaultPaymentMethod;
    }
}

