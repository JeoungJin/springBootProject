package com.kosta.myapp.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kosta.myapp.vo.BoardVO;


public interface BoardRepository extends CrudRepository<BoardVO, Long>,
                                         QuerydslPredicateExecutor<BoardVO>
{
	//1. 기본 crud는 제공된다....findAll(), findById(), save(), delete(), count(), exists()
	//2.규칙에 맞는 메서드정의 추가
	//https://docs.spring.io/spring-data/jpa/docs/2.5.1/reference/html/#jpa.query-methods
    List<BoardVO> findByTitle(String title);//where title = ?
    List<BoardVO> findByWriter(String writer2);
    List<BoardVO> findByWriterAndTitle(String writer, String title);
    List<BoardVO> findByTitleLike(String title);//where title like '%'?'%'
    List<BoardVO> findByTitleContaining(String title);//where title like ?
    List<BoardVO> findByBnoGreaterThan(Long bno);//where bno>?
	
     //지현작
    List<BoardVO> findByBnoGreaterThanEqualOrderByBnoDesc(Long bno);
    //where Bno > ? order by bno desc 
    //지혜작
    List<BoardVO> findByWriterContainingOrTitleContaining(String writer, String title); 
    //찬수작 
    List<BoardVO> findByBnoLessThanEqualOrderByBno(long bno);
    
    //솔
    List<BoardVO> findByRegDateBetween(Timestamp sdate, Timestamp edate);
    //준
    List<BoardVO> findByRegDateGreaterThanEqualAndRegDateLessThanEqual(Timestamp sdate, Timestamp edate);

    
    List<BoardVO> findByRegDateBetweenOrderByBnoDesc(Timestamp sdate, Timestamp edate, Pageable paging);
    Page<BoardVO> findByRegDateGreaterThan(Timestamp sdate, Pageable paging);
    
    //3. JPQL(JPA Query Language)...join, 복잡SQL가눙 
    //*는 불가하다. 
    @Query("select b from  BoardVO b where bno >= ?2 and  b.title like %?1% order by b.title desc" )
    List<BoardVO>  selectAllByTitle(String tit, Long bno) ; 
    
    @Query("select b from  BoardVO b where bno >= :bno and  b.title like %:tt% order by b.title desc" )
    List<BoardVO>  selectAllByTitle3(@Param("tt") String tit, @Param("bno") Long bno) ; 
    
    @Query("select b from  #{#entityName} b where bno >= :bno and  b.title like %:tt% order by b.title desc" )
    List<BoardVO>  selectAllByTitle4(@Param("tt") String tit, @Param("bno") Long bno) ; 
    
    @Query("select b from  #{#entityName} b where bno >= ?2 and  b.title like %?1% order by b.title desc" )
    List<BoardVO>  selectAllByTitle5(String tit, Long bno) ; 
    
    
    //SQL문이다. (nativeQuery = true)
    @Query(value = "select * from  t_boards b  where bno >= ?2 and  b.title like %?1% order by b.title desc" , nativeQuery = true)
    List<BoardVO>  selectAllByTitle2(String tit, Long bno) ; 
    
    
    
	
}
