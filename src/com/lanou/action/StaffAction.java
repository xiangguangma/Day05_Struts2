package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *  分支测试 哈哈哈
 * Created by dllo on 17/10/16.
 */
public class StaffAction extends ActionSupport {

    private List<Department> departList;  // 存储所有部门的集合
    private List<Post> postList;

    {
        /*
            初始化部门集合数据
         */
        departList = new ArrayList<>();

        /* 添加部门 */
        for (int i = 0; i < 3; i++) {
            Department department = new Department();
            department.setId(i); // 部门 id
            department.setDname("部门" + i); // 部门名字

            List<Post> postList = new ArrayList<>(); // 存储当前部门下的所有职务集合
            for (int j = 0; j < 5; j++) {
                Post post = new Post();
                post.setId(j); // 职务id
                post.setPname(i + "职务" + j);  // 职务名称

                postList.add(post); // 将职务加到职务集合中
            }

            department.setPostList(postList); // 将职务列表设置到部门对象中

            departList.add(department); //将部门对象加到部门集合中
        }
    }


    private String departId; // 发起部门id 查询是传递过来的部门id


    /**
     * 查找所有不部门
     *
     * @return
     */
    public String findDepartment() {
        return SUCCESS;
    }


    /**
     * 根据部门 id 查找职务集合
     *
     * @return
     */
    public String findPostByPid() throws IOException {
        System.out.println("要查询的部门id : " + departId);

        int dId = Integer.parseInt(departId);

        // 根据部门 id 获取该部门下的所有职务集合
        List<Post> postList = departList.get(dId).getPostList();

        // 封装 json 数据
        JsonConfig jsonConfig = new JsonConfig();

        String jsonArray = JSONArray.fromObject(postList, jsonConfig).toString();

        // 将json数据作为响应内容进行返回
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(jsonArray);

        return null; // 什么都不做
    }

    /**
     *  根据struts2 返回json数据
     * @return
     */
    public String findPostListByPid(){
        // 获得请求的部门id
        int dId = Integer.parseInt(departId);

        // 根据部门id 查找部门集合
        postList = departList.get(dId).getPostList();



        return SUCCESS;
    }


    public List<Department> getDepartList() {
        return departList;
    }

    public void setDepartList(List<Department> departList) {
        this.departList = departList;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
