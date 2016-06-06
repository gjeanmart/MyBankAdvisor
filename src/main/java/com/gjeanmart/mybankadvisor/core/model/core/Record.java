package com.gjeanmart.mybankadvisor.core.model.core;

import com.gjeanmart.mybankadvisor.core.model.config.Account;
import com.gjeanmart.mybankadvisor.core.model.config.Category;
import com.gjeanmart.mybankadvisor.core.model.config.PaymentMethod;
import com.gjeanmart.mybankadvisor.core.model.config.Tier;
import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Proxy(lazy=false)
@Table(name = "RECORD")
public class Record extends BaseModel implements Serializable {

    @Id
    @Column(name="ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="DATE", nullable = false)
    private Date date;

    private Tier tier;

    private Category category;

    private PaymentMethod paymentMethod;

    private Account account;

    private String description;

    private String source;

    private Record transfert;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Record getTransfert() {
        return transfert;
    }

    public void setTransfert(Record transfert) {
        this.transfert = transfert;
    }

    @Override
    public String toString() {
        return "Record{" +
                "date=" + date +
                ", tier=" + tier +
                ", category=" + category +
                ", paymentMethod=" + paymentMethod +
                ", account=" + account +
                ", description='" + description + '\'' +
                ", source='" + source + '\'' +
                ", transfert=" + transfert +
                '}';
    }
}

