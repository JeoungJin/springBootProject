package com.kosta.myapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.myapp.repository.PDSBoardRepository;
import com.kosta.myapp.repository.PDSFileRepository;
import com.kosta.myapp.vo.relation.PDSBoard;
import com.kosta.myapp.vo.relation.PDSFile;

import lombok.extern.java.Log;

@SpringBootTest
@Log
@Commit //test종료후 rollback막기 
public class PDSBoardTest {

	@Autowired
	PDSBoardRepository boardRepo;
	
	@Autowired
	PDSFileRepository fileRepo;
		
	@Test
	public void boardSelectAll() {
		List<PDSBoard> blist = (List<PDSBoard>)boardRepo.findAll(Sort.by(Direction.DESC, "pwriter", "pname"));
		for(PDSBoard b:blist) {
			System.out.println(b);
		}
	}
	
	//@Test
	public void boardDelete() {
		long pid = 130L;
		
		boardRepo.deleteById(pid);
	}
	
	//board에 file추가 
	//@Test
	public void boardUpdateFileAdd() {
		long pid = 130L;
		boardRepo.findById(pid).ifPresent(board->{
			board.setPwriter("지현");
			board.setPname("비밀문서");
			List<PDSFile> flist = board.getFiles2();
			flist.remove(0);
			PDSFile file = PDSFile.builder()
					.pdsfilename("aa.jpg")
					.build();
			flist.add(file);
			boardRepo.save(board);
		});
	}
	
	
	//@Transactional
	//@Test
	public void fileNameUpdate2() {
		long fno = 126L;
		String fname = "최종문서.xls";
		int result = boardRepo.updatePDSFile(fno, fname);
		log.info(result + " 수정");
	}
	
	
	//@Test
	public void fileNameUpdate() {
		long fno = 125L;
		String fname = "파일이름수정.xls";
		fileRepo.findById(fno).ifPresent(file->{
			file.setPdsfilename(fname);
			fileRepo.save(file);
		});
	}
	
	
	//@Test
	public void getFilesCount2() { 
		List<Object[]> blist =  boardRepo.getFilesCount2();
		blist.forEach(b->{
			log.info(Arrays.toString(b));
		});
	}
	
	//@Test
	public void getFilesCount1() { 
		List<Object[]> blist =  boardRepo.getFilesCount1();
		blist.forEach(b->{
			log.info(Arrays.toString(b));
		});
	}
	
	
	//@Test
	public void getFilesInfo() {
		 
		List<Object[]> blist = boardRepo.getFilesInfo();
		blist.forEach(b->{
			log.info(Arrays.toString(b));
		});
	}
	//@Test
	public void fileSearch() {
		Long fno = 140L;
		fileRepo.findById(fno).ifPresentOrElse(f->{
			log.info(f.toString());
		}, ()->{log.info(fno + "파일이 존재하지않음");});
	}
	
	//@Test
	public void retrieveById() {
		Long pid=124L;
		boardRepo.findById(pid).ifPresentOrElse(b->{
			log.info("pid:" + b.getPid());
			log.info("pname:" + b.getPname());
			log.info("pwriter:" + b.getPwriter());
			log.info("files2:" + b.getFiles2());
			log.info("files2건수 :" + b.getFiles2().size());
		}, ()->{log.info(pid + "존재하지 않는다.");});
	}
	
	
	//@Test
	public void retrieve() {
		boardRepo.findAll().forEach(board->{
			System.out.println(board);
		});
	}
	//@Test
	public void insert() {
		//Board3건 File5건 insert합시다. 
		IntStream.rangeClosed(1, 3).forEach(i->{
			PDSBoard board = PDSBoard.builder()
					.pname("메뉴얼" + i)
					.pwriter("홍길동")
					.build();
			List<PDSFile> fileList = new ArrayList<>();
			IntStream.rangeClosed(1, 5).forEach(j->{
				PDSFile file = PDSFile.builder()
						.pdsfilename("첨부파일" + j + ".doc")
						.build();
				fileList.add(file);
			});
			board.setFiles2(fileList);
			boardRepo.save(board);
			
		});
	}
	
	
	
	
}






