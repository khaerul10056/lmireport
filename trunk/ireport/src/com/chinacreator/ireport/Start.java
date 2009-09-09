/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package com.chinacreator.ireport;

import it.businesslogic.ireport.gui.MainFrame;

/**
 * @author 李茂
 * @since 3.0
 * @version $Id: Start.java Aug 3, 2009 11:44:04 AM $
 *
 */
//begin Start.java
public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//args = new String[]{"192.168.11.110","10086","20090820160844234589","系统管理员","127.0.0.1"};
	    AddedOperator.getInstance().beforeIreportLoadCheck();
		AddedOperator.getInstance().initRemoteArgs(args);

		MainFrame.main(null);
	}

}

//end Start.java