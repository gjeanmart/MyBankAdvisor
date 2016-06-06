package com.gjeanmart.mybankadvisor.core.model.reference;

import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.hibernate.annotations.Proxy;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.SortableField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Indexed
@Proxy(lazy=false)
@Table(name = "CURRENCY")
public class Currency extends BaseModel implements Serializable {

    public static final String[] SEARCH_FIELDS = {"code", "description"};

    @Id
    @Column(name="CODE", unique = true, nullable = false, length = 3)
    private String code;

    @Field
    @SortableField
    @Column(name="DESCRIPTION", unique = true, nullable = false, length = 100)
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Currency currency = (Currency) o;

        return code != null ? code.equals(currency.code) : currency.code == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
