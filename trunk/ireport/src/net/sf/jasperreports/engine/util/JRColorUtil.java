/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
package net.sf.jasperreports.engine.util;

import java.awt.Color;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRColorUtil.java 1664 2007-03-26 09:48:03Z lucianc $
 */
public class JRColorUtil
{

	/**
	 *
	 */
	public static final int COLOR_MASK = Integer.parseInt("FFFFFF", 16);

	/**
	 *
	 */
	public static String getColorHexa(Color color)
	{
		String hexa = Integer.toHexString(color.getRGB() & COLOR_MASK).toUpperCase();
		return ("000000" + hexa).substring(hexa.length());
	}

}
