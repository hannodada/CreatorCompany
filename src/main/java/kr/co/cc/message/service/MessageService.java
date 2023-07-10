package kr.co.cc.message.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.cc.archive.dto.ArchiveDTO;
import kr.co.cc.member.dto.MemberDTO;
import kr.co.cc.message.dao.MessageDAO;
import kr.co.cc.message.dto.MessageDTO;

@Service
@MapperScan(value= {"kr.co.cc.message.dao"})
public class MessageService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${spring.servlet.multipart.location}") private String root;
	
	
	private final MessageDAO dao;
	
	public MessageService(MessageDAO dao){
		this.dao = dao;
	}

	
	
	public HashMap<String, Object> sendList(HttpSession session, HashMap<String, Object> params) {
		
		int page = Integer.parseInt(String.valueOf(params.get("page")));
	    String search = String.valueOf(params.get("search"));
	    String loginId = (String) session.getAttribute("id");
	    MessageDTO loginid = dao.logincheck(loginId);
	    
	    HashMap<String, Object> map = new HashMap<String, Object>();

	    int offset = 10*(page-1);	    
		
	    logger.info("offset : " + offset);
	    
	    logger.info("params : " + params);
	    
	    int total = 0;	    
		
	    if(search.equals("default") || search.equals("")) {
	      
	    	  total = dao.sendtotalCount();

	      	}else {	      
	    	   	   
	    	  total = dao.sendtotalCountSearch(search);
	       }
	    
	    int range = total%10  == 0 ? total/10 : total/10+1;

	      page = page>range ? range:page;
	      
	      ArrayList<MessageDTO> list = null;
	      
	      params.put("offset", offset);
			
	      logger.info("user search:"+search);
	      
	      if(search.equals("default") ||search.equals("")) {

	          list = dao.sendList(offset);
	       
	     
	      }else {

	         list = dao.sendListSearch(params);
	      }
	      		
	      
	      //logger.info("list size : "+ list.size());
	      map.put("pages", range);
	      map.put("list", list);
	      map.put("currPage", page);	      
	      map.put("loginid",loginid);

	
		return map;

	}

	public ModelAndView search(HashMap<String, String> params) {
		ModelAndView mav = new ModelAndView("msSendList");
		ArrayList<MemberDTO> search = dao.search(params);
		mav.addObject("search", search);
		return mav;
	}

	//  쪽지 상세보기
	public MessageDTO msdetail(String id, String flag) {
		if(flag.equals("detail")) {
			logger.info("if문 진입");
			dao.upHit(id); // 읽음 처리
			
		}
		
		return dao.msdetail(id);
	}


	public ArrayList<String> msDetailFile(String id) {
		return dao.msDetailFile(id);
	}



	public ArrayList<MessageDTO> receiveList(HttpSession session) {
		String id = (String) session.getAttribute("id");
		return dao.receiveList(id);
	}


	public boolean msDelete(String id) {
		
		return dao.msdelete(id);
	}

	// 쪽지 작성
	public String msWrite(MultipartFile file, @RequestParam("to_id") String[] toIds, HashMap<String, String> params, HttpSession session) {
	    
	    String loginId = (String) session.getAttribute("id");
	    String page = "redirect:/msWrite.go";
	    logger.info("params: " + params);
	    logger.info("files: " + file);
	    
	        MessageDTO dto = new MessageDTO();
	        
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < toIds.length; i++) {
	            sb.append(toIds[i]);
	            if (i < toIds.length - 1) {
	                sb.append(","); // 구분자로 ',' 사용
	            }
	        }
	        dto.setTo_id(sb.toString());
	         
	        
	        dto.setFrom_id(loginId);
	        dto.setTitle(params.get("title"));
	        dto.setContent(params.get("content"));
	        logger.info("to id 갯수"+toIds.length);
	        for (String toId : toIds) {
				dto.setTo_id(toId);
				int row = dao.msWrite(dto);
				
				logger.info("insert row: " + row);
			}

	       String idx = dto.getId();

		    HashMap<String , Object> map = new HashMap<String, Object>();
		    for (String recieveId : toIds) {
		    	
		    	msNotice(loginId,recieveId,"쪽지", idx);
		    	
		    	map.clear();
			}
	       
	       
	        logger.info("idx: " + idx);

	        if (file != null && !file.isEmpty()) {
	            // 입력받은 파일 이름
	            String fileName = file.getOriginalFilename();
	            // 확장자를 추출하기 위한 과정
	            String ext = fileName.substring(fileName.lastIndexOf("."));
	            // 새로운 파일 이름은?
	            String newFileName = UUID.randomUUID().toString() + ext;
	            String classification = "쪽지";
	            try {
	                byte[] bytes = file.getBytes();

	                Path path = Paths.get(root + "/" + newFileName);
	                Files.write(path, bytes);
	                dao.msfileWrite(fileName, newFileName, classification, idx);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	       

	        
	    return "msSendSuccess";
	}




	private void msNotice(String send_id, String recieveId, String type, String identifyValue) {
		
		
		dao.msNotice(send_id,recieveId,type,identifyValue);
	}



	public String msReply(MultipartFile file, HashMap<String, String> params, HttpSession session) {
		
		String tofromId = params.get("from_id"); // 답장을 보낼 사람의 ID
		String loginId = (String) session.getAttribute("id");
		MessageDTO dto = new MessageDTO();
		
		logger.info("params :"+params);
		logger.info("files :"+file);

		dto.setFrom_id(loginId);
		dto.setTofrom_id(tofromId);
		dto.setTitle(params.get("title"));
		dto.setContent(params.get("content"));
		int row = dao.msWrite(dto);
		logger.info("insert row : "+row);
		
		String idx = dto.getId();
		
		logger.info("idx : "+idx);
		
	    if (file != null && !file.isEmpty()) {		
		// 입력받은 파일 이름
		String fileName = file.getOriginalFilename();
		// 확장자를 추출하기 위한 과정
		String ext = fileName.substring(fileName.lastIndexOf("."));
		// 새로운 파일 이름은?
		String newFileName = System.currentTimeMillis() + ext;
		String classification = "쪽지";
		try {
			byte[] bytes = file.getBytes();

			Path path = Paths.get(root + "/" + newFileName);
			Files.write(path, bytes);			
			dao.msfileWrite(fileName, newFileName,classification, idx);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	    }	
		
		return "msSendSuccess";
	}






		public boolean msSelectDelete(String id) {
			logger.info("체크한 쪽지 삭제 요청");
			
		    return dao.msSelectDelete(id);
			
		}



		public ArrayList<MessageDTO> msDeptList() {
			
			return dao.msDeptList();
		}



		public ArrayList<MessageDTO> sendMemberchk(String id) {
			logger.info("체크한 사원들 제발");
			
			return dao.sendMemberchk(id);
		}



		public ArrayList<MessageDTO> msDept() {
			
			return dao.msDept();
		}



		public String selectFile(String id) {
			
			return dao.selectFile(id);
		}





	}









