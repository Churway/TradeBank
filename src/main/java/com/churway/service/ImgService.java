package com.churway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.churway.entity.Img;
import com.churway.dao.ImgMapper;

@Transactional
@Service
public class ImgService {
	@Autowired
	private ImgMapper imgMapper;

	public ImgMapper getImgMapper() {
		return imgMapper;
	}

}