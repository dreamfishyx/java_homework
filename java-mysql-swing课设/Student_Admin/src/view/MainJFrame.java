package view;

import javax.swing.*;
import java.awt.*;

/**
 * @Description: 运行主窗口
 * @Author: dreamfish
 * @Date: 2022/12/13 15:25
 * @Version: 1.0
 **/
public class MainJFrame extends JFrame{
    public MainJFrame(String title) throws HeadlessException {
        super(title);
        this.setResizable(false);//设置不可随意伸缩
        this.setVisible(true);  //设置窗口可见
        this.setBounds(400,100, 500, 500);
        /* 定义组件的位置：
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        init();
    }
    public void init(){
        add(new North(),BorderLayout.NORTH);
        add(new Middle(),BorderLayout.CENTER);
        add(new South(),BorderLayout.SOUTH);
        setVisible(true);
    }
}
