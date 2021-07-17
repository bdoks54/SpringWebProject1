package com.myspring.sns.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.sns.HomeController;
import com.myspring.sns.board.service.BoardService;
import com.myspring.sns.board.vo.BoardVO;
import com.myspring.sns.comment.service.CommentService;
import com.myspring.sns.comment.vo.CommentVO;

@Controller
public class BoardController {
	private static String ARTICLE_IMAGE_REPO = "c:\\spring\\image_repo";
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	private BoardService service;
	@Inject
	private CommentService commentService;

	@RequestMapping(value = "/board/listBoard.do", method = RequestMethod.GET)
	public ModelAndView listBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName= getViewName(request);
		ModelAndView mav = new ModelAndView(viewName);
		String searchStr = request.getParameter("searchStr");
		if(searchStr==null)searchStr="";
		String viewType = request.getParameter("viewType");
		if(viewType==null)viewType="0";
		int page=1;
		
		if(viewType.equals("1")) {
			List<BoardVO> boardList = service.selectImgBoard(page);
			List<CommentVO> commentList = new ArrayList();
			
			for(BoardVO vo : boardList) {
			int boardNum = vo.getBoard_number();		
			List<CommentVO> commentList2 = commentService.getCommentList(boardNum);
			for(CommentVO cvo : commentList2) {
				commentList.add(cvo);
			}
			}
			mav.addObject("boardList", boardList);
			mav.addObject("commentList", commentList);
		}else {
			List boardList = service.selectBoard(page);
			List commentList = commentService.selectCommentList(page);
			mav.addObject("boardList", boardList);
			mav.addObject("commentList", commentList);
			}
		return mav;
	}

	@RequestMapping(value="/board/viewBoard.do", method=RequestMethod.GET)
	public ModelAndView viewBoard(@RequestParam("board_num") int board_number,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName=getViewName(request);
		BoardVO boardVO = service.selectBoardByNum(board_number);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("board", boardVO);
		return mav;
	}

	@RequestMapping(value="/board/boardForm.do", method= RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName=getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = "/board/addBoard.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception{
		multipartRequest.setCharacterEncoding("utf-8");

		Map<String, Object> boardMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = multipartRequest.getParameter(name);
			
			if(name.equals("board_content")) {
				value= value.replace("<", "&lt;");
				value= value.replace(">", "&gt;");
				value= value.replace("\r\n", "<br>");
			}else if(name.equals("board_title")) {
				value= value.replace("<", "&lt;");
				value= value.replace(">", "&gt;");
			}			
			boardMap.put(name, value);
		}
		String imageFileName = upload(multipartRequest);
		boardMap.put("board_imgname", imageFileName);
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int boardNO = service.insertBoard(boardMap);

			if(imageFileName != null && imageFileName.length() !=0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+boardNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/listBoard.do'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e){
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			srcFile.delete();

			message = " <script>";
			message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/board/boardForm.do'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	//한개 이미지 업로드하기
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		String imageFileName= null;
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while(fileNames.hasNext()){
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			imageFileName=mFile.getOriginalFilename();
			File file = new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\" + fileName);
			if(mFile.getSize()!=0){ //File Null Check
				if(!file.exists()){ //경로상에 파일이 존재하지 않을 경우
					file.getParentFile().mkdirs();  //경로에 해당하는 디렉토리들을 생성
					mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+imageFileName)); //임시로 저장된 multipartFile을 실제 파일로 전송
				}
			}

		}
		return imageFileName;
	}


	@RequestMapping("/download.do")
	protected void download(@RequestParam("imageFileName") String imageFileName,
			@RequestParam("board_num") String board_number,
			HttpServletResponse response)throws Exception {
		OutputStream out = response.getOutputStream();
		String downFile = ARTICLE_IMAGE_REPO + "\\" +board_number+"\\"+ imageFileName;
		File file = new File(downFile);

		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer); 
			if (count == -1) 
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}




	@RequestMapping(value = "/board/deleteBoard.do", method= RequestMethod.GET)
	public ModelAndView delBoard(@ModelAttribute("board_num") int board_num,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		int result= service.deleteBoard(board_num);
		int number = service.selectMaxNum();
		int alterAI = service.alterAI(number+1);
		
		File file = new File("C:\\spring\\image_repo\\"+Integer.toString(board_num));
		try {
			if(file.exists()) {
			File[] fileList = file.listFiles();
			for(int i=0;i<fileList.length;i++) {
				if(fileList[i].isFile()) {
					fileList[i].delete();
				}else {
					
				}
				fileList[i].delete();
			}
			file.delete();
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView("redirect:/board/listBoard.do");
		return mav;
	}

	@RequestMapping(value = "/board/updateForm.do",method=RequestMethod.GET)
	public ModelAndView updateFrom(@ModelAttribute("board_num") int board_num, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String viewName = getViewName(request);
		BoardVO boardVO = service.selectBoardByNum(board_num);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("board", boardVO);
		return mav;
	}

	@RequestMapping(value = "/board/updateBoard.do", method=RequestMethod.POST)
	public ModelAndView updateBoard(@ModelAttribute("board") BoardVO vo,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		int result = service.updateBoard(vo);
		ModelAndView mav = new ModelAndView("redirect:/board/listBoard.do");
		return mav;
	}
	
	@RequestMapping(value = "/board/countBoard.do", method= RequestMethod.GET)
	public ModelAndView countBoard(@ModelAttribute("board_num") int board_num,
									HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		int result= service.countBoard(board_num);
		ModelAndView mav = new ModelAndView("redirect:/board/listBoard.do");
		return mav;
	}
	
	@RequestMapping(value = "/comment/addComment.do", method = RequestMethod.POST)
	public ModelAndView addComment(@ModelAttribute("comment") CommentVO vo,
									HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String content = vo.getComment_content();
		content = content.replace("<", "&lt;");
		content = content.replace(">", "&gt;");
		content = content.replace("\r\n", "<br>");
		vo.setComment_content(content);
		int result = commentService.insertComment(vo);
		ModelAndView mav = new ModelAndView("redirect:/board/listBoard.do");
		return mav;
	}

	@RequestMapping(value = "/comment/deleteComment.do", method = RequestMethod.GET)
	public ModelAndView deleteComment(@ModelAttribute("comment_num")int comment_num,HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		int result = commentService.deleteComment(comment_num);
		ModelAndView mav = new ModelAndView("redirect:/board/listBoard.do");
		return mav;
	}
	

	
	@RequestMapping(value = "board/getBoardList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBoardList(HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<BoardVO> boardList = null;
		int page= Integer.parseInt(request.getParameter("page"));
		List<CommentVO> commentList = commentService.selectCommentList(page);
		boardList = service.selectBoard(page);
		resultMap.put("boardList", boardList);
		resultMap.put("commentList", commentList);
		return resultMap; 
	}
	
	@RequestMapping(value = "board/getImgBoardList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getImgBoardList(HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<BoardVO> boardList = null;
		int page= Integer.parseInt(request.getParameter("page"));
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		boardList = service.selectImgBoard(page);
		
		for(BoardVO vo: boardList) {
			int boardNum = vo.getBoard_number();
			List<CommentVO> commentList2 = commentService.getCommentList(boardNum);
			
			for(CommentVO cvo: commentList2) {
				commentList.add(cvo);
			}
		}
		resultMap.put("boardList", boardList);
		resultMap.put("commentList", commentList);
		return resultMap; 
	}
	
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;
	}
}
