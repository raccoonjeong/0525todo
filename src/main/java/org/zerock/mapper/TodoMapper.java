package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.TodoVO;

public interface TodoMapper {

	public List<TodoVO> list(Criteria cri);
	
	public TodoVO read(Integer tno);
	public int create(TodoVO vo);
	
	public int delete(Integer tno);
	public int update(TodoVO vo);
	
	public int total();
}
