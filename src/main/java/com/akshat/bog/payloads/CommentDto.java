package com.akshat.bog.payloads;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommentDto {

	private int id;
	private String content;
	private Integer userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	

	

}
