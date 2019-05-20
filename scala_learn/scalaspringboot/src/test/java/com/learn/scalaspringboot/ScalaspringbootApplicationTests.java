package com.learn.scalaspringboot;

import com.learn.scalaspringboot.domain.MetaDatabase;
import com.learn.scalaspringboot.service.MetaDatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ScalaspringbootApplicationTests {


    @Autowired
    MetaDatabaseService metaDatabaseService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void metaDataServiceTestWithSave() {
        MetaDatabase metaDatabase = new MetaDatabase();
        metaDatabase.setName("default");
        metaDatabase.setLocation("hdfs://hadoop000:8020/user/hive/warehouse");
        metaDatabaseService.save(metaDatabase);
    }

    @Test
    public void metaDataServiceTestWithQuery() {
        Iterable<MetaDatabase> iterable = metaDatabaseService.query();
        iterable.forEach(metaDatabase -> log.info("metaDatabase = " + metaDatabase));
    }
}
