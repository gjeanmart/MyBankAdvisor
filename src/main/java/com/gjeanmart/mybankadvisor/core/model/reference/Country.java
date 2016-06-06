package com.gjeanmart.mybankadvisor.core.model.reference;

import com.gjeanmart.mybankadvisor.core.model.utils.BaseModel;
import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.pattern.PatternReplaceFilterFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.AnalyzerDefs;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.annotations.Proxy;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Indexed
@Proxy(lazy=false)
@Table(name = "COUNTRY")
public class Country extends BaseModel implements Serializable {

    public static final String[] SEARCH_FIELDS = {"name", "alpha3Code", "alpha2Code"};

    @Id
    @Column(name="ALPHA3CODE", unique = true, nullable = false, length = 3)
    private String alpha3Code;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES, analyzer = @Analyzer(definition = "autocompleteEdgeAnalyzer"))
    @SortableField
    @Column(name="ALPHA2CODE", unique = true, nullable = false, length = 2)
    private String alpha2Code;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES, analyzer = @Analyzer(definition = "autocompleteEdgeAnalyzer"))
    @SortableField
    @Column(name="NAME", nullable = false, length = 100)
    private String name;


    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Country country = (Country) o;

        return alpha3Code != null ? alpha3Code.equals(country.alpha3Code) : country.alpha3Code == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (alpha3Code != null ? alpha3Code.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", alpha3Code='" + alpha3Code + '\'' +
                '}';
    }
}
