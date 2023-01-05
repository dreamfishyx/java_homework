package com.dreamfish.admin.controller;

import com.dreamfish.admin.entity.Employee;
import com.dreamfish.admin.service.EmpService;
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
 * @date: Created in 2022/8/6-16:18
 * @version: v1.8
 **/
@Controller
public class EmpController {

    @Autowired
    private EmpService service;
    private MyPage page;

    /**
     *@Description: 员工添加（添加前通过JSR303校验，不通过则存储相关错误信息到请求域，并返回添加页面）
     *@Return: java.lang.String
     **/
    @RequestMapping(value = "/empAdd")
    public String addEmp(@Valid Employee e,
                         BindingResult result,
                         Model model,
                         RedirectAttributes attr){
        //存储错误信息
        HashMap<String, String> error = new HashMap<>();
        for (FieldError fe:result.getFieldErrors()){
            error.put(fe.getField(),fe.getDefaultMessage());
        }

        if(error.size()!=0){
            model.addAttribute("empError",error);
            return "forward:/deptSearchAll";
        }

        service.addEmp(e);
        //添加后返回末页查看
        attr.addAttribute("empName",page.getSearchName());
        if((page.getCount())% MyPage.PAGE_SIZE ==0){
            attr.addAttribute("pageNum",page.getPages()+1);
        }else {
            attr.addAttribute("pageNum",page.getPages());
        }
        return "redirect:/getEmp";
    }

    /**
     *@Description: 删除员工。从搜索页面进行删除，就返回搜索页面对应位置。
     *             员工列表页面进行删除，就返回全部查询页面的相应页数。（包含一些页面判断）
     *@Return: java.lang.String
     **/
    @RequestMapping("/empDel")
    public String delEmp(Integer empId,RedirectAttributes attr){
        service.delEmp(empId);
        //删除后返回当前页页查看(最后一页的唯一一个记录删除则返回前一页)
        attr.addAttribute("empName",page.getSearchName());
        if((page.getCount()-1)%MyPage.PAGE_SIZE==0&&page.getIsLastPage()&&page.getCount()!=1){
            attr.addAttribute("pageNum",page.getPageNow()-1);
        }else{
            attr.addAttribute("pageNum",page.getPageNow());
        }
        return "redirect:/getEmp";
    }

    /**
     *@Description: 把全部员工查询和通过姓名模糊查询放到一起，通过name参数是否有值判断
     * name参数为空就是全部查询，否则就是按名称模糊查询
     *@Return: java.lang.String
     **/
    @RequestMapping(value = "/getEmp",method = {RequestMethod.POST,RequestMethod.GET})
    public String getByName(@RequestParam(value = "empName",defaultValue = "")String name,
                            @RequestParam(value = "pageNum",defaultValue ="1")Integer pageNum,
                            Model model){
        PageHelper.startPage(pageNum,MyPage.PAGE_SIZE);

        List<Employee>   list = service.getByName(name);


        PageInfo<Employee> pageInfo = new PageInfo<>(list, 5);

        //将总页数等转存，便于删、改、增完成跳转相应页数
        page=new MyPage(pageInfo,name);

        model.addAttribute("empPage",pageInfo);
        //将查询
        model.addAttribute("empName",name);

        return "empList";
    }
    /**
    *@Description: 员工修改前查询数据，用于回显
    *@Return: java.lang.String
    **/
    @RequestMapping("/empSearch")
    public String getEmpById(@RequestParam("empId")Integer id,Model model){
        Employee employee = service.getById(id);
        model.addAttribute("empUpdate",employee);
        return "forward:/deptSearchAll";
    }

    /**
     *@Description: 临时处理器，由于懒，把新增和修改放在同一个页面同一份个表单。但是只能发送一种url
     * 所以通过临时处理器把两种请求（通过empId有无）分开,分别转发给方法处理
     *@Return: java.lang.String
     **/
    @RequestMapping("/empTemp")
    public String transit(@RequestParam(value = "empId",required = false) Integer empId){
        if (empId==null){
            //新增
            return "forward:/empAdd";
        }
        //修改
        return "forward:/empUpdate";
    }

    /**
     *@Description: 修改方法，JSR303校验，但是由部分信息不允许修改或不修改，传回来参数为null或"",会被校验。
     * 要在记录错误信息时将它们排除在外，不作为判断标准。
     *@Return: java.lang.String
     **/
    @RequestMapping("/empUpdate")
    public String deptUpdate(@Valid Employee e,
                             BindingResult result, Model model,
                             RedirectAttributes attr){
        //存储错误信息
        HashMap<String, String> error = new HashMap<>();
        for (FieldError fe:result.getFieldErrors()){
            if(fe.getField().equals("email")&& !"".equals(e.getEmail())){
                error.put(fe.getField(),fe.getDefaultMessage());
            }
            if(fe.getField().equals("hireDate")&& !"".equals(e.getHireDate())){
                error.put(fe.getField(),fe.getDefaultMessage());
            }


        }

        if(error.size()!=0){
            model.addAttribute("empError",error);
            return "forward:/empSearch"+"?empId="+e.getEmpId();
        }
        service.updateEmp(e);

        //重定向手动拼接参数导致中文乱码的解决:
        attr.addAttribute("pageNum",page.getPageNow());
        attr.addAttribute("empName",page.getSearchName());
        return "redirect:/getEmp";
    }
}
