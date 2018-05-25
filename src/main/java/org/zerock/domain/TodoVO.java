package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class TodoVO {
	private Integer tno;
	private String content;
	private Date regdate;

}
