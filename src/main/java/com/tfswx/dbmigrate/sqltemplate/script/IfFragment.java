package com.tfswx.dbmigrate.sqltemplate.script;

import com.tfswx.dbmigrate.sqltemplate.Context;

public class IfFragment implements SqlFragment {

	private String test;

	private SqlFragment contents;

	private ExpressionEvaluator expression ;

	public IfFragment(SqlFragment contents, String test) {
		
		this.expression = new ExpressionEvaluator();
		this.contents = contents;
		this.test = test;
	}

	public boolean apply(Context context) {
		if (expression.evaluateBoolean(test, context.getBinding())) {

			this.contents.apply(context);

			return true;
		}
		return false;
	}

}
