package com.example.libraryManagement.in.entites;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ActivityLog {

	
	@Id @GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	

	@Column(nullable = false,length = 50)
	private String action;


	@Column(nullable = false,length = 50)
	private String entityType;
	
	
	@Column(name = "entity_id")
    private int entityId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    
    public ActivityLog() {}
    
    public ActivityLog(User user,String action,String entityType, int entityId,String description) 
    {
    	this.user=user;
    	this.action=action;
    	this.entityId=entityId;
    	this.entityType=entityType;
    	this.description=description;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
    
    
    
	
}
