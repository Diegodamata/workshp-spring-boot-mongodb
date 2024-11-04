package com.diegodev.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

//classe responsavel por decodificar parametro de url

public class URL {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8"); //decodificando uma url,uso o decode, passo o texto que quero decodificar, e o padrão de decodificação
			
		}catch(UnsupportedEncodingException e) {
			return "";
		}
	}
}
