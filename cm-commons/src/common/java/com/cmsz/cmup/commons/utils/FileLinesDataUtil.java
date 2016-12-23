package com.cmsz.cmup.commons.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

import com.cmsz.cmup.commons.logging.alarm.AlarmLogHandler;
import com.cmsz.cmup.commons.logging.system.SystemLogHandler;

public class FileLinesDataUtil {
	private static AlarmLogHandler alarmLogger = AlarmLogHandler.getLogger(FileLinesDataUtil.class);
	private static SystemLogHandler systemLogger = SystemLogHandler.getLogger(FileLinesDataUtil.class);
	String errormsg;

	/**
	 * 查找最后一行数据(代签名文件最后一行是签名数据，不是文件尾)
	 * 
	 * @param:Map<String, Object>(将文件路径put到map中，且key为”localFilePath“)
	 * @author: louiszhang
	 * @throws Exception
	 * @return String 最后一行数据
	 */
	public String getFinalLineData(Map<String, Object> variableMap) throws Exception {
		String endLine = "";
		try {
			endLine = getBackwardsLine(variableMap, 0);
		} catch (Exception e) {
			throw e;
		}

		return endLine;
	}

	/**
	 * 获得文件倒数第二行数据（即带签名文件文件尾）
	 * 
	 * @author: louiszhang
	 * @param:Map<String, Object>(将文件路径put到map中，且key为”localFilePath“)
	 * @throws Exception
	 * @return String 文件倒数第二行数据
	 */
	public String getPenultimateData(Map<String, Object> variableMap) throws Exception {
		String penulLine = "";
		try {
			String endLine = getFinalLineData(variableMap);
			long length = ((long) endLine.length()) + 4;
			penulLine = getBackwardsLine(variableMap, length);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

		return penulLine;
	}

	/**
	 * 获得倒数行，传入variableMap：包含文件路径localFilePath的参数map；length：需要去除的无关字符串长度
	 * 
	 * @author louiszhang
	 * @time 20162016年7月26日下午4:47:52
	 * @param oriFilePath
	 * @param length
	 * @return
	 */
	public String getBackwardsLine(Map<String, Object> variableMap, long length) throws Exception {
		String filePath = variableMap.get("localFilePath").toString();
		String line = "";
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(filePath, "r");
			long len = rf.length();
			long start = rf.getFilePointer();
			long nextend = start + len - 1 - length;
			rf.seek(nextend);
			int c = -1;
			int t = 0;
			while (nextend > start) {
				c = rf.read();
				if (c == '\n' || c == '\r') {
					line = rf.readLine();
					t++;
					if (t >= 1 && line != null) {
						return line;
					}
					nextend--;
				}
				nextend--;
				rf.seek(nextend);
			}
		} catch (IOException e) {
			errormsg = "#" + filePath + "#" + "文件读取异常，无法获得最后一行数据...";
			systemLogger.error(errormsg, variableMap, e);
			alarmLogger.error(errormsg, variableMap, e);
			throw e;
		} finally {
			if (null != rf) {
				rf.close();
			}
		}
		return line;
	}

	/**
	 * 检查文件是否含有回车换行(文件需要一行回车换行)
	 * 
	 * @param:Map<String, Object>(将文件路径put到map中，且key为”localFilePath“)
	 * @author: louiszhang
	 * @throws Exception
	 * @return boolean
	 */
	public boolean checkFileBlankLine(Map<String, Object> variableMap) throws Exception {
		String filePath = variableMap.get("localFilePath").toString();
		String line = "";
		try (RandomAccessFile rf = new RandomAccessFile(filePath, "r");) {
			rf.seek(rf.length() - 1);// 设置到此文件开头测量到的文件指针偏移量，在该位置发生下一个读取或写入操作。
			int c = -1;
			c = rf.read();
			if (c == '\n') {
				line = rf.readLine();
				if (line == null) {
					return true;
				}
			}
		} catch (IOException e) {
			errormsg = "#" + filePath + "#" + "文件读取异常，无法获得最后一行数据...";
			systemLogger.error(errormsg, variableMap, e);
			alarmLogger.error(errormsg, variableMap, e);
			throw e;
		}
		return false;
	}

}
