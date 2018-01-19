package com.service.tokenisation.utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.service.tokenisation.exception.TokenException;

/**
 * TokenUtil class
 * 
 * @author: Satyajit
 * @version: 1
 */
public class TokenUtil {
	private static final Logger LOG = Logger.getLogger(TokenUtil.class);
	
	

	private TokenUtil() {
		super();
	}

	/**
	 * 
	 * @param upperLimit
	 * @param lowerLimit
	 * @return
	 */

	public static long generateRealTimeInt(long tokenToStartGenerate) {
		return tokenToStartGenerate + 1;

	}

	/**
	 * 
	 * @param upperLimit
	 * @param lowerLimit
	 * @return
	 * @throws TokenException
	 */
	public static Timestamp generateRealTimeDate(int upperLimit, int lowerLimit, String tokenToStartGenerate)
			throws TokenException {
		SimpleDateFormat formatter1 = new SimpleDateFormat(Constants.REQUEST_DATE_FORMAT);
		Date date1 = null;
		try {
			date1 = formatter1.parse(tokenToStartGenerate);
		} catch (ParseException e1) {
			LOG.error(Constants.EXCEPTION, e1);
			throw new TokenException(Constants.SYSTEM_ERROR);
		}
		Timestamp timeStampDate = new Timestamp(date1.getTime());

		long timestamp = timeStampDate.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		int year = cal.get(Calendar.YEAR);
		int milliSecond = cal.get(Calendar.MILLISECOND);
		int second = cal.get(Calendar.SECOND);
		int minute = cal.get(Calendar.MINUTE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);

		String dateformat = "";
		String paddedSeconds = padSeconds(milliSecond);

		if (lowerLimit <= year && (year < upperLimit)) {
			milliSecond = getMillSecond(milliSecond);
			if (milliSecond > 999) {
				milliSecond = 0;

				second = getSecond(second);
				if (second > 59) {
					second = 0;
					minute = getMinute(minute);
					if (minute > 59) {
						minute = 0;
						hour = getHour(hour);
						if (hour >= 24) {
							hour = 0;
							date = getDate(date);
							if (date >= 30) {
								date = 1;
								month = getMonth(month);
								if (month >= 12) {
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
			paddedSeconds = padSeconds(milliSecond);
			dateformat = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second + "."
					+ paddedSeconds;
			timeStampDate = Timestamp.valueOf(dateformat);
		}
		return timeStampDate;
	}

	private static int getMillSecond(int millisecond) {

		return millisecond + 10;
	}

	private static int getSecond(int sec) {
		return ++sec;
	}

	private static int getMinute(int min) {
		return ++min;
	}

	private static int getHour(int ho) {
		return ++ho;
	}

	private static int getDate(int dat) {
		return ++dat;
	}

	private static int getMonth(int mon) {
		return ++mon;
	}

	private static int getYear(int yr) {
		return ++yr;
	}

	private static String padSeconds(int milliSecond) {
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

	public static String appendIdentifier(String vaultIdentifier, String tokenToStartGenerate) {
		String sToken = tokenToStartGenerate;
		String random = Constants.STRING_APPENDER + vaultIdentifier;
		sToken = random + sToken;
		return sToken;
	}

	/**
	 * 
	 * @param upperLimit
	 * @param lowerLimit
	 * @return
	 */
	public static String generateRealTimeString(String vaultIdentifier, String tokenToStartGenerate) {
		String sToken = "";
		sToken = tokenToStartGenerate;
		char[] tokenIndex = { '0', '0', '0', '0', '0', '0', '0' };

		tokenIndex = setStartingIndex(sToken, tokenIndex, Constants.getStringToken());
		String random = Constants.STRING_APPENDER + vaultIdentifier;
		sToken = incrementTokenIndex(tokenIndex, Constants.getStringToken());
		sToken = random + sToken;

		return sToken;
	}

	public static String incrementTokenIndex(char[] tokenIndex, char[] stringTokens) {
		boolean stopAdding = false;
		for (int i = 0; (i < tokenIndex.length) && (!stopAdding); i++) {
			tokenIndex[i] = (char) (tokenIndex[i] + 1);
			if ((tokenIndex[i]) < stringTokens.length) {
				stopAdding = true;
			} else {
				tokenIndex[i] = 0;
			}
		}
		return nextToken(tokenIndex, stringTokens);

	}

	/**
	 * This function takes an initial input string and aligns the index so we can start counting from there
	 */
	public static char[] setStartingIndex(String startToken, char[] tokenIndex, char[] stringTokens) {
		for (int i = startToken.length() - 1, j = 0; i >= 0; i--, j++) {
			tokenIndex[j] = (char) indexInTokens(startToken.charAt(i), stringTokens);
		}
		return tokenIndex;
	}

	/** This function turns the index into a token */
	public static String nextToken(char[] tokenIndex, char[] stringTokens) {
		String newToken = "";
		for (int i = 0; i < tokenIndex.length; i++) {
			int j = tokenIndex[i];
			newToken = stringTokens[j] + newToken;
		}
		return newToken;
	}

	/** This function finds where in the Token Character Universe this Character occurs */
	public static int indexInTokens(char tokenChar, char[] stringTokens) {
		int idxInTokens = -1, i = 0;
		while (idxInTokens < 0 && i < stringTokens.length) {

			if (stringTokens[i] == tokenChar) {
				idxInTokens = i;
			}
			i++;
		}
		return idxInTokens;
	}

	public static String readFile(String fileName, String minimumNumToken) throws TokenException {
		Properties prop = new Properties();
		InputStream input = null;
		String mintoken = null;
		try {
			input = TokenUtil.class.getClassLoader().getResourceAsStream(fileName);
			prop.load(input);
			mintoken = prop.getProperty(minimumNumToken);
		} catch (IOException ex) {
			LOG.error(Constants.EXCEPTION, ex);
			throw new TokenException(Constants.SYSTEM_ERROR);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOG.error(Constants.EXCEPTION,e);
				}
			}
		}
		return mintoken;

	}

}