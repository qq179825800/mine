package cn.lin.code.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 提现申请生成批次号 
 * 格式：YYYYMMDD+01
 * 批次号的规则：年、月、日、+2位数字
 * 缺点：服务重启会有问题，先这样用着吧
 * 改进：利用数据库存储或者Redis存储
 * @author niuwk
 * @version 1.0
 *
 */
public class BatchNumberGenerator {

	
	private static int number = 0;//末尾数字
	private static int step = 0;  //倒数第二位数字
	private static Map<String, Long> map = new HashMap<String, Long>();//存储某天流水的集合
	
	public static synchronized long generateId() {
		
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		if(map.get(date)==null){
			map.clear();
			number = 0;
			step = 0;
			map.put(date, Long.parseLong(date + step + number));
		}
		
		number++;
		if (number > 9) {
			step++;
			number = 0;
		}
		map.put(date, Long.parseLong(date + step + number));//或者直接取到当前的数字直接加1 比如：2017070400+1
		return  map.get(date);
	}
}
