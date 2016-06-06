package com.gjeanmart.mybankadvisor.core.model.config;

import com.gjeanmart.mybankadvisor.core.model.reference.Bank;
import com.gjeanmart.mybankadvisor.core.model.reference.Currency;
import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Proxy(lazy=false)
@Table(name = "ACCOUNT")
public class Account extends BaseModel implements Serializable {

    @Id
    @Column(name="ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME", nullable = false, length = 20, unique = true)
    private String name;

    @Column(name="BIC", nullable = true, length = 11)
    private String bic;

    @Column(name="IBAN", nullable = true, length = 34)
    private String iban;

    @Column(name="INITIAL_BALANCE", nullable = false)
    private Double initialBalance = 0.0;

    @OneToOne
    @JoinColumn(name = "currency")
    private Currency currency;

    private Bank bank;

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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
