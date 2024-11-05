package com.diegodev.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//classe responsavel por decodificar parametro de url

public class URL {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8"); //decodificando uma url,uso o decode, passo o texto que quero decodificar, e o padrão de decodificação
			
		}catch(UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static Date convertDate(String textDate,  Date defaultValue) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		try {	
			return sdf.parse(textDate);
			
		}catch(ParseException e) {
			return defaultValue;
		}
		
	}
}
