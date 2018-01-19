package com.service.tokenseeder.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.service.tokenseeder.constants.Constants;
import com.service.tokenseeder.model.Configuration;
import com.service.tokenseeder.model.SeedDateTime1;
import com.service.tokenseeder.model.SeedDateTime2;
import com.service.tokenseeder.model.SeedDateTime3;
import com.service.tokenseeder.model.TokenDecision;
import com.service.tokenseeder.utils.HibernateUtil;

/**
 * 
 * <p>
 * The <b> DateTimeToken </b> class is responsible for managing the Date Time Token The driving factors are the upper
 * and lower year limits.
 * </p>
 *
 */

public class DateTimeTokenDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeTokenDAO.class);

	ConfigDAO configDAO = null;
	Configuration config = null;
	List<Timestamp> listDateToken = null;
	TokenDecisionDAO tDecisionDAO = null;
	TokenDecision tDecision = null;
	final Calendar cal = Calendar.getInstance();

	/**
	 * <p>
	 * This method is responsible for inserting tokens into the TokenBank. Based on the Token Set details
	 * SeedDateTime<n> are populated.
	 * <p>
	 * 
	 * @param dateTimevale
	 * @param datetablename
	 * @param datetostart
	 * @param upperyearlimit
	 * @param loweryearlimit
	 * @param configurationIdentifier
	 * @return
	 */

	public Timestamp insertTokenToBank(long dateTimevale, String datetablename, String datetostart, int upperyearlimit,
			int loweryearlimit, int configurationIdentifier) {

		cal.setTimeInMillis(System.currentTimeMillis());

		final String startTimeDt = new SimpleDateFormat("HH:mm:ss:SSS").format(cal.getTime());

		LOGGER.info("Datetime: startTimeDt :" + startTimeDt);

		SimpleDateFormat formatter1 = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
		Date date1 = null;
		try {
			date1 = formatter1.parse(datetostart);
		} catch (ParseException e1) {

			LOGGER.error("HibernateException ", e1.getMessage());
		}
		Timestamp timeStampDate = new Timestamp(date1.getTime());

		long timestamp = timeStampDate.getTime();
		cal.setTimeInMillis(timestamp);

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		tx = session.beginTransaction();

		int year = cal.get(Calendar.YEAR);
		int milliSecond = cal.get(Calendar.MILLISECOND);
		int second = cal.get(Calendar.SECOND);
		int minute = cal.get(Calendar.MINUTE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);

		String dateformat = "";
		String paddedSeconds = padSeconds(milliSecond);
		int k = 0;
		try {

			for (k = 0; k < dateTimevale; ++k) {

				paddedSeconds = padSeconds(milliSecond);
				dateformat = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second + "."
						+ paddedSeconds;
				timeStampDate = Timestamp.valueOf(dateformat);
				if (loweryearlimit <= year && (year < upperyearlimit)) {
					if (("SeedDateTime1").equals(datetablename)) {
						SeedDateTime1 seedDateTime = new SeedDateTime1();
						seedDateTime.setSeedValue(timeStampDate);
						seedDateTime.setSeedUsedFlag(false);
						seedDateTime.setConfigurationIdentifier(configurationIdentifier);
						session.save(seedDateTime);
						flushToken(session, k);
					} else if (("SeedDateTime2").equals(datetablename)) {
						SeedDateTime2 seedDateTime = new SeedDateTime2();
						seedDateTime.setSeedValue(timeStampDate);
						seedDateTime.setSeedUsedFlag(false);
						seedDateTime.setConfigurationIdentifier(configurationIdentifier);
						session.save(seedDateTime);
						flushToken(session, k);
					} else {
						SeedDateTime3 seedDateTime = new SeedDateTime3();
						seedDateTime.setSeedValue(timeStampDate);
						seedDateTime.setSeedUsedFlag(false);
						seedDateTime.setConfigurationIdentifier(configurationIdentifier);
						session.save(seedDateTime);
						flushToken(session, k);
					}
				}
				milliSecond = getMillSecond(milliSecond);

				if (milliSecond > 999) {
					milliSecond = 0;
					second = getSecond(second);
					if (second > 59) {
						// Initialize second to starting value and increment minute
						second = 0;
						minute = getMinute(minute);
						if (minute > 59) {
							// Initialize minute to starting value and increment hour
							minute = 0;
							hour = getHour(hour);
							if (hour > 23) {
								// Initialize hour to starting value and date minute
								hour = 0;
								date = getDate(date);
								if (date >= 30) {
									// Initialize date to starting value and month minute
									date = 1;
									month = getMonth(month);
									if (month >= 12) {
										// Initialize month to starting value and year minute
										month = 1;
										year = getYear(year);
										if (year > 9998) {
											year = 3000;
										}
									}
								}
							}
						}
					}
				}
			}

			dateformat = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second + "."
					+ paddedSeconds;
			timeStampDate = Timestamp.valueOf(dateformat);
			session.getTransaction().commit();

		} catch (SQLGrammarException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("SQLGrammar Exception insertTokenToBank-DateTimeTokenDAO", e);
		} catch (ConstraintViolationException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("ConstraintViolation Exception insertTokenToBank-DateTimeTokenDAO", e);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOGGER.error("Hibernat Exception insertTokenToBank-DateTimeTokenDAO", e);
		} finally {
			if (session != null) {
				session.close();
			}
			cal.setTimeInMillis(0);
			cal.setTimeInMillis(System.currentTimeMillis());
			final String endTimeDt = new SimpleDateFormat("HH:mm:ss:SSS").format(cal.getTime());

			LOGGER.info("Datetime: endTimeDt :" + endTimeDt);

		}
		return timeStampDate;

	} // end of method

	private int getMillSecond(int millisecond) {
		return millisecond + 10;
	}

	private int getSecond(int second) {
		return ++second;
	}

	private int getMinute(int minute) {
		return ++minute;
	}

	private int getHour(int hour) {
		return ++hour;
	}

	private int getDate(int date) {
		return ++date;
	}

	private int getMonth(int month) {
		return ++month;
	}

	private int getYear(int year) {
		return ++year;
	}

	private void flushToken(Session session, int count) {
		if (count % 1000 == 0) {
			session.flush();
			session.clear();
		}
	}

	private String padSeconds(int milliSecond) {
		String msTemp = "0";

		msTemp = String.valueOf(milliSecond);
		int len = msTemp.length();

		switch (len) {
		case 1:
			msTemp = "00" + msTemp;
			break;
		case 2:
			msTemp = "0" + msTemp;
			break;
		default:
			break;
		}

		return msTemp;

	}

} // End of class
