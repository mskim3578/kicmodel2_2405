package model;

import java.util.Date;

public class Comment {
int ser;
int num;
String content;
String id;
Date regdate;



public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getSer() {
	return ser;
}
public void setSer(int ser) {
	this.ser = ser;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getRegdate() {
	return regdate;
}
public void setRegdate(Date regdate) {
	this.regdate = regdate;
}



}
