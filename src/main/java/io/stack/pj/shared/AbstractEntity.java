package io.stack.pj.shared;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Prajin Maharjan
 * @version 1.0
 */
@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractEntity extends AbstractPersist<Long> {

    @CreationTimestamp
    @Setter(AccessLevel.PROTECTED)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    @UpdateTimestamp
    @Setter(AccessLevel.PROTECTED)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate = new Date();

    @Version
    private Long version = 0L;

    @Override
    public boolean isNew() {
        return super.isNew();
    }

    @PrePersist
    public void prePersist() {
        this.lastModifiedDate = new Date();
    }
}
