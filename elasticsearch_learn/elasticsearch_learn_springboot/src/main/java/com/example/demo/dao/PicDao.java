package com.example.demo.dao;

import com.example.demo.bean.Pic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author LanceDai
 * @date 2018/11/12 20:48
 * @description *
 */
@Repository
public interface PicDao extends ElasticsearchRepository<Pic, String> {

    // 判断图片是否存在过
    List<Pic> findByOriginalName(String originalName);

    long count();
    List<Pic> findByTagsContainingOrderByPublicTime(String tag);
//    List<Pic> findAllOrderByPublicTime();

    List<Pic> findAllOrOrderByPublicTimeDesc();

    Page<Pic> findAll(Pageable pageable);
}
