package com.weihui.cashdesk.utils;

/**
 * Created by hanxiaodi on 18/12/4.
 */
public class walkflower {

	public  int walknum ;
	public  int walkmethod(int n){
		if(n==1){
			walknum =1;
		}
		else if(n==2){
			walknum =2;
		}
		else if(n==3){
			walknum =4;

		}else {
			walknum =walkmethod(n-1)+walkmethod( n-2 )+walkmethod( n-3 );
		}
		return walknum;
	}

	public static void main(String[] args){
		walkflower w = new walkflower();
		int  i = w.walkmethod( 4 );
		System.out.println(i);
	}
}
