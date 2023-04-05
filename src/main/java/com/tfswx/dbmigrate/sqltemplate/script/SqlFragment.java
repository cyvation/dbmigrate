package com.tfswx.dbmigrate.sqltemplate.script;

import com.tfswx.dbmigrate.sqltemplate.Context;


/**
 * 
 * @author Wen
 *
 */
public interface SqlFragment {
	boolean apply(Context context ) ;

}
