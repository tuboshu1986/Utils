package com.hb.utils.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;

/**
 * BeanUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 30, 2019</pre>
 */
public class BeanUtilsTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: beanName(Object obj)
     */
    @Test
    public void testBeanNameObj() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: beanName(Class<T> clazz)
     */
    @Test
    public void testBeanNameClazz() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getClasses(Class<? extends Object> clazz, Class<? extends Object> lastSuperClazz)
     */
    @Test
    public void testGetClasses() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getFieldAnnotation(Field f, Class<? extends T> annotationClazz)
     */
    @Test
    public void testGetFieldAnnotation() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: fieldStringValue(Object obj, String fieldName)
     */
    @Test
    public void testFieldStringValue() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: stringValue(Object obj, Field f)
     */
    @Test
    public void testStringValue() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setValue(Object obj, Field f, String val)
     */
    @Test
    public void testSetValue() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getFields(Class<? extends Object> clazz)
     */
    @Test
    public void testGetFields() throws Exception {
        BeanUtils.getFields(SubBean.class).forEach(item -> {
            System.out.printf(item.getName());
        });
    }

    /**
     * Method: getField(Class<? extends Object> clazz, String name)
     */
    @Test
    public void testGetField() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: logString(Object obj)
     */
    @Test
    public void testLogString() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: copyFieldIfNotNull(Object from, Object to)
     */
    @Test
    public void testCopyFieldIfNotNull() throws Exception {
        SubBean obj = new SubBean(null, null, null, 18);
        SubBean obj1 = new SubBean("1", "xm", null, 12);
        BeanUtils.copyFieldIfNotNull(obj1, obj);
        Assert.assertEquals(obj1.getId(), obj.getId());
        Assert.assertEquals(obj1.getUserName(), obj.getUserName());
        Assert.assertEquals(obj1.getAge(), obj.getAge());
    }

    @Test
    public void testFieldValue(){
        SubBean obj1 = new SubBean("1", "xm", null, 12);
        Object val = BeanUtils.fieldValue(obj1, "userName");
        Assert.assertEquals("xm", val);
        Assert.assertEquals(12, BeanUtils.fieldValue(obj1, "age"));
    }

}

class BaseBean{
    private String id;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class SubBean extends BaseBean{
    private String name;
    private Integer age;

    public SubBean(String id, String userName, String name, Integer age) {
        this.setId(id);
        this.setUserName(userName);
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
