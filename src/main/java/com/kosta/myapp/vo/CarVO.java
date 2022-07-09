package com.kosta.myapp.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString 
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "t_cars")
public class CarVO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long carNO;
	private String model;
	private int price;
	private String color;
	 
	
}
