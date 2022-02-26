package com.patika.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;
}
