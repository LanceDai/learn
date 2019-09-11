package com.learn

import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

/**
  * @author LanceDai 
  * @date 2019/4/12 17:04 
  * @description * 
  */
@RestController
class ScalaHelloBoot {
  @RequestMapping(value = Array("/sayScalaHello"), method = Array(RequestMethod.GET))
  def sayHello() {

  }
}
