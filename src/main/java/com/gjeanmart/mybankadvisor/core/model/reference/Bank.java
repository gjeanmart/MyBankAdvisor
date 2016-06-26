package com.gjeanmart.mybankadvisor.core.model.reference;

import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "COUNTRY")
    private Country country;

    @ManyToMany(
            targetEntity=Currency.class,
            cascade={CascadeType.MERGE}
    )
    @JoinTable(
            name="BANK_CURRENCY",
            joinColumns=@JoinColumn(name="ID"),
            inverseJoinColumns=@JoinColumn(name="CODE")
    )
    private List<Currency> currencies;

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
