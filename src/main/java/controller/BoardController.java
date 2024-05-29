package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import dao.KicBoardDAO;
import dao.KicMemberDAO;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.Comment;
import model.KicBoard;
import model.KicMember;

@WebServlet("/board/*")
public class BoardController extends MskimRequestMapping {
	
	HttpSession session;
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	session= request.getSession();	
	String nav=(String) session.getAttribute("boardid");
	String boardName=(String) session.getAttribute("boardName");
	request.setAttribute("nav", nav);
	request.setAttribute("boardName", boardName);
	
	super.service(request, response);
	}
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return "/view/index.jsp";
	}
	
	@RequestMapping("boardForm")
	public String boardForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		return "/view/board/boardForm.jsp";
	}
	@RequestMapping("boardInfo")
	public String boardInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		KicBoardDAO  dao = new KicBoardDAO();
		dao.addReadCount(num);  //readcnt++
		int count =dao.getCommmentCount(num);
		KicBoard board = dao.getBoard(num);
		List<Comment> li = dao.commentList(num);
		request.setAttribute("board", board);
		request.setAttribute("li", li);
		request.setAttribute("count", count);
		
		return "/view/board/boardInfo.jsp";
	}
	
	
	@RequestMapping("boardCommentPro")
	public String boardCommentPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	    String comment = request.getParameter("comment");
   	    int boardnum  = Integer.parseInt(request.getParameter("boardnum"));
		String id = (String) session.getAttribute("id");
		KicBoardDAO  dao = new KicBoardDAO();
		dao.insertComment(comment,boardnum,id  );	
		int count =dao.getCommmentCount(boardnum);		
		request.setAttribute("comment", comment);
		request.setAttribute("count", count);
		return "/single/boardCommentPro.jsp";
	}
	
	
	
	@RequestMapping("boardUpdateForm")
	public String boardUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http://localhost:8080/kicmodel2/board/boardInfo?num=10
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(num);
		KicBoardDAO  dao = new KicBoardDAO();
		KicBoard board = dao.getBoard(num);
		
		request.setAttribute("board", board);
		return "/view/board/boardUpdateForm.jsp";
	}
	
	
	@RequestMapping("boardUpdatePro")
	public String boardUpdatePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http://localhost:8080/kicmodel2/board/boardInfo?num=10
		String path = 
				request.getServletContext().getRealPath("/")+"img/board/";
		MultipartRequest  multi = 
				new MultipartRequest(request, path, 10 * 1024 * 1024, "UTF-8");
		int num = Integer.parseInt(multi.getParameter("num"));
		String pass = multi.getParameter("pass");
		System.out.println(num);
		KicBoardDAO dao = new KicBoardDAO();
		KicBoard    board = new KicBoard();
		KicBoard boarddb = dao.getBoard(num);
		board.setNum(num);
		board.setContent(multi.getParameter("content"));
		board.setSubject(multi.getParameter("subject"));
		board.setName(multi.getParameter("name"));
		if (multi.getFilesystemName("file1")==null)
			board.setFile1(multi.getParameter("originfile"));
		else 
		board.setFile1(multi.getFilesystemName("file1"));
		String msg = "수정 되지 않았습니다";
		String url = "boardUpdateForm?num="+num;
		System.out.println(boarddb);
		if (boarddb!=null) {		
		  if (pass.equals(boarddb.getPass())) {
			  int count = dao.boardUpdate(board);
			  if (count==1) {
					msg="수정 되었습니다";
					url="boardInfo?num="+num;
				} 
		  } else {	  msg = "비밀번호 확인 하세요";   }
		}  else {
			 msg = "게시물이 없습니다"; 
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		  return "/view/alert.jsp";		
	}
	
	
	
	@RequestMapping("boardPro")
	public String boardPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = 
				request.getServletContext().getRealPath("/")+"img/board/";
		
		MultipartRequest  multi = 
				new MultipartRequest(request, path, 10 * 1024 * 1024, "UTF-8");
		String boardid = (String) session.getAttribute("boardid");//1
		KicBoard kicboard = new KicBoard();
		kicboard.setName(multi.getParameter("name"));
		kicboard.setPass(multi.getParameter("pass"));
		kicboard.setSubject(multi.getParameter("subject"));
		kicboard.setContent(multi.getParameter("content"));
		kicboard.setFile1(multi.getFilesystemName("file1"));
		kicboard.setBoardid(boardid); //2
		System.out.println(kicboard);
		KicBoardDAO   dao = new KicBoardDAO();
		int num = dao.insertBoard(kicboard);
		String msg="게시물 등록 성공";
		String url = "boardList?boardid="+boardid;
		if (num==0) {			msg="게시물 등록 실패";	}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		  return "/view/alert.jsp";
	}
	
	@RequestMapping("boardList")
	public String boardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		KicBoardDAO dao=new KicBoardDAO();
		String boardid = request.getParameter("boardid");
		session.setAttribute("boardid", boardid);
	    if (session.getAttribute("boardid")==null) boardid="1"; //1
		
		String pageNum = request.getParameter("pageNum");//2
		session.setAttribute("pageNum", pageNum);//3
		 if (session.getAttribute("pageNum")==null) pageNum="1";  //4
		
		
		String boardName="";
		switch (boardid) {
		case "1" : boardName="공지사항"; break;
		case "2" : boardName="자유게시판"; break;
		case "3" : boardName="QnA"; break;
		default : boardName="공지사항";
		}
		session.setAttribute("boardName", boardName);
		int count = dao.boardCount(boardid);
		int limit = 3;
		int pageInt = Integer.parseInt(pageNum);
		int boardNum = count - ((pageInt-1)*limit);  //page의 ser계산
		
		int bottomLine = 3;
		int start = (pageInt - 1) / bottomLine * bottomLine + 1;//1,2,3->1 , 4,5,6->4
		int end = start + limit -1 ;// 1~ 3, 4~6, 7~9
		int maxPage = (count / limit) + (count % limit == 0 ? 0 : 1);
		if (end > maxPage) 			end = maxPage;
		
		List<KicBoard> li = dao.boardList(boardid, pageInt, limit);
		
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("pageInt", pageInt);
		request.setAttribute("boardNum", boardNum);
		
		request.setAttribute("boardName", boardName);	
		request.setAttribute("li", li);	
		request.setAttribute("boardid", boardid);	
		request.setAttribute("nav", boardid);
		request.setAttribute("count", count);
	
		
		return "/view/board/boardList.jsp";
	}
	
	@RequestMapping("boardDeleteForm")
	public String boardDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("num", request.getParameter("num"));
		return "/view/board/boardDeleteForm.jsp";
	}
	
	@RequestMapping("boardDeletePro")
	public String boardDeletePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		String boardid = (String) session.getAttribute("boardid");  //1
		KicBoardDAO dao=new KicBoardDAO();
		KicBoard boarddb = dao.getBoard(num);
		String msg="삭제 되지 않았습니다";
		String url="boardDeleteForm?num="+num;
		if (boarddb!=null) {		
			  if (pass.equals(boarddb.getPass())) {
				  int count = dao.boardDelete(num);
				  if (count==1) {msg="삭제 되었습니다";
						url="boardList?boardid="+boardid;//2
					} 
			  } else {	  msg = "비밀번호 확인 하세요";   }
			}  else {	 msg = "게시물이 없습니다"; 	}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			  return "/view/alert.jsp";		
	}
	
	

}
