package com.gjeanmart.mybankadvisor.core.model.reference;

import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Proxy(lazy=false)
@Table(name = "BANK")
public class Bank extends BaseModel implements Serializable {

    @Id
    @Column(name="ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME", nullable = false, length = 100)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
