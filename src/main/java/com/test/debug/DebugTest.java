package com.test.debug;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DebugTest {
    public static void main(String[] args) {
        SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
        Timestamp d2 = new Timestamp(d1.getTime());
        Obj obj = new Obj(d1, d2);
        System.out.println(obj.getD().getTime());
        System.out.println(sfm.format(obj.getD()));

        System.out.println(obj.getD1().getTime());
        System.out.println(obj.getD1());
    }
}


class Obj{
    private Date d;
    
    private Timestamp d1;

    public Obj(Date d, Timestamp d1){
        this.d = d;
        this.d1 = d1;
    }
    
    public Date getD()
    {
        return d;
    }

    public void setD(Date d)
    {
        this.d = d;
    }

    public Timestamp getD1()
    {
        return d1;
    }

    public void setD1(Timestamp d1)
    {
        this.d1 = d1;
    }
    
}
