package com.learn.scalaspringboot.controller

import java.lang

import com.learn.scalaspringboot.domain.MetaTable
import com.learn.scalaspringboot.service.MetaTableService
import lombok.extern.slf4j.Slf4j
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}

/**
  * @author LanceDai 
  * @date 2019/4/12 18:29 
  * @description * 
  */
@RestController
@RequestMapping(Array("/meta/table"))
@Slf4j
class MetaTableController @Autowired()(metaTableService: MetaTableService) {
  var log: Logger = LoggerFactory.getLogger("MetaDatabaseController")

  @RequestMapping(value = Array("/"), method = Array(RequestMethod.POST))
  def save(@RequestParam("name") name: String,
           @RequestParam("tableType") tableType: String,
           @RequestParam("dbID") dbID: Int): String = {
    val metaTable = new MetaTable
    metaTable.name = name
    metaTable.tableType = tableType
    metaTable.dbID = dbID
    metaTableService.save(metaTable)
    log.info(metaTable.toString)
    "success"
  }

  @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
  def query(): lang.Iterable[MetaTable] = {
    metaTableService.query()
  }
}
