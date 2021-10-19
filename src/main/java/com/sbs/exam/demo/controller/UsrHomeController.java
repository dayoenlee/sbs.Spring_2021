package com.sbs.exam.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

@Controller
public class UsrHomeController {
	@RequestMapping("/usr/home/test1")
	@ResponseBody
	public String getString() {
		return "Hi";
	}
	@RequestMapping("/usr/home/getMap")
	@ResponseBody
	public Map getMap() {
		Map<String,Object>map = new HashMap<>();
		
		map.put("철수의 나이",22);
		map.put("영희의 나이",32);
		return map;
	}
	@RequestMapping("/usr/home/getList")
	@ResponseBody
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		
		list.add("철수의 나이");
		list.add("영희의 나이");
		
		return list;
	}
	@RequestMapping("/usr/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		Article article = new Article();
		
	
		return article;
	}
}	
@Data
class Article{
	
	private int id;
	private String title;
	
	public Article() {
		id =1;
		title ="제목";
	}
}
