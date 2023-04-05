// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.common.type;

/**
 * 数据库表类型:视图表、物理表
 *
 * @author tfswx
 */
public enum DBTableType {
  /**
   * 物理表
   */
  TABLE(0),

  /**
   * 视图表
   */
  VIEW(1);

  private int index;

  DBTableType(int idx) {
    this.index = idx;
  }

  public int getIndex() {
    return index;
  }
}
