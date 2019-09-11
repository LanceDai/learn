package com.learn.scalaspringboot.service

import java.lang

import com.learn.scalaspringboot.domain.MetaTable
import com.learn.scalaspringboot.repository.MetaTableRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
  * @author LanceDai 
  * @date 2019/4/12 18:24 
  * @description * 
  */
@Service
class MetaTableService @Autowired()(metaTableRepository: MetaTableRepository) {
  @Transactional
  def save(metaTable: MetaTable): Unit = {
    metaTableRepository.save(metaTable)
  }

  def query(): lang.Iterable[MetaTable] = {
    metaTableRepository.findAll()
  }
}
