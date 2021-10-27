package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인을 안했을경우 글쓰기버튼을 눌러도 글쓰기 창으로 이동하지 않도록 설정
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.forward("/board/notlogin", request, response);
			return;
		}
		
		
		
		String no = request.getParameter("no");

		BoardVo vo = new BoardRepository().BoardView(Long.parseLong(no));
		new BoardRepository().hitUpdate(Long.parseLong(no));
		request.setAttribute("vo", vo);
		MvcUtil.forward("board/view", request, response);
	}

}
