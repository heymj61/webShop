package com.kosta.controller4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitParamServlet
 */

//@WebServlet을 지우고 대신 web.xml의 설정으로 변경해보자
public class InitParamServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InitParamServlet3() {
		System.out.println("InitParamServlet3생성됨");
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tel = getInitParameter("tel");
		String address = getInitParameter("address");

		System.out.println(tel);
		System.out.println(address);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
