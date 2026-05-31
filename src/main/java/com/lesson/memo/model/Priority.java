package com.lesson.memo.model;

public enum Priority {
	HIGH("高"), MEDIUM("中"), LOW("低");
	
	private String priority;
	
	private Priority(String priority) {
		this.priority = priority;
	}
	
	public String getValue() {
		return this.priority;
	}
	
	public String getPriority() {
		return toString();
	}
}


