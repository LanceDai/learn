package com.learn.scalaspringboot.controller;

import com.learn.scalaspringboot.domain.MetaDatabase;
import com.learn.scalaspringboot.service.MetaDatabaseService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LanceDai
 * @date 2019/4/12 18:00
 * @description *
 */
@RestController
@RequestMapping("/meta/database")
public class MetaDatabaseController {
    private final MetaDatabaseService metaDatabaseService;

    public MetaDatabaseController(MetaDatabaseService metaDatabaseService) {
        this.metaDatabaseService = metaDatabaseService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String save(@ModelAttribute MetaDatabase metaDatabase){
        metaDatabaseService.save(metaDatabase);
        return "success";
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<MetaDatabase> query(){
        return metaDatabaseService.query();
    }
}
