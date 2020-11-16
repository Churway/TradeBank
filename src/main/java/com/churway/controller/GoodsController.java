package com.churway.controller;

import com.churway.entity.Goods;
import com.churway.model.EsGoods;
import com.churway.model.GoodsDto;
import com.churway.service.GoodsService;
import com.churway.utils.BaseController;
import com.github.pagehelper.PageInfo;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Controller
@RequestMapping("/churway/Goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Value("${image.save.parent.path}")
    String parentPath;

    @ResponseBody
    @RequestMapping("/add")
    public String add(GoodsDto goodsDto, HttpServletRequest request) {
        List<MultipartFile> imgs = goodsDto.getImgs();
        List<String> pathList = new ArrayList<>();
        try {
            for (MultipartFile i : imgs) {
                String path = UUID.randomUUID() + "-" + i.getOriginalFilename();
                i.transferTo(new File(parentPath + path));
                pathList.add(path);
            }
            goodsDto.setMainImg(pathList.get(0));
        } catch (IOException e) {
            return "Error when transfer images,please try later.";
        }
        goodsService.add(goodsDto, super.getUser(request), pathList);
        return "success";
    }

    /**
     *
     * @param pageable
     * @param keyword
     * @param typeId
     * @return
     */
    @ResponseBody
    @RequestMapping("/showGoods")
    public Map<String,Object> showGoods(@PageableDefault Pageable pageable, String keyword, Long typeId) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().mustNot(termQuery("state",0)).minimumNumberShouldMatch(1);
        if(typeId != 0)
            boolQueryBuilder.must(termQuery("typeId", typeId));
        if(keyword != null && !"".equals(keyword))
            boolQueryBuilder.should(matchQuery("name", keyword)).should(matchQuery("description", keyword));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withPageable(pageable).build();
        AggregatedPage<EsGoods> esGoodsPage = elasticsearchTemplate.queryForPage(nativeSearchQuery, EsGoods.class);
        List<EsGoods> esGoods=esGoodsPage.getContent();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("list",esGoods);
        map.put("total",esGoodsPage.getTotalPages());
        return map;
    }

    @ResponseBody
    @RequestMapping("/showMyGoods")
    public PageInfo<Goods> showMyGoods(Integer page, Integer size, Date startDate, Date endDate, HttpServletRequest request) {
        return goodsService.showMyGoods(page,size,startDate,endDate,super.getUser(request).getId());
    }

    @RequestMapping("/goRoom")
    public String goRoom(Long id,HttpServletRequest request){
        request.setAttribute("id",id);
        return "tradebank/room";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Long id){
        if(goodsService.delete(id) == 0)
            return "fail";
        return "success";
    }

    @ResponseBody
    @RequestMapping("/setAction")
    public String setAction(Long id){
        Goods goods = goodsService.findById(id);
        if(goods.getState() != 1)
            return "fail";
        return "/setAction";
    }


    @RequestMapping("/init")
    public String init(){
        List<Goods> list = goodsService.selectAll();
        list.forEach(g->{
            EsGoods esGoods = new EsGoods();
            BeanUtils.copyProperties(g,esGoods);
            goodsService.add(esGoods);
            System.out.println("initing----->"+esGoods);
        });
        return "tradebank/success";
    }

}