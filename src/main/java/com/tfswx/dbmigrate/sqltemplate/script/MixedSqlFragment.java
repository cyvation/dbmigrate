package com.tfswx.dbmigrate.sqltemplate.script;

import com.tfswx.dbmigrate.sqltemplate.Context;

import java.util.List;

/**
 * 
 * @author Wen
 *
 */
public class MixedSqlFragment implements SqlFragment {
	
	private List<SqlFragment> contents ;
	
	public MixedSqlFragment(List<SqlFragment> contents){
		this.contents  = contents ;
	}

	public boolean apply(Context context) {
		
		for(SqlFragment sf : contents){
			sf.apply(context);
		}
		
		return true;
	}
	
	
	
	

}
