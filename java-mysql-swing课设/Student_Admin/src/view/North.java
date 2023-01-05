package view;

import dao.JDBCUtils;
import dao.StudentDaoImpl;
import entity.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Description: 主窗口
 * @Author: dreamfish
 * @Date: 2022/12/13 17:34
 * @Version: 1.0
 **/
public class North extends JPanel implements ActionListener {
    JTextField text1;
    JButton bSearch,insert;
    public North() {
        init();
    }
    public void init(){
        text1 = new JTextField(10);

        text1.setText("输入学号");

        bSearch = new JButton("搜索");
        bSearch.addActionListener(this);

        insert = new JButton("新增");
        insert.addActionListener(this);
        this.add(text1);
        this.add(bSearch);
        this.add(insert);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bSearch){
            String text = text1.getText();
            if (text==null||"".equals(text)||"输入学号".equals(text)){
                Middle.updateDefaultData();//为空查询全部
            }else {

                try {
                    Connection conn = JDBCUtils.getConnection();
                    StudentDaoImpl impl = new StudentDaoImpl();
                    //查询对应学号学生
                    Student stu = impl.getStudentByNum(conn, text);
//                System.out.println(stu);
                    ArrayList<Student> list = new ArrayList<>();
                    list.add(stu);
                    Middle.update(list);//更新表格
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }else{
            new UpdateIFrame("新增");
        }


    }
}
