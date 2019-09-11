package com.learn.scalaspringboot.service;

import com.learn.scalaspringboot.domain.MetaDatabase;
import com.learn.scalaspringboot.repository.MetaDatabaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author LanceDai
 * @date 2019/4/12 17:47
 * @description *
 */
@Service
public class MetaDatabaseService {
    private final MetaDatabaseRepository metaDatabaseRepository;

    public MetaDatabaseService(MetaDatabaseRepository metaDatabaseRepository) {
        this.metaDatabaseRepository = metaDatabaseRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(MetaDatabase metaDatabase) {
        metaDatabaseRepository.save(metaDatabase);
    }

    public Iterable<MetaDatabase> query() {
        return metaDatabaseRepository.findAll();
    }
}
