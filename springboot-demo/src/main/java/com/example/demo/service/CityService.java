package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.CityMapper;
import com.example.demo.pojo.City;


/*
 * 服务层
 * */
@Service
public class CityService {

	@Autowired
	private CityMapper cityMapper;
	
	
	public List<City> list(){
		return cityMapper.selectAll();
	}
	
	
}
