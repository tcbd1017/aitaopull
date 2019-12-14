package cn.kgc.tangcco.tcbd1017.lo.commons.suiji;
 
import java.util.Random;

public class SuiJi {

	public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
	public static String getRandomNumber(int length){
	     String str="0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(10);
	       if(i==0&&number==0) {
	    	   i--;
	    	   continue;
	       }
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
	
}
