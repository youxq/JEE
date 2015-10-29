package com.you.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 主要是JSON交互的测试类
 * @author YOU
 *
 */
public class JsonServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public JsonServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 接收从前台传过来的数据，并转化为JSON数组（因为本来就是数组）
		String data = request.getParameter("data");
		JSONArray jsonArray = JSONArray.fromObject(data);
		// 遍历JSON数组
		for (int i = 0; i < jsonArray.size(); i++) {
			// 因为数组里是JSON对象，所以才用getJSONObject方法，还有其他的getString，getInt方法
			JSONObject json = jsonArray.getJSONObject(i);
			System.out.println(json.getString("userName"));
			System.out.println(json.getString("password"));
		}
		// 下面是将数据传回前端，创建map数据。里面分别放一个String和一个List
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add(new String(i + ""));
		}
		map.put("data", "success");
		map.put("list", list);
		// 传回前台的方法，这边要注意的是如果传回的是list或者数组
		// 那么下面的方法就改成JSONArray.fromObject
		JSONObject json = JSONObject.fromObject(map);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
