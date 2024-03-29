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
 * FieldsProvider.java
 *
 * Created on December 5, 2006, 8:55 PM
 *
 */

package it.businesslogic.ireport;

import it.businesslogic.ireport.gui.ReportQueryDialog;
import java.awt.Component;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author gtoffoli
 */
public interface FieldsProvider {
    
    /**
     * Returns true if the provider supports the {@link #getFields(IReportConnection,JRDataset,Map) getFields} 
     * operation. By returning true in this method the data source provider indicates
     * that it is able to introspect the data source and discover the available fields.
     * 
     * @return true if the getFields() operation is supported.
     */
    public boolean supportsGetFieldsOperation();
    
    /**
     * Returns the fields that are available from a query of a specific language
     * The provider can use the passed in report to extract some additional
     * configuration information such as report properties. 
     * The IReportConnection object can be used to execute the query.
     * 
     * @param con the IReportConnection active in iReport. 
     * @param the JRDataset that will be filled using the data source created by this provider.
     * 	The passed in report can be null. That means that no compiled report is available yet.
     * @param parameters map containing the interpreted default value of each parameter
     * @return a non null fields array. If there are no fields then an empty array must be returned.
     * 
     * @throws UnsupportedOperationException is the method is not supported
     * @throws JRException if an error occurs.
     */
    public JRField[] getFields(IReportConnection con,  JRDataset reportDataset, Map parameters ) throws JRException, UnsupportedOperationException;
    
    /**
     * Returns true if the getFields can be run in a backgroiund thread each time the user changes the query.
     * This approach can not be valid for fieldsProviders that require much time to return the list of fields.
     */
    public boolean supportsAutomaticQueryExecution();
    
    /**
     * Returns true if the FieldsProvider can run an own query designer
     */
    public boolean hasQueryDesigner();
    
    /**
     * Returns true if the FieldsProvider can run an own editor
     */
    public boolean hasEditorComponent();
    
    /**
     * This method is used to run a query designer for the specific language.
     *
     * @param con the IReportConnection active in iReport. 
     * @param query the query to modify
     * @param reportQueryDialog the parent reportQueryDialog. It can be used to get all (sub)dataset informations
     * with reportQueryDialog.getSubDataset();
     *
     */
    public String designQuery(IReportConnection con,  String query, ReportQueryDialog reportQueryDialog ) throws JRException, UnsupportedOperationException;
    
    
    /**
     * The component that will stay on the right of the query panel. To listen for query changes, the component must implement
     * the interface FieldsProviderEditor. The component will be visible only when a queryCahnged is succesfully executed.
     * The component can store the reference to the report query dialog in which it will appear.
     *
     * The editor can 
     */
    public FieldsProviderEditor getEditorComponent( ReportQueryDialog reportQueryDialog );
    
}
