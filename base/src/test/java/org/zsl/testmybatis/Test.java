/*package org.zsl.testmybatis;

import java.util.Calendar;
import java.util.Date;

import com.landray.kmss.util.DateUtil;


public class Test {

	public static void main(String[] args) {
		new Test().caculateSeason(
				DateUtil.convertStringToDate("2014-02-12", DateUtil.PATTERN_DATE),
				DateUtil.convertStringToDate("2016-08-12", DateUtil.PATTERN_DATE));
		System.out.println("hello");
	}
	
	
		
	public Integer caculateSeason(Date fdStartDate,Date fdEndDate){
		
		if(fdStartDate==null||fdEndDate==null||fdEndDate.before(fdStartDate))
			return null;
		
		Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
       
        c1.setTime(fdStartDate);//��ʼʱ��
        c2.setTime(fdEndDate);//����ʱ��
        
        int startYear = c1.get(Calendar.YEAR);//��ʼ���
        int endYear = c2.get(Calendar.YEAR);//�������
        
        int startMonth = c1.get(Calendar.MONTH);//��ʼ�·�
        int endMonth = c2.get(Calendar.MONTH);//�����·�
		
		
		return null;
	}
}*/