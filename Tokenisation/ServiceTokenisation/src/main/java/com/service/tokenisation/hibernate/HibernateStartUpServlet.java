package com.service.tokenisation.hibernate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import com.service.tokenisation.utility.Constants;

/**
 * Created by vijayraghavan.d on 2/12/2016.
 * 
 * Edited by Satyajit on 2/22/2016 : fetching configuration file/log file from configuration folder
 * Edited by Satyajit on 2/11/2016 : fetching configuration file/log file from external location using Context file
 */

public class HibernateStartUpServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(HibernateStartUpServlet.class);
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			HibernateUtil.configure();
			HibernateUtil.initLogPro();
			HibernateUtil.loadAllProperties();
		} catch (Exception e) {
			LOG.error(Constants.EXCEPTION, e);
		}

	}
	
	
}