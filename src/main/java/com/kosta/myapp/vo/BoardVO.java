package com.kosta.myapp.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString 
@AllArgsConstructor @NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "t_boards")
public class BoardVO {

	@Id
	@GeneratedValue( strategy =GenerationType.AUTO) //번호 자동생성기 
	//create sequence hibernate_sequence start with 1 increment by  1
	private Long bno;
	
	@NonNull
	@Column(nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String content;
	private String writer;
	
	@CreationTimestamp
	private Timestamp regDate;
	
	@UpdateTimestamp
	private Timestamp updateDate;
	
}
