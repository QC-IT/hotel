package com.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dao.TestDao;
import com.hotel.service.TestService;
@Service
public class TestServiceImpl implements TestService{
@Autowired
private TestDao testDao;
	@Override
	public int test() throws Exception {
		
		return testDao.test();
	}

}
