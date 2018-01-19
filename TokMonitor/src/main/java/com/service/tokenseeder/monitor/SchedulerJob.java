package com.service.tokenseeder.monitor;

import java.sql.Timestamp;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.service.tokenseeder.constants.Constants;
import com.service.tokenseeder.dao.ConfigDAO;
import com.service.tokenseeder.dao.TableSetDecisionDAO;
import com.service.tokenseeder.dao.TokenDecisionDAO;
import com.service.tokenseeder.model.Configuration;
import com.service.tokenseeder.model.TokenDecision;
import com.service.tokenseeder.model.TokenSetDecision;
import com.service.tokenseeder.tokenizer.GenerateToken;
import com.service.tokenseeder.utils.TokenUtil;

public class SchedulerJob {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerJob.class);
	ConfigDAO configDAO = null;
	Configuration config = null;
	List<Timestamp> listDateToken = null;
	TokenDecisionDAO tDecisionDAO = null;
	TokenDecision tDecision = null;
	TableSetDecisionDAO tableSetDAO = null;
	List<TokenSetDecision> tokenSetDecision = null;

	public void job(String region) {
		LOGGER.info("SchedulerJob>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> In job()");
		String integerTableInUse = null, stringTableInUse = null, dateTableInUse = null;
		configDAO = new ConfigDAO(region);
		config = configDAO.getConfigDetails();

		tDecisionDAO = new TokenDecisionDAO(config.getConfigurationIdentifier());
		tDecision = tDecisionDAO.getTokenDecisionDetails();

		int tableInUseByTE = tDecision.getTableSetToUseByTokenEngine();
		int tableInUseByTG = tDecision.getTableSetToUseByTokenSeeder();

		tableSetDAO = new TableSetDecisionDAO(tableInUseByTE);
		tokenSetDecision = tableSetDAO.getSetDecisionDetails();

		for (TokenSetDecision tableName : tokenSetDecision) {
			integerTableInUse = tableName.getTokenIntegerTable();
			stringTableInUse = tableName.getTokenStringTable();
			dateTableInUse = tableName.getTokenDateTimeTable();
		}

		int numberOfIntTokenInBank = (int) tableSetDAO.getNumberofTokenInTable(integerTableInUse,
				config.getConfigurationIdentifier());
		int numberOfStrTokenInBank = (int) tableSetDAO.getNumberofTokenInTable(stringTableInUse,
				config.getConfigurationIdentifier());
		int numberOfDatTokenInBank = (int) tableSetDAO.getNumberofTokenInTable(dateTableInUse,
				config.getConfigurationIdentifier());

		String sbeanchMark = TokenUtil.readFile(Constants.FILE_NAME, Constants.TOKEN_BENCHMARK);
		int beanchMark = Integer.parseInt(sbeanchMark);
		boolean isCheckIntTable = checkTableToSwitch(numberOfIntTokenInBank, beanchMark);
		boolean isCheckStrTable = checkTableToSwitch(numberOfStrTokenInBank, beanchMark);
		boolean isCheckDatTable = checkTableToSwitch(numberOfDatTokenInBank, beanchMark);
		boolean toCheck = true;
		if (isCheckIntTable == toCheck || isCheckStrTable == toCheck || isCheckDatTable == toCheck) {
			tableInUseByTG = switchTableSet(tableInUseByTE, tableInUseByTG);
			GenerateToken generateToken = new GenerateToken();
			generateToken.generateTokenInSet(tableInUseByTG, region);
		}

	}

	private boolean checkTableToSwitch(int numberOfTokenInBank, int integerBeanchMark) {
		LOGGER.info("SchedulerJob>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> In checkTableToSwitch()");
		if (numberOfTokenInBank <= integerBeanchMark) {
			return true;
		}
		return false;
	}

	private int switchTableSet(int tableInUseByTE, int tableInUseByTG) {
		LOGGER.info("SchedulerJob>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> In switchTableSet()");
		int tokenEngine = tableInUseByTE;
		int tokenGenerator = tableInUseByTG;
		if (tokenEngine == 1 && tokenGenerator == 2) {
			tokenEngine = 3;
			tokenGenerator = 1;
		} else if (tokenEngine == 2 && tokenGenerator == 3) {
			tokenEngine = 1;
			tokenGenerator = 2;
		} else if (tokenEngine == 3 && tokenGenerator == 1) {
			tokenEngine = 2;
			tokenGenerator = 3;
		}
		tDecisionDAO.updateTableSet(tokenEngine, tokenGenerator);
		return tokenGenerator;
	}
}
