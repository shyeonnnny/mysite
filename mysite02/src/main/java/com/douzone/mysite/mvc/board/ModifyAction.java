package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");

		BoardVo vo = new BoardVo();
		vo.setNo(Long.parseLong(no));
		vo.setTitle(title);
		vo.setContents(contents);

		new BoardDao().update(vo);

		MvcUtil.redirect(request.getContextPath()+"/board?ba&p=1", request, response);
		
		
	}

}
