package com.tfswx.dbmigrate.sqltemplate.script;

import com.tfswx.dbmigrate.sqltemplate.Context;
import com.tfswx.dbmigrate.sqltemplate.token.GenericTokenParser;
import com.tfswx.dbmigrate.sqltemplate.token.TokenHandler;

public class TextFragment implements SqlFragment {

	private String sql;

	public TextFragment(String sql) {
		this.sql = sql;
	}

	public boolean apply(final Context context) {

		GenericTokenParser parser2 = new GenericTokenParser("${", "}",
				new TokenHandler() {

					public String handleToken(String content) {

						Object value = OgnlCache.getValue(content,
								context.getBinding());

						return value == null ? "" : value.toString();
					}
				});

		context.appendSql(parser2.parse(sql));
		return true;
	}

}
