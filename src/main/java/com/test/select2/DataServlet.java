package com.test.select2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DataServlet
 */
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int page = 1;
	    if(null!=request.getParameter("page")&&!"".equals(request.getParameter("page"))){
	        page = Integer.parseInt(request.getParameter("page"));
	    }
	    
	    String search = request.getParameter("search");
	    
	    response.getWriter().write(page(page,search).toString());
	}

	private JSONObject page(int page, String search){
	    List<String> list = search(search);
	    
	    int start = (page-1)*10;
	    int end = start+10;
	    end = list.size()>end?end:list.size();
	    
	    JSONArray arr = new JSONArray();
	    if(list.size()>0){
    	    for (int i = start; i < end; i++)
            {
    	        JSONObject json = new JSONObject();
    	        String text = list.get(i);
    	        json.put("id", i);
    	        json.put("text", text);
    	        arr.add(json);
            }
    	    
	    }
	    
        JSONObject rst = new JSONObject();
        rst.put("results", arr);
	    
	    JSONObject pagination = new JSONObject();
	    pagination.put("more", list.size()>end?true:false);
	    
	    rst.put("pagination", pagination);
	    
	    return rst;
	}
	
	private List<String> search(String search){
	    if(null==search||"".equals(search)){
	        return strs;
	    }
	    
	    List<String> list = new ArrayList<String>();
	    for (String string : strs)
        {
            if(string.indexOf(search)==0){
                list.add(string);
            }
        }
	    return list;
	}
	
	private static List<String> strs;
	static
    {
	    FileInputStream in;
        try
        {
            in = new FileInputStream("E:/workspace/Test/WebContent/jqueryPlugin/select2/test/js/countries.json");
    	    byte[] bs = new byte[in.available()];
    	    in.read(bs);
    	    in.close();
    	    
    	    String jsonStr = new String(bs);
    	    JSONObject json = JSONObject.fromObject(jsonStr);
    	    
    	    strs = new ArrayList<String>();
    	    Iterator<String> iterator = json.keySet().iterator();
    	    while(iterator.hasNext()){
    	        String key = iterator.next();
    	        strs.add(json.getString(key));
    	    }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
	
}
