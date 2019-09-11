package com.learn.scalaspringboot.domain;

/**
 * @author LanceDai
 * @date 2019/4/12 17:42
 * @description *
 */

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据库元数据
 */
@Data
@Entity
@Table
public class MetaDatabase {
    /**
     * 数据库ID
     **/
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 数据库名称
     **/
    private String name;
    /**
     * 数据库存放的文件系统地址
     **/
    private String location;
}
