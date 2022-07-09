package com.kosta.myapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kosta.myapp.vo.relation.FreeBoard;

public interface FreeBoardRepository extends PagingAndSortingRepository<FreeBoard, Long> {
	//1.default method : findAll(), save()
	//2.규칙에 맞는 함수정의 
	public Page<FreeBoard> findByBnoGreaterThan(Long bno, Pageable page);
	//3.JPQL
	@Query(value = "select bno, count(reply.board_bno) "
			+ " from tbl_freeboards board left outer join tbl_free_replies reply on(board.bno = reply.board_bno) "
			+ " group by bno", nativeQuery = true)
	public List<Object[]> getCountReply() ;
	
	@Query(value = "select board.bno, count(reply) "
			+ " from FreeBoard board left outer join board.replies reply   "
			+ " group by board.bno" )
	public List<Object[]> getCountReply2() ;
	

	
}
