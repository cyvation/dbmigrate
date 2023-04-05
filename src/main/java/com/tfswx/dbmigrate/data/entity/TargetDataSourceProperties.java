// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tfswx (cyvation@tfswx.com)
// Date : 2023/3/2
// Location: chengdu , china
/////////////////////////////////////////////////////////////
package com.tfswx.dbmigrate.data.entity;

import java.util.concurrent.TimeUnit;
import lombok.Data;

@Data
public class TargetDataSourceProperties {

  private String ip;
  private String port;
  private String url;
  private String driverClassName;
  private String username;
  private String password;
  private Long connectionTimeout = TimeUnit.SECONDS.toMillis(60);
  private Long maxLifeTime = TimeUnit.MINUTES.toMillis(30);

  private String targetSchema = "";
  private Boolean targetDrop = Boolean.TRUE;
  private Boolean onlyCreate = Boolean.FALSE;
  private Boolean createTableAutoIncrement = Boolean.FALSE;
  private Boolean writerEngineInsert = Boolean.FALSE;
  private Boolean changeDataSync = Boolean.FALSE;
}
