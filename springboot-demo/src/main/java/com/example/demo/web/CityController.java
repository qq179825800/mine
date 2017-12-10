package com.example.demo.web;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.pojo.City;
import com.example.demo.service.CityService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/*
 * SpringMVC控制器
 * */
@ Controller
@RequestMapping("/user")
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class CityController {

	@Autowired
	private CityService cityService;

	@RequestMapping("/list")
	public String list(Map<String, Object> model) {
		List<City> cities = cityService.list();
		model.put("city", cities);
		return "lists";
	}
	@RequestMapping("/data")
	@ResponseBody
	public List<City> listData() {
		List<City> cities = cityService.list();

		return cities;
	}
}
