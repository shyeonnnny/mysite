package com.douzone.mysite.mvc.main;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		// TODO Auto-generated method stub
		return new MainAction();
	}

}
