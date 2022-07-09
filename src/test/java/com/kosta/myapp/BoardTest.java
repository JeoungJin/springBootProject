package com.kosta.myapp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.kosta.myapp.repository.BoardRepository;
import com.kosta.myapp.vo.BoardVO;

@SpringBootTest
public class BoardTest {

	@Autowired
	BoardRepository boardRepo ;
	
	@Test
	public void jpqlTest1() {
		//List<BoardVO> blist = boardRepo.selectAllByTitle("목", 50L);
		//List<BoardVO> blist = boardRepo.selectAllByTitle2("목", 50L);
		//List<BoardVO> blist = boardRepo.selectAllByTitle3("목", 90L);
		//List<BoardVO> blist = boardRepo.selectAllByTitle4("목", 90L);
		List<BoardVO> blist = boardRepo.selectAllByTitle5("목", 90L);
		blist.forEach(b->{
			 System.out.println(b);
		 });
	}
	
	
	
	//@Test
	public void solPageInfo() {
		Timestamp s = Timestamp.valueOf("2022-06-30 00:00:00");
		Pageable paging = PageRequest.of(0, 5, Sort.by(Direction.DESC, "writer","regDate" )); //page는 0부터시작, 1page의 건수는 10으로 설정
		
		 Page<BoardVO> result = boardRepo.findByRegDateGreaterThan(s,paging);
		 
		 System.out.println("페이지번호:" + result.getNumber());
		 System.out.println("전체건수:" + result.getTotalElements());
		 System.out.println("페이지수:" + result.getTotalPages());
		 System.out.println("한페이지의건수:" + result.getNumberOfElements());
		 
		 List<BoardVO> blist = result.getContent();
		 blist.forEach(b->{
			 System.out.println(b);
		 });
		
	}
	
	
	//@Test
	public void solPagingSort() {
		Timestamp s = Timestamp.valueOf("2022-06-30 00:00:00");
		Timestamp e = Timestamp.valueOf("2022-06-31 00:00:00");
		Pageable paging = PageRequest.of(1, 5, Sort.by(Direction.DESC, "writer","regDate" )); //page는 0부터시작, 1page의 건수는 10으로 설정
		
		boardRepo.findByRegDateBetweenOrderByBnoDesc(s, e, paging).forEach(b->{
			System.out.println(b);
		});
	}
	
	//@Test
	public void solPaging() {
		Timestamp s = Timestamp.valueOf("2022-06-30 00:00:00");
		Timestamp e = Timestamp.valueOf("2022-06-31 00:00:00");
		Pageable paging = PageRequest.of(1, 10); //page는 0부터시작, 1page의 건수는 10으로 설정
		
		boardRepo.findByRegDateBetweenOrderByBnoDesc(s, e, paging).forEach(b->{
			System.out.println(b);
		});
	}
	
	//@Test
	public void jun() {
		Timestamp s = Timestamp.valueOf("2022-06-30 09:22:50.744");
		Timestamp e = Timestamp.valueOf("2022-06-30 09:22:50.822");

		boardRepo.findByRegDateGreaterThanEqualAndRegDateLessThanEqual(s, e).forEach(b->{
			System.out.println(b);
		});
	}
	
	//@Test
	public void sol() {
		Timestamp s = Timestamp.valueOf("2022-06-30 09:22:50.744");
		Timestamp e = Timestamp.valueOf("2022-06-30 09:22:50.822");

		boardRepo.findByRegDateBetween(s, e).forEach(b->{
			System.out.println(b);
		});
	}
	
	
	//@Test
	public void chan() {
		
		boardRepo.findByBnoLessThanEqualOrderByBno(10L).forEach(b->{
			System.out.println(b);
		});
	}
	
	//@Test
	public void ji() {
		String writer = "J";
		String title="9";
		
		boardRepo.findByWriterContainingOrTitleContaining(writer,title).forEach(b->{
			System.out.println(b);
		});
	}
	
	//@Test
	public void jihyun() {
		boardRepo.findByBnoGreaterThanEqualOrderByBnoDesc(90L).forEach(b->{
			System.out.println(b);
		});
	}
	
	
	//@Test
	public void selectByGrater() {
		boardRepo.findByBnoGreaterThan(90L).forEach(b->{
			System.out.println(b);
		});
	}
	
	
	//@Test
	public void selectByContaining() {
		boardRepo.findByTitleContaining("제목").forEach(b->{
			System.out.println(b);
		});
	}
	//@Test
	public void selectByLike() {
		boardRepo.findByTitleContaining("%제목%").forEach(b->{
			System.out.println(b);
		});
	}
	//@Test
	public void selectByAnd() {
		boardRepo.findByWriterAndTitle("KIM", "title update").forEach(b->{
			System.out.println(b);
		});
	}
	//@Test
	public void selectByWriter() {
		boardRepo.findByWriter("KIM").forEach(b->{
			System.out.println(b);
		});
	}
	
	//@Test
	public void selectByTitle() {
		boardRepo.findByTitle("title update").forEach(b->{
			System.out.println(b);
		});
	}
	
	
	
	//@Test
	public void countAndExists() {
		
		Long cnt = boardRepo.count();
		System.out.println("board table의 전체건수:" + cnt);
		
		boolean result = boardRepo.existsById(1L);
		System.out.println(result?"존재":"없음");
		
	}
	
	
	
	
	
	//수정
	//@Test
	public void test6() {
		boardRepo.findById(2L).ifPresentOrElse (board->{
			board.setTitle("title update");
			board.setContent("content update");
			board.setWriter("KIM");
			boardRepo.save(board);
		}, ()->{
			BoardVO board = BoardVO.builder()
					.title("제목 new")
					.content("내용 new")
					.writer("KIM")
					.build();
			boardRepo.save(board);
		});
		 
				 
		
		
	}
	


		
		
	
	//1건조회
	//@Test
	public void test5() {
		Optional<BoardVO> board = boardRepo.findById(1L);
		if(board.isPresent()) {
			BoardVO b = board.get();
			System.out.println(b);		
		}else {
			System.out.println("존재하지않는다.");
		}
		
	}
	//@Test
	public void test4() {
		boardRepo.findById(1L).ifPresentOrElse(board->{
			System.out.println(board);
		}, ()->{System.out.println("존재하지않는다.");}); 
	}
	
	//@Test
	public void test3() {
		boardRepo.findById(101L).ifPresent(board->{
			System.out.println(board);
		});
	}
	
	
	
	//@Test
	public void test2() {
		List<BoardVO>  boardList = (List<BoardVO>) boardRepo.findAll(); //select ~ from t_boards
		boardList.forEach(board->{
			System.out.println(board);
		});
		
	}
	
	
	//@Test
	public void test1() {
		
		IntStream.rangeClosed(1, 10).forEach(i->{
			
			BoardVO board = BoardVO.builder()
					.title("목요일"+ i)
					.content("비가온다" + i)
					.writer("Jeoung")
					.build();
			
			boardRepo.save(board); //insert into t_boards values()
		});
	}
}





