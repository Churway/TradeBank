package com.churway.service;

import com.churway.dao.ImgMapper;
import com.churway.entity.Img;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ImgService {
    @Autowired
    private ImgMapper imgMapper;

    public ImgMapper getImgMapper() {
        return imgMapper;
    }

    public void add(List<String> pathList, Long id) {
        pathList.forEach(p -> {
            Img img = new Img();
            img.setPath(p);
            img.setId(id);
            imgMapper.insert(img);
        });
    }
}