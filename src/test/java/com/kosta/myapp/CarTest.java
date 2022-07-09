package com.kosta.myapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.repository.CarRepository;
import com.kosta.myapp.vo.CarVO;

@SpringBootTest
public class CarTest {

	@Autowired
	CarRepository carRepo;
	
	
	
	
	//@Test
	public void countAndExists() {
		
		Long cnt = carRepo.count();
		System.out.println("Car table의 전체건수:" + cnt);
		
		boolean result = carRepo.existsById(103L);
		System.out.println(result?"존재":"없음");
		
	}
	
	
	//삭제
	//@Test
	public void delete() {
		carRepo.deleteById(102L);
		carRepo.findById(103L).ifPresentOrElse(car->{
			carRepo.delete(car);
		}, ()->{System.out.println("삭제할 데이터가 없다");});
	}
	
	
	
	//여러개 조회 
	//@ParameterizedTest
	//@ValueSource(longs = { 102L, 103L, 999L, 1000L })
	public void aa(Long args) {
		carRepo.findById(args).ifPresentOrElse(car->{
			 System.out.println(car);
		}, ()->{System.out.println("존재하지않는다.");});
	}
	
	//@Test
	public void bb() {
		List<Long> carNos = new ArrayList<>();
		carNos.add(102L);carNos.add(103L);
		carNos.add(999L);carNos.add(1000L);
		carRepo.findAllById(carNos).forEach(car->{
			System.out.println(car);
		});
	}
	
	
	//수정(Update)
	//@Test
	public void update() {
		 
		carRepo.findById(106L).ifPresentOrElse(car->{
			car.setColor("red");
			car.setPrice(5000);
			car.setModel("DDD");
			carRepo.save(car);
		}, ()->{System.out.println("존재하지않는다.");});
	}
	
	
	
	//조회(Read)
	//@Test
	public void retrieveAll() {
		carRepo.findAll().forEach(car->{
			System.out.println(car);
		});
	}
	
	//입력(Create)
	//@Test
	public void insertFunction() {
		String[] arr = {"black", "white", "gray"};
		IntStream.rangeClosed(1, 10).forEach(index->{
			int num = new Random().nextInt(arr.length);
			CarVO car = CarVO.builder()
					.color(arr[num])
					.price(2000)
					.model("ABC모델")
					.build();
			carRepo.save(car);
		});
		
	}
}
