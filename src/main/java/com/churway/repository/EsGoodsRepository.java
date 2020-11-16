package com.churway.repository;

import com.churway.model.EsGoods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/11/13
 * @since 1.0.0
 */
public interface EsGoodsRepository extends ElasticsearchRepository<EsGoods,Long> {


}
