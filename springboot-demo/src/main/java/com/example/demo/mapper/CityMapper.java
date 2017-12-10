package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.pojo.City;



/*
 * Mapper接口，这里需要加上Mapper注解
 * */
@Mapper
public interface CityMapper {
	
	List<City> selectAll();
	
}
