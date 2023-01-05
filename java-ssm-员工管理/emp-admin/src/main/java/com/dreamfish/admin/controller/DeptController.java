package com.dreamfish.admin.controller;

import com.dreamfish.admin.entity.Department;
import com.dreamfish.admin.service.DeptService;
import com.dreamfish.admin.util.MyPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @author: dreamfish
 * @Description: TODO
 * @date: Created in 2022/8/6-23:45
 * @version: v1.8
 **/
@Controller
public class DeptController {
    @Autowired
    private DeptService service;
    private MyPage page;
    //存储一些页面数据，方便增删改返回不同页数

    /**
    *@Description: 主要用于对修改、新增员工前的部门全部查询
    *@Return: java.lang.String
    **/
    @RequestMapping("/deptSearchAll")
    public String getAllDept(Model model){

        List<Department> allDept = service.getAllWithPart();
        model.addAttribute("allDept",allDept);
        return "empEdit";
    }

    /**
    *@Description: 删除部门。从搜索页面进行删除，就返回搜索页面对应位置。
     *             部门列表页面进行删除，就返回全部查询页面的相应页数。（包含一些页面判断）
    *@Return: java.lang.String
    **/
    @RequestMapping("/deptDel")
    public String delEmp(Integer deptId,RedirectAttributes attr){

        service.delDept(deptId);

        attr.addAttribute("deptName",page.getSearchName());
        if((page.getCount()-1)%MyPage.PAGE_SIZE==0&&page.getIsLastPage()&&page.getCount()!=1){
            attr.addAttribute("pageNum",page.getPageNow()-1);
        }else {
            attr.addAttribute("pageNum",page.getPageNow());
        }

        return "redirect:/getDept";
    }


    /**
    *@Description: 由于懒,就把全部部门查询和通过姓名模糊查询放到一起，通过name参数是否有值判断
     * name参数为空就是全部查询，否则就是按名称模糊查询
    *@Return: java.lang.String
    **/
    @RequestMapping(value = "/getDept",method = {RequestMethod.POST,RequestMethod.GET})
    public String getByName(@RequestParam(value = "deptName",defaultValue = "")String name,
                            @RequestParam(value = "pageNum",defaultValue ="1")Integer pageNum,
                            Model model){
        PageHelper.startPage(pageNum,MyPage.PAGE_SIZE);
        List<Department> list = service.getByName(name);
        PageInfo<Department> pageInfo = new PageInfo<>(list, 5);

        //将总页数等转存，便于删、改、增完成跳转相应页数
        page=new MyPage(pageInfo,name);

        model.addAttribute("deptPage",pageInfo);
        model.addAttribute("deptName",name);
        model.addAttribute("map",new HashMap<String,String>());

        return "deptList";
    }

    /**
    *@Description: 部门添加（添加前通过JSR303校验，不通过则存储相关错误信息到请求域，并返回添加页面）
    *@Return: java.lang.String
    **/
    @RequestMapping(value = "/deptAdd",method = RequestMethod.POST)
    public String addEmp(@Valid Department d,
                         BindingResult result,
                         Model model,
                         RedirectAttributes attr){

        //存储错误信息
        HashMap<String, String> error = new HashMap<>();
        for (FieldError e:result.getFieldErrors()){
            error.put(e.getField(),e.getDefaultMessage());
        }
        //不符合要求返回原页面，显示错误
        if(error.size()!=0){
            model.addAttribute("deptError",error);
            return "deptEdit";
        }

        service.addDept(d);

        //添加后返回末页查看
        attr.addAttribute("deptName",page.getSearchName());
        if((page.getCount())% MyPage.PAGE_SIZE ==0){
            attr.addAttribute("pageNum",page.getPages()+1);
        }else{
            attr.addAttribute("pageNum",page.getPages());
        }
        return "redirect:/getDept";
    }

    /**
    *@Description: 部门修改前对部门信息的查询，用于信息回显
    *@Return: java.lang.String
    **/
    @RequestMapping("/deptSearch")
    public String getEmpById(@RequestParam("deptId")Integer id,Model model){
        Department department = service.getById(id);
        model.addAttribute("deptUpdate",department);
        return "deptEdit";
    }

    /**
    *@Description: 临时处理器，由于懒，把新增和修改放在同一个页面同一份个表单。但是只能发送一种url
     * 所以通过临时处理器把两种请求（通过deptId有无）分开,分别转发给方法处理
    *@Return: java.lang.String
    **/
    @RequestMapping("/deptTemp")
    public String transit(@RequestParam(value = "deptId",required = false) Integer deptId){
        if (deptId==null){
            //新增
            return "forward:/deptAdd";
        }
        //修改
        return "forward:/deptUpdate";
    }

    /**
    *@Description: 修改方法，JSR303校验，但是由部分信息不允许修改或未修改，传回来参数为null或"",会被校验。
     * 在记录错误信息时要将它们排除在外，不作为判断标准。
    *@Return: java.lang.String
    **/

    @RequestMapping("/deptUpdate")
    public String deptUpdate(@Valid Department d,
                             BindingResult result,Model model,
                             RedirectAttributes attr
    ){
        //存储错误信息
        HashMap<String, String> error = new HashMap<>();
        for (FieldError fe:result.getFieldErrors()){
            if("establishTime".equals(fe.getField())&&!"".equals(d.getEstablishTime())){
                error.put(fe.getField(),fe.getDefaultMessage());
            }
        }

        if(error.size()!=0){
            model.addAttribute("deptError",error);

            return "forward:/deptSearch"+"?deptId="+d.getDeptId();
        }
        service.updateDept(d);
        //重定向手动拼接参数导致中文乱码的解决:
        attr.addAttribute("pageNum",page.getPageNow());
        attr.addAttribute("deptName",page.getSearchName());
        //返回修改数据所在页
        return "redirect:/getDept";
    }
}
