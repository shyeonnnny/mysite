package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String wno = request.getParameter("no");
		
		BoardVo vo = new BoardVo();
		BoardRepository dao = new BoardRepository();
		vo = dao.findByNo(Long.parseLong(wno));
		request.setAttribute("vo", vo);
		
		MvcUtil.forward("board/reply", request, response);

	}

}
