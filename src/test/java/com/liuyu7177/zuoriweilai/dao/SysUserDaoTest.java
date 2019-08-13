package com.liuyu7177.zuoriweilai.dao;

import com.liuyu7177.zuoriweilai.model.entitys.SysPrivilege;
import com.liuyu7177.zuoriweilai.model.entitys.SysRole;
import com.liuyu7177.zuoriweilai.model.entitys.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyu7177 On 2019/8/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-redis.xml", "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SysUserDaoTest {
    @Autowired
    private SysUserDao userDao;

    @Test
    public void TestRedis() {
        SysUser addUser = new SysUser();
        addUser.setUserName("liuyu7177");
        addUser.setUserPassword("123456");
        addUser.setUserEmail("liuyu7177@live.com");
        addUser.setUserInfo("test liuyu7177");
        addUser.setHeadImg(new byte[]{1, 2, 3});
        addUser.setCreateTime(new Date());
        userDao.insert(addUser);
        SysUser u = userDao.getById(addUser.getId());
        System.out.println(u);
        u.setUserName(u.getUserName() + "_update");
        userDao.updateByPrimaryKey(u);
        SysUser updateUser = userDao.getById(u.getId());
        System.out.println("删除id:" + updateUser.getId() + "影响行数：" + userDao.deleteByPrimaryKey(u.getId()));
    }

    @Test
    public void TestWhereIf() {
        List<SysUser> userList = userDao.getUserByWhereIf("liuyu");
        for (SysUser u:userList){
            System.out.println(u);
        }
    }
    @Test
    public void TestChoose() {
        SysUser searchUser = new SysUser();
        searchUser.setUserName("7177");
        List<SysUser> userList = userDao.getUserByChoose(searchUser);
        for (SysUser u:userList){
            System.out.println(u);
        }
    }

    @Test
    public void TestWhere() {
        List<SysUser> userList = userDao.getUserByWhere("liuyu");
        for (SysUser u:userList){
            System.out.println(u);
        }
    }

    @Test
    public void TestupdateBySet() {
        SysUser addUser = new SysUser();
        addUser.setUserName("liuyu7177");
        addUser.setUserPassword("123456");
        addUser.setUserEmail("liuyu7177@live.com");
        addUser.setUserInfo("test liuyu7177");
        addUser.setHeadImg(new byte[]{1, 2, 3});
        addUser.setCreateTime(new Date());
        userDao.insert(addUser);

        addUser.setUserName(addUser.getUserName() + "_update");
        userDao.updateBySet(addUser);
    }


    @Test
    public void TestForeach() {
        List<Long> ids=new  ArrayList();
        //ids.add(1L);
        //ids.add(2L);
        List<SysUser> userList = userDao.selectByIdList(ids);
        for (SysUser u:userList){
            System.out.println(u);
        }
    }
    @Test
    public void TestGetUserAndRoleById() {
        // 一对一自动映射
        SysUser u = userDao.getUserAndRoleById(1L);
        System.out.println(u);
    }

    @Test
    public void TestGetAllUserAndRoleById() {
        //resultMap一对一映射
        SysUser u = userDao.getUserAndRoleResultMapById(1L);
        System.out.println(u);
    }
    @Test
    public void TestGetUserAndRoleResultAssociationMapById() {
        //resultMap一对一映射 通过Association
        SysUser u = userDao.getUserAndRoleResultAssociationMapById(1L);
        System.out.println(u);
    }
    @Test
    public void TestGetUserAndRoleResultAssociationMapSelectById() {
        //resultMap一对一映射 通过Association 嵌套查询
        SysUser u = userDao.getUserAndRoleResultAssociationMapSelectById(1L);
        System.out.println(u.getUserName());
        //System.out.println(u);
        System.out.println("用户名："+u.getRole().getRoleName());
    }


    @Test
    public void TestGetUserAndRoleListMapById() {
        //resultMap一对多映射 通过collection
        SysUser u = userDao.getUserAndRoleListMapById(2L);
        System.out.println(u.toStringA());
    }
    @Test
    public void TestGetUserRAandRoleListMapById() {
        //resultMap一对多嵌套查询 通过collection
        SysUser u = userDao.selectByPrimaryKey(2L);
        System.out.println(u.toStringA());
        for (SysRole r:u.getRoleList()){
            System.out.println(r.toStringA());
            for (SysPrivilege p:r.getPrivilegeList()){
                System.out.println(p.toString());
            }
        }

    }

    @Test
    public void TestSelectUserById() {
        //调用存储过程
        SysUser u = new SysUser();
        u.setId(1L);
        userDao.selectUserById(u);
        System.out.println(u);
    }

}