package com.zrrd.mybatis.dao;

import com.zrrd.mybatis.pojo.Emp;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class EmpMapperImpl implements EmpMapper {

    private final SqlSession sqlSession;

    public EmpMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Emp> queryAll() throws Exception {
        /*
         * 通过当前这个类的父接口的全类名（namespace）
         * 通过当前对象（this）获取当前类的字节码对象，再通过当前类的字节码对象可以获取当前类实现的父接口、
         * 组成的数组 由于当前接口只有一个所有通过数组[0]就可以获取到当前接口在通过，来获取全类名（包名，接口名）
         */
        String interName = this.getClass().getInterfaces()[0].getName();
        /*
         * 获取当前方法名（=SQL标签id的值）
         * 获取存放的方法调用栈信息（所有运行时方法的调用信息） 返回一个数组
         * 会在栈顶（数组的第一个元素）调用这个方法queryAll() 这个方法在数组中的第二个位置（也就是数组的第二个元素）
         */
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        /*
         * 通过数组中第二个元素 获取当前调用的方法的名字（=SQL标签id的值）
         */
        String methodName = st[1].getMethodName();
        List<Emp> list = sqlSession.selectList(interName + "." + methodName);

        return list;
    }

    @Override
    public void insert() throws Exception {

        String interName = this.getClass().getInterfaces()[0].getName();

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        String methodName = stackTrace[1].getMethodName();

        sqlSession.insert(interName + "." + methodName);

    }

    @Override
    public void delete(String name) throws Exception {
        String interName = this.getClass().getInterfaces()[0].getName();

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        String methodName = stackTrace[1].getMethodName();

        sqlSession.delete(interName + "." + methodName, name);
    }

    @Override
    public void update(List<Integer> list) throws Exception {
        String interName = this.getClass().getInterfaces()[0].getName();

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        String methodName = stackTrace[1].getMethodName();

        sqlSession.update(interName + "." + methodName, list);
    }

    @Override
    public void insert2(Map<String, Object> map) throws Exception {

    }

    @Override
    public void update2(Emp emp) throws Exception {

    }


}
