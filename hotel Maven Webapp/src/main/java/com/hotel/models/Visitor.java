package com.hotel.models;
/**
 * 过滤器访问拦截防爬虫的用户model
 * @author yuanhaohe
 *
 */
public class Visitor {
	//访问ip
private String ip;
//访问开始时间
private long beginVisitTime;
//最近一次访问时间
private long lastVisitTime;
//访问次数
private int times;
//是否拒绝访问
private boolean refuse;
//该ip拒绝的次数
private int refuseTimes;
public void setRefuseTimes(int refuseTimes){
	this.refuseTimes=refuseTimes;
}
public int getRefuseTimes(){
	return refuseTimes;
}

public void setRefuse(boolean refuse){
	this.refuse=refuse;
}
public boolean getRefuse(){
	return refuse;
}
public void setTimes(int times){
	this.times=times;
}
public int getTimes(){
return times;
}
public void setIp(String ip){
	this.ip=ip;
}
public String getIp(){
	return ip;
}
public long getBeginVisitTime(){
	return beginVisitTime;
}

public void setBeginVisitTime(long beginVisitTime){
	this.beginVisitTime=beginVisitTime;
}

public long getLastVisitTime(){
	return lastVisitTime;
}
public void setLastVisitTime(long lastVisitTime){
	this.lastVisitTime=lastVisitTime;
}
@Override
public String toString() {
	return "Visitor [ip=" + ip + ", beginVisitTime=" + beginVisitTime + ", lastVisitTime=" + lastVisitTime + ", times="+times+", refuse="+refuse+"， refuseTimes="+refuseTimes+"]";
}
}
