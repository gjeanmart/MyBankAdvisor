package com.gjeanmart.mybankadvisor.core.model.utils;

import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.pattern.PatternReplaceFilterFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import javax.persistence.Parameter;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
@AnalyzerDefs({
        @AnalyzerDef(	name = "autocompleteEdgeAnalyzer",
                tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),

                filters = {
                        @TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
                                @org.hibernate.search.annotations.Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
                                @org.hibernate.search.annotations.Parameter(name = "replacement", value = " "),
                                @org.hibernate.search.annotations.Parameter(name = "replace", value = "all") }),
                        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                        @TokenFilterDef(factory = StopFilterFactory.class),
                        @TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
                                @org.hibernate.search.annotations.Parameter(name = "minGramSize", value = "3"),
                                @org.hibernate.search.annotations.Parameter(name = "maxGramSize", value = "50") }) })
})
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = -2310155274833386464L;

    @Column(name="ADDED_BY", nullable = true)
    private String addedBy;

    @Column(name="ADDED_DATE", nullable = true)
    private Date addedDate = new Date();

    @Column(name="LAST_MODIFIED_BY", nullable = true)
    private String lastModifiedBy;

    @Column(name="LAST_MODIFIED_DATE", nullable = true)
    private Date lastModifiedDate;

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


}
