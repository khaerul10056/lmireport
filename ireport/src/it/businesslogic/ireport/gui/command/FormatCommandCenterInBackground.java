/*
 * Copyright (C) 2005 - 2008 JasperSoft Corporation.  All rights reserved. 
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from JasperSoft,
 * the following license terms apply:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; and without the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/gpl.txt
 * or write to:
 *
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330,
 * Boston, MA  USA  02111-1307
 *
 *
 *
 *
 * FormatCommandCenterInBackground.java
 * 
 * Created on 9 mei 2005, 21:36
 *
 */

package it.businesslogic.ireport.gui.command;

import it.businesslogic.ireport.OperationType;
import java.awt.Point;

/**
 *
 */
public class FormatCommandCenterInBackground extends FormatCommand {
    {
        operationType = OperationType.CENTER_IN_BACKGROUND;
    }
        
    int centerx;
    int centery;   
    
    void preparation() {
        //centerx = 10 + jrf.getReport().getWidth()/2;
        int insideWidth = jrf.getReport().getWidth() - jrf.getReport().getRightMargin() - jrf.getReport().getLeftMargin() ;
        centerx = jrf.getReport().getLeftMargin() + 10+ insideWidth/2;
    }
    
    void modify() {
    	if (re.getBand().getName().equals("background"))
    	{
	        int centery = jrf.getReport().getBandYLocation(re.getBand()) + 10 + re.getBand().getHeight()/2 ;
        	re.setPosition(new Point(centerx-(re.getWidth()/2), centery-(re.getHeight()/2)));
        }
    }
    
}
