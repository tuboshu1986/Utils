package com.test.web.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LongPollServlet
 */
public class LongPollServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LongPollServlet()
    {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Random random = new Random();
        PrintWriter out = response.getWriter();
        long start = System.currentTimeMillis();

        while (true)
        {
            try
            {
                Thread.sleep(3000);
                int randomNum = random.nextInt(100);
                System.out.println(randomNum);
                if (randomNum > 10 && randomNum < 50)
                {
                    out.write("result:" + randomNum + ";timed:" + (System.currentTimeMillis() - start));
                    break;
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e1)
                {
                    e1.printStackTrace();
                }
            }
            System.out.println(">>>>一次请求");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException
    {
    }

    public static void main(String[] args)
    {
        Random random = new Random();
        System.out.println(random.nextInt(100));
    }
    
}
