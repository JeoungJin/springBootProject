package com.kosta.myapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.kosta.myapp.vo.relation.PDSFile;

public interface PDSFileRepository extends CrudRepository<PDSFile, Long> {
	//1.기본제공되는 메서드만 가능
	//2.규칙에 맞는 함수정의가능 
	//3.JPQL : @Query이용해서 query직접사용가능 
}
