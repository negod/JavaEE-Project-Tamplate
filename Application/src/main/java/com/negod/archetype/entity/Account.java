/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.archetype.entity;

import com.negod.archetype.generic.GenericEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author joaki
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@ToString(callSuper = true, doNotUseGetters = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Account extends GenericEntity {

    private static final long serialVersionUID = 1L;

    @Field(store = Store.YES)
    @Column(name = "name", insertable = true, unique = true)
    @XmlElement
    private String name;

}
