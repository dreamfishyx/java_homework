package view;

import dao.JDBCUtils;
import dao.StudentDaoImpl;
import entity.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * @Description: 瞎编修改、删除按钮
 * @Author: dreamfish
 * @Date: 2022/12/13 18:54
 * @Version: 1.0
 **/
public class South extends JPanel implements ActionListener {
    private JButton b1;
    private JButton b2;


    public South() {
        init();
    }
    public void init(){
         b1 = new JButton("修改");
        b1.addActionListener(this);
         b2 = new JButton("删除");
        b2.addActionListener(this);
        add(b1);
        add(b2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = Middle.getTable();
        int column = table.getSelectedRow();


        if(column>-1){//存在选中行
           int i=(Integer) Middle.getUid().get(column);//获得选中学生的uid。
            if(e.getSource()==b1){
                try {
                    Connection conn = JDBCUtils.getConnection();
                    StudentDaoImpl impl = new StudentDaoImpl();
                    Student byId = impl.getById(conn, i);
                    System.out.println(byId);
                    UpdateIFrame.setStudent(byId);
                    new UpdateIFrame("修改");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                try {
                    Connection conn = JDBCUtils.getConnection();
                    StudentDaoImpl impl = new StudentDaoImpl();
                    impl.delete(conn,i);
                    Middle.updateDefaultData();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
