package com.service.tokenseeder.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.service.tokenseeder.dao.ConfigDAO;


public class JobMonitor implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(JobMonitor.class);
	SchedulerJob scheduler = new SchedulerJob();
	List<String> bList = new ArrayList<String>();

	public void doJob() {
		try {
			//bList = getBusinessEntityList();
			JobMonitor jobMonitor = new JobMonitor();
			Thread thread = new Thread(jobMonitor);
			ScheduledExecutorService scheduledPool = Executors.newSingleThreadScheduledExecutor();
			scheduledPool.scheduleWithFixedDelay(thread, 0, 1, TimeUnit.MINUTES);
		} catch (Exception e) {
			LOGGER.error("Start Job Exception ", e);
		}

	}

	@Override
	public void run() {
		bList = getBusinessEntityList();
		for (String businessList : bList) {
			try {
				scheduler.job(businessList);
			} catch (Exception e) {
				LOGGER.error("Run Method Exception ", e);
			}
		}

	}

	private List<String> getBusinessEntityList() {
		ConfigDAO cdao = new ConfigDAO();
		bList = cdao.getRegions();
		return bList;
	}

}
