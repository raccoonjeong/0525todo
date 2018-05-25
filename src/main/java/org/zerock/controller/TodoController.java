package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.TodoVO;
import org.zerock.mapper.TodoMapper;

import lombok.extern.log4j.Log4j;

@CrossOrigin
@Log4j
@RestController
@RequestMapping("/todo/*")
public class TodoController {

	private TodoMapper mapper;

	public TodoController(TodoMapper mapper) {
		this.mapper = mapper;
	}

	@GetMapping("/list/{page}")
	public ResponseEntity<Map<String,Object>> listPage(Criteria cri) {

		log.info("cri: " + cri);
		
		ResponseEntity<Map<String,Object>> entity = null;
		
		try{
			int total = mapper.total();
			PageMaker pageMaker = new PageMaker(cri, total); 
				
			Map<String, Object> map = new HashMap<String, Object>();
			List<TodoVO> list = mapper.list(cri);
			
			map.put("pageMaker", pageMaker);
			map.put("list", list);
					
			
			entity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity =  new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		
		}
		
		return entity;
	}
	@GetMapping("/{tno}")
	public ResponseEntity<TodoVO> read(@PathVariable("tno")Integer tno){
		
		return new ResponseEntity<TodoVO>( mapper.read(tno), HttpStatus.OK);		
	}

	@PostMapping("/new")
	public ResponseEntity<String> register(@RequestBody TodoVO vo) {

		mapper.create(vo);
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@PutMapping("/{tno}")
	public ResponseEntity<String> update(@PathVariable("tno")Integer tno, @RequestBody TodoVO vo){
		vo.setTno(tno);
		String msg = mapper.update(vo) == 1? "success" : "fail";
		
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/{tno}")
	public ResponseEntity<String> delete(@PathVariable("tno")Integer tno){

		String msg = mapper.delete(tno) == 1? "success" : "fail";
		
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	

}
