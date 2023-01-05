package view;

import dao.JDBCUtils;
import dao.StudentDaoImpl;
import entity.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * @Description: 更新、修改窗口
 * @Author: dreamfish
 * @Date: 2022/12/13 19:43
 * @Version: 1.0
 **/
public class UpdateIFrame extends JFrame implements ActionListener {
    private static Student student=null;
    private JTextField []tf=new JTextField[5];
    private JPanel jp=new JPanel();
    private JLabel []jb=new JLabel[5];
    public UpdateIFrame(String title) throws HeadlessException {
        super(title);
        this.setResizable(false);//设置不可随意伸缩
        this.setVisible(true);  //设置窗口可见
        this.setBounds(400,100, 500, 400);
        /* 定义组件的位置：
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        init();
    }
    public void init(){
        jp.setLayout(new FlowLayout(FlowLayout.CENTER,120,20));
        String[] title={"学号","姓名","年龄","电话","籍贯"};
        for (int i = 0; i < 5; i++) {
            tf[i]=new JTextField(16);
            tf[i].setFont(new Font ( "宋体" ,Font.BOLD, 15));
            jb[i]=new JLabel(title[i]+":");
            jb[i].setFont(new Font ( "宋体" ,Font.BOLD, 15));
            JPanel temp = new JPanel();
            temp.add(jb[i]);
            temp.add(tf[i]);
            jp.add(temp);
        }
        JButton button = new JButton("确认");
        button.setFont(new Font ( "宋体" ,Font.BOLD, 15));
        button.addActionListener(this);
        jp.add(button);
        if("修改".equals(getTitle())){  //此时为修改
            tf[0].setText(student.getStuNumber());
            tf[1].setText(student.getName());
            tf[2].setText(student.getAge()+"");
            tf[3].setText(student.getPhoneNumber());
            tf[4].setText(student.getNativePlace());
        }
        add(jp);
        setVisible(true);
    }
    /**
     *@description: 设置输入框预处理信息
     *@param: dao.Student
     *@return: void
     **/
    public static void setStudent(Student student) {
        UpdateIFrame.student = student;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = this.getTitle(); //获取标题
        //获取修改数据
        String stuNum = tf[0].getText();
        String name = tf[1].getText();
        int age = Integer.parseInt(tf[2].getText());
        String phone = tf[3].getText();
        String nativePlace = tf[4].getText();
        //创建对象
        Student stu = new Student(stuNum, name, age, phone, nativePlace);
        //获取数据库连接
        Connection conn=null;
        try {
             conn = JDBCUtils.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        StudentDaoImpl impl = new StudentDaoImpl();
        //区分修改和新增
        if ("修改".equals(title)){//修改
            stu.setUid(student.getUid());//设置uid
//            System.out.println(stu);
                impl.updateInfo(conn,stu);//修改
                Middle.updateDefaultData();//更新表格信息
        }else{//新增
            impl.insert(conn,stu);//新增
            Middle.updateDefaultData();//更新表格信息

        }
        student=null;//清除信息
        dispose();//关闭当前窗口
    }
}
