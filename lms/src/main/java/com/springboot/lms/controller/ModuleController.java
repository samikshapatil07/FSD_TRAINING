package com.springboot.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.CModule;
import com.springboot.lms.service.ModuleService;

@RestController
@RequestMapping("/api/module")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;
//---------------------- add module -------------------------------
	/*
	 * AIM: To add module to DB <-- module needs course id 
	 * METHOD: POST 
	 * PATH: /api/module/add/{courseId}
	 * PARAM: Module as request body , courseId as PathVariable 
	 * Response: Module along with course it belongs to 
	 * */
	
	@PostMapping("/add/{courseId}")
	public CModule addModule(@PathVariable int courseId, 
							@RequestBody CModule module) {
		
		return moduleService.addModule(courseId,module); 
	}
}