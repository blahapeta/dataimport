/**
 * IBM CSOB BPM2
 *
 * Copyright (c) 2016 International Business Machines. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of IBM.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered
 * into with IBM.
 *
 * Note to US Government users: Documentation related to restricted rights
 * Use, duplication or disclosure is subject to restrictions set forth in
 * GSA ADP Schedule with IBM Corp.
 *
 * IBM MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. IBM SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.cleverdata.dataimport.config;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;


@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {ServiceConfig.class};
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		 return new String[]{"/"};
	}


	@Override
	protected Filter[] getServletFilters() {
	    CharacterEncodingFilter charFilter = new CharacterEncodingFilter();
	    charFilter.setEncoding("UTF-8");
	    charFilter.setForceEncoding(true);
	    return new Filter[] { new HiddenHttpMethodFilter(), charFilter,
	        new HttpPutFormContentFilter() };
	}

}
