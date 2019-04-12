package com.learn.scalaspringboot.domain

import javax.persistence.{Entity, GeneratedValue, Id, Table}
import lombok.{Data, ToString}

import scala.beans.BeanProperty

/**
  * @author LanceDai 
  * @date 2019/4/12 18:19 
  * @description * 
  */
@Entity
@Table
class MetaTable {
  @Id
  @GeneratedValue
  @BeanProperty
  var id: Int = _
  var name: String = _
  var tableType: String = _
  var dbID: Int = _

  override def toString = s"MetaTable(id=$id, name=$name, tableType=$tableType, dbID=$dbID)"
}
