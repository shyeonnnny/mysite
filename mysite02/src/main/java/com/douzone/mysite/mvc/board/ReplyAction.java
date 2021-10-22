package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String group_no = request.getParameter("group_no");
		String order_no = request.getParameter("order_no");
		String depth = request.getParameter("depth");
		String user_no = request.getParameter("no");
		
		int igroup_no = Integer.parseInt(group_no);
		int iorder_no = Integer.parseInt(order_no);
		int idepth = Integer.parseInt(depth);
		Long luser_no = Long.parseLong(user_no);
		BoardVo vo1 = new BoardVo();
		if(Integer.parseInt(order_no) == 1) {
			vo1.setGroup_no(Integer.parseInt(group_no));
			vo1.setDepth(Integer.parseInt(depth));
			vo1.setOrder_no(Integer.parseInt(order_no));
			new BoardDao().UpdateOrderNo1(vo1);
		} else {
			vo1.setGroup_no(Integer.parseInt(group_no));
			vo1.setDepth(Integer.parseInt(depth));
			vo1.setOrder_no(Integer.parseInt(order_no));
			new BoardDao().UpdateOrderNo2(vo1);
		}
		
		BoardVo vo2 = new BoardVo();
		vo2.setTitle(title);
		vo2.setContents(contents);
		vo2.setGroup_no(Integer.parseInt(group_no));
		vo2.setDepth(Integer.parseInt(depth) + 1);
		vo2.setOrder_no(Integer.parseInt(order_no) + 1);
		vo2.setUser_no(Long.parseLong(user_no));
		
		new BoardDao().replyInsert(vo2);
		
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);
		

		
		
		
		
		System.out.println("%%%%%%%%%5" + title +"dd"+ contents+"dd" + group_no
				+"dd"+ order_no +"dd"+ depth +"dd"+ user_no);
	}

}
