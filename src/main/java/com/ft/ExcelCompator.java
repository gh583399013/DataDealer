package com.ft;

import com.ft.util.DataProcessor;
import com.sun.org.apache.regexp.internal.RE;
import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * @author mask
 * @date 2020/11/27 22:20
 * @desc
 */
public class ExcelCompator {
    private DataProcessor dataProcessor = new DataProcessor();

    private JPanel form;

    private JLabel panel1;
    private JTextField textField1;
    private JButton button1;

    private JLabel panel2;
    private JTextField textField2;
    private JButton button2;

    private JLabel panel3;
    private JTextField textField3;
    private JButton button3;

    private JButton confirm;
    private JButton exit;

    public ExcelCompator() {
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser chooser = new JFileChooser();
                FileSystemView fsv = FileSystemView.getFileSystemView(); //注意了，这里重要的一句
                //System.out.println(fsv.getHomeDirectory()); //得到桌面路径
                chooser.setCurrentDirectory(fsv.getHomeDirectory());//初始界面为桌面

                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//用来选择文件
                FileNameExtensionFilter filter = new FileNameExtensionFilter(//设置选择的文件类型
                        "Excel", "xlsx");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(chooser);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    String taiZhangFilePath = chooser.getSelectedFile().getPath();
                    dataProcessor.setTaiZhangFilePath(taiZhangFilePath);
                    textField1.setText(chooser.getSelectedFile().getName());
                }
            }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser chooser = new JFileChooser();
                FileSystemView fsv = FileSystemView.getFileSystemView(); //注意了，这里重要的一句
                //System.out.println(fsv.getHomeDirectory()); //得到桌面路径
                chooser.setCurrentDirectory(fsv.getHomeDirectory());//初始界面为桌面

                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//用来选择文件
                FileNameExtensionFilter filter = new FileNameExtensionFilter(//设置选择的文件类型
                        "Excel", "xlsx");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(chooser);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    String zongZhangFilePath = chooser.getSelectedFile().getPath();
                    dataProcessor.setZongZhangFilePath(zongZhangFilePath);
                    textField2.setText(chooser.getSelectedFile().getName());
                }
            }
        });

        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser chooser = new JFileChooser();
                FileSystemView fsv = FileSystemView.getFileSystemView(); //注意了，这里重要的一句
                //System.out.println(fsv.getHomeDirectory()); //得到桌面路径
                chooser.setCurrentDirectory(fsv.getHomeDirectory());//初始界面为桌面

                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//用来选择文件
                int returnVal = chooser.showOpenDialog(chooser);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    dataProcessor.setFinalFilePath(chooser.getSelectedFile().getPath());
                    textField3.setText(chooser.getSelectedFile().getPath());
                }
            }
        });

        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(dataProcessor.getTaiZhangFilePath() == null || dataProcessor.getTaiZhangFilePath().length() == 0){
                    JOptionPane.showMessageDialog(null, "请选择台账文件", "警告",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(dataProcessor.getZongZhangFilePath() == null || dataProcessor.getZongZhangFilePath().length() == 0){
                    JOptionPane.showMessageDialog(null, "请选择总账文件", "警告",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(textField3.getText() != null && textField3.getText().length() > 0){
                    dataProcessor.setFinalFilePath(textField3.getText());
                }
                if(dataProcessor.getFinalFilePath() == null || dataProcessor.getFinalFilePath().length() == 0){
                    JOptionPane.showMessageDialog(null, "请选择生成结果保存路径", "警告",JOptionPane.WARNING_MESSAGE);
                    return;
                }

                dataProcessor.dealDataAndWriteToExcel();
                String[] cmd = new String[5];
                String url = dataProcessor.getFinalFilePath();
                cmd[0] = "cmd";
                cmd[1] = "/c";
                cmd[2] = "start";
                cmd[3] = " ";
                cmd[4] = url;
                try {
                    Runtime.getRuntime().exec(cmd);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ExcelCompator");
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new ExcelCompator().form);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
