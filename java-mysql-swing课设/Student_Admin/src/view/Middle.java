package view;

import dao.JDBCUtils;
import dao.StudentDaoImpl;
import entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 通过jtable学生数据
 * @Author: dreamfish
 * @Date: 2022/12/13 17:59
 * @Version: 1.0
 **/
public class Middle extends JPanel {
    private static Object[][] objs=null;
    private static JTable table=null;
    private static List<Integer> uid=new ArrayList<>();//存储对应行数据对应学生uid
    public Middle() {
        updateDefaultData();
        init();
    }
    public void init(){
        String[] title={"学号","姓名","年龄","电话号码","籍贯"};
        table = new JTable(objs, title);
        table.getTableHeader().setReorderingAllowed(false);//设置顺序不可修改
        table.getTableHeader().setResizingAllowed(false);//不可变宽
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//只能选一行
        add(new JScrollPane(table));
    }

    public static void updateDefaultData(){
        List<Student> all=null;
        try {
            Connection conn = JDBCUtils.getConnection();
            StudentDaoImpl impl = new StudentDaoImpl();
             all = impl.getAll(conn);
//            System.out.println(all);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(all!=null){
            objs= new Object[all.size()][5];

            for (int i = 0; i < all.size(); i++) {
                objs[i][0]=all.get(i).getStuNumber();
                objs[i][1]=all.get(i).getName();
                objs[i][2]=all.get(i).getAge();
                objs[i][3]=all.get(i).getPhoneNumber();
                objs[i][4]=all.get(i).getNativePlace();
                uid.add(all.get(i).getUid());//存储uid
            }

        }
        if(table!=null){    //table为空，则不更新数据
            String[] title={"学号","姓名","年龄","电话号码","籍贯"};
            DefaultTableModel model = new DefaultTableModel(objs, title);
            table.setModel(model);
        }
    }
    /**
     *@description: 用于查询后更新显示数据
     *@param:
     *@return: void
     **/
    public static void update(List<Student> all){
        uid=new ArrayList<>();
        if(all!=null){
            objs= new Object[all.size()][5];

            for (int i = 0; i < all.size(); i++) {
                if(all.get(i)!=null){
                    objs[i][0]=all.get(i).getStuNumber();
                    objs[i][1]=all.get(i).getName();
                    objs[i][2]=all.get(i).getAge();
                    objs[i][3]=all.get(i).getPhoneNumber();
                    objs[i][4]=all.get(i).getNativePlace();
                    uid.add(all.get(i).getUid());//存储uid
                }

            }
            String[] title={"学号","姓名","年龄","电话号码","籍贯"};
            DefaultTableModel model = new DefaultTableModel(objs, title);
            table.setModel(model);
        }
    }
    public static JTable getTable(){
        return table;
    }
    public static List<Integer> getUid(){
        return uid;
    }
}
