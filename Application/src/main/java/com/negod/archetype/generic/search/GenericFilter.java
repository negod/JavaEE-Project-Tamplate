/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.archetype.generic.search;

import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class GenericFilter {

    private Set<String> searchFields = new HashSet<>();
    private String globalSearchWord;
    //private OrderBy order;
    private Pagination pagination;

}
