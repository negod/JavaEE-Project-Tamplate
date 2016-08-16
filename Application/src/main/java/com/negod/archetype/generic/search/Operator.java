/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.archetype.generic.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum Operator {
    AND, OR;
}
