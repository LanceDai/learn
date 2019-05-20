package com.learn.scalaspringboot.repository

import com.learn.scalaspringboot.domain.MetaTable
import org.springframework.data.repository.CrudRepository

/**
  * @author LanceDai
  * @date 2019/4/12 17:45
  * @description *
  */
trait MetaTableRepository extends CrudRepository[MetaTable, Integer] {
}
