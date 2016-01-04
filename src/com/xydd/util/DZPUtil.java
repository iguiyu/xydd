package com.xydd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.xydd.weixin.pojo.DZPStatic;

/**
 * 抽奖生成工具类
 * 
 * @author vincent
 * @date 2013-05-18
 */
public class DZPUtil {
	// 起头

	
	public static long getPrize() {
		long result=5;
		int number=0;
		if(DZPStatic.prize1Count<DZPStatic.prize1MaxCount)
		{
			number= new Random().nextInt(DZPStatic.prize1) + 1; 
			if(number==DZPStatic.prize1-1)
			{
				result=1;
				DZPStatic.prize1Count++;
				return result;
			}
		}
		if(DZPStatic.prize2Count<DZPStatic.prize2MaxCount)
		{
			number= new Random().nextInt(DZPStatic.prize2) + 1; 
			if(number==DZPStatic.prize2-1)
			{
				result=2;
				DZPStatic.prize2Count++;
				return result;
			}
		}
		if(DZPStatic.prize3Count<DZPStatic.prize3MaxCount)
		{
			number= new Random().nextInt(DZPStatic.prize3) + 1; 
			if(number==DZPStatic.prize3-1)
			{
				result=3;
				DZPStatic.prize3Count++;
				return result;
			}
		}
		if(DZPStatic.prize4Count<DZPStatic.prize4MaxCount)
		{
			number= new Random().nextInt(DZPStatic.prize4) + 1; 
			if(number==DZPStatic.prize4-1)
			{
				result=4;
				DZPStatic.prize4Count++;
				return result;
			}
		}
		return result;
	}
	
	
	
	
	public static void main(String[] args) {
		for(int i=0;i<10000;i++)
		{
			long prize=DZPUtil.getPrize();
			if(prize<2)
			System.out.println("["+prize+"]");
		}
	}
}
