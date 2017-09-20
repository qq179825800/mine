package cn.lin.code.util;

import cn.getgrid.common.api.configuration.YesNoEnum;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtil {
	public static DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 将日期类型转为字符串日期
	 * @param date
	 * @return
	 */
	public static String toStringDate(Date date){
		if(null == date){
			return null;
		}
		return fmt.format(date);
	}
	
	/**
	 * 获取系统当前日期
	 * @return
	 */
	public static String getCurrentDate(){
		Date date = new Date();
		return fmt.format(date);
	}
	/**
	 * 获取系统当前时间
	 * @return
	 */
	public static String getCurrentTime(){
		Date date = new Date();
		return dateFmt.format(date);
	}
	/**
	 * 获取相应的一段时间
	 * @param type 1：一天、2：一周、3：一月、4：一季、5：一年、6：三年、7：永久
	 * @return 
	 */
	public static String[] getPeriodTime(int timeType){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		String startDate = fmt.format(cal.getTime());
		String endDate = "";
		if(timeType == 1){
			endDate = fmt.format(cal.getTime());
		}else if(timeType == 2){  //一周
			 cal.add(Calendar.WEEK_OF_YEAR, +1);
			 endDate = fmt.format(cal.getTime());
		}else if(timeType == 3){  //一月
			 cal.add(Calendar.MONTH, +1);
			 endDate = fmt.format(cal.getTime());
		}else if(timeType == 4){  //一季
			 cal.add(Calendar.MONTH, +3);
			 endDate = fmt.format(cal.getTime());
		}else if(timeType == 5){  //一年
			 cal.add(Calendar.YEAR, +1);
			 endDate = fmt.format(cal.getTime());
		}else if(timeType == 6){  //三年
			 cal.add(Calendar.YEAR, +3);
			 endDate = fmt.format(cal.getTime());
		}else if(timeType == 7){
			endDate = "2099-12-31";
		}
		return new String[] { startDate, endDate };
	}
	
	/**
	 * 根据时间段获取时间类型
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer getTimeType(String startDate,String endDate){
		Integer timeType = null;
		if(StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)){
			return null;
		}else{
			if(startDate.equals(endDate)){
				timeType = 1;
			}else if(endDate.equals("2099-12-31")){
				timeType = 7;
			}else{
				try {
					Date smdate = fmt.parse(startDate);
					Date bdate = fmt.parse(endDate);
					int days = daysBetween(smdate,bdate);
					if(days == 7){//一周
						timeType = 2;
					}else if(days == 30 || days == 31){  //一月
						timeType = 3;
					}else if(days <=93){  //一季
						timeType = 4;
					}else if(days ==365 || days == 366){   //一年
						timeType = 5;
					}else if(days >= 365*3 && days <= 366*3 ){  //三年
						timeType = 6;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return timeType;
	}
	
	
	 /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate){    
    	long between_days = 0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        try {
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate));  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(smdate);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(bdate);    
	        long time2 = cal.getTimeInMillis();         
	        between_days=(time2-time1)/(1000*3600*24);  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return Integer.parseInt(String.valueOf(between_days)); 
    }  
    
    
   
    /**
     * 获得日期周- 周一和周日的日期
     * @return[周一日期,周日日期]
     */
    @SuppressWarnings("deprecation")
	public static String[] dateToWeek(String datePoint) {
    	String startWeek = "";
    	String endWeek = "";
    	try {
    		System.out.println("传入的时间为"+datePoint);
			Date mdate = fmt.parse(datePoint);
			int b = mdate.getDay();
			if(b == 0){
				b = 7;
			}
			Date fdate;
			List<Date> list = new ArrayList<Date>();
			Long fTime = mdate.getTime() - b * 24 * 3600000;
			for (int a = 1; a <= 7; a++) {
				fdate = new Date();
				fdate.setTime(fTime + (a * 24 * 3600000));
				list.add(a-1, fdate);
			}
			 startWeek = fmt.format(list.get(0));
			 endWeek = fmt.format(list.get(6));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return new String[] {startWeek,endWeek};
//    	return null;
	}
    
    
    /** 
     * 根据日期获得星期 
     * @param date 
     * @return 
     * { "0", "1", "2", "3", "4", "5", "6" }
     */ 
	public static String getWeekOfDate(Date date) { 
	  String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" }; 
	  Calendar calendar = Calendar.getInstance(); 
	  calendar.setTime(date); 
	  int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
	  return weekDaysCode[intWeek]; 
	} 
	
	
	 /**
	  * 获取一周前的日期
	  * @return
	  */
	 public static String getUpWeek(Date date){
	    Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        date = calendar.getTime();
        String time = fmt.format(date);
        return time;
	 }
	 
	 /**
	  * 根据日期、类型(向上或向下)、天数获取日期
	  * @param dateTime-日期
	  * @param upDown-1:增加天数 2:减少天数
	  * @param days-天数
	  * @return
	  */
	 public static String getDayByType(String dateTime,int upDown,int days){
		 Date mdate;
		 Date fdate = new Date();
		 Long fTime = 0l;
		try {
			System.out.println("传入的时间为"+dateTime);
			mdate = fmt.parse(dateTime);
		    if(upDown == 1){ //向上
		    	fTime = mdate.getTime() + days * 24 * 3600000;
			 }else if(upDown == 2){ //向下
				fTime = mdate.getTime() - days * 24 * 3600000;
			 }
			fdate.setTime(fTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String resultTime =  fmt.format(fdate);
		return resultTime;
	 }
	 
	 /**
	  *  获取增加后的时间
	  * 
	 * @param dateTime 需要增加的时间
	 * @param minute  增加的分钟数
	 * @return
	 * @throws ParseException 
	 */
	public static String getAddTime(String dateTime, int minute) throws ParseException{
		 
		Date afterDate = new Date(dateFmt.parse(dateTime).getTime() + minute*60000);
		return dateFmt.format(afterDate );
		 
	 }
	
	/**
	 * 比较两个时间大小
	 * 
	 * @param time1
	 * @param time2
	 * @return state 大于0：time1<time2; 
	 * 				   小于0：time1>time2; 
	 * 				   等于0：time1=time2;
	 */
	public static int compareTime(String time1, Date time2){
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		int state = 0;
		try {
			c1.setTime(dateFmt.parse(time1));
			c2.setTime(dateFmt.parse(dateFmt.format(time2)));
			int result=c1.compareTo(c2);
			if (result < 0) {
				state = YesNoEnum.NO.getCode();
			} else if (result > 0){
				state = YesNoEnum.YES.getCode();
			} else {
				state = YesNoEnum.NO.getCode();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return state;
	}
	
	/**
	 * @说明: 返回时间路径：yyMM/dd/hh,作为文件上传文件路径
	 * @return
	 * @创建人：黄小虎
	 * @创建时间:2016年12月1日 上午10:02:11
	 */
	public static String getDetailTime(){
		String result = "";
		//获取当前时间的年月日和小时
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH"); 
		String dateNowStr = sdf.format(new Date()); 
		String date[] = dateNowStr.split("-");
		//截取时间
		String year = date[0].substring(2, 4); 	//年份截取后两位
		String month = date[1];
		String day = date[2].substring(0, 2).trim();
		String hour = dateNowStr.split(" ")[1].trim();
		result = year+""+month+File.separator+day+File.separator+hour;
		return result.trim();
	}
	
	/**
	 * @说明: 返回时间路径：yyyy/MM/dd/hh,作为文件上传文件路径
	 * @return
	 * @创建人：黄小虎
	 * @创建时间:2016年12月1日 上午10:02:11
	 */
	public static String getTimePath(){
		String result = "";
		//获取当前时间的年月日和小时
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH"); 
		String dateNowStr = sdf.format(new Date()); 
		String date[] = dateNowStr.split("-");
		//截取时间
		String year = date[0]; 	//年份截取后两位
		String month = date[1];
		String day = date[2].substring(0, 2).trim();
		String hour = dateNowStr.split(" ")[1].trim();
		result = year+File.separator+month+File.separator+day+File.separator+hour;
		return result.trim();
	}
	
	private static final long ONE_MINUTE = 60000L;  
    private static final long ONE_HOUR = 3600000L;  
    private static final long ONE_DAY = 86400000L;  
  
    private static final String ONE_SECOND_AGO = "秒前";  
    private static final String ONE_MINUTE_AGO = "分钟前";  
    private static final String ONE_HOUR_AGO = "小时前";  
    private static final String ONE_DAY_AGO = "天前";  
    
	/**
	 * @说明: 获取当前时间 与 传入时间的时间差 .时间差小于1小时的显示 几分钟前；小于24小时 大于 1小时 显示几小时前，大于24小时显示日期
	 * @param time
	 * @return
	 * @创建人：黄小虎
	 * @创建时间:2016年9月8日 下午2:39:02
	 */
	public static String getTimeDifference(Timestamp time){
		long delta = new Date().getTime()-time.getTime();
        if (delta < 1L * ONE_MINUTE) {  
            long seconds = toSeconds(delta);  
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;  
        }  
        if (delta < 45L * ONE_MINUTE) {  
            long minutes = toMinutes(delta);  
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;  
        }  
        if (delta < 24L * ONE_HOUR) {  
            long hours = toHours(delta);  
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;  
        }  
        if (delta < 30L * ONE_DAY) {  
            long days = toDays(delta);  
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;  
        }  else {  
        	return fmt.format(time);
        }  
	}
	
	private static long toSeconds(long date) {  
        return date / 1000L;  
    }  
	
    private static long toMinutes(long date) {  
        return toSeconds(date) / 60L;  
    }  
  
    private static long toHours(long date) {  
        return toMinutes(date) / 60L;  
    }  
  
    private static long toDays(long date) {  
        return toHours(date) / 24L;  
    }   
	 
	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		   Date date = null;
		   try {
		    date = format.parse("2016-10-08");
		   } catch (ParseException e) {
		    e.printStackTrace();
		   }
		
		String s = getDayByType(toStringDate(date), 1, 7);
		System.err.println(daysBetween( new Date(),format.parse(s)));
		
	}
}
