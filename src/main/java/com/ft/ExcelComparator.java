package com.ft;

import com.ft.util.DataProcessor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * @author mask
 * @date 2020/11/27 22:20
 * @desc
 */
public class ExcelComparator {
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

    public ExcelComparator() {
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textField1.setText(null);
                JFileChooser chooser = new JFileChooser();
                FileSystemView fsv = FileSystemView.getFileSystemView(); //注意了，这里重要的一句
                //System.out.println(fsv.getHomeDirectory()); //得到桌面路径
                chooser.setCurrentDirectory(fsv.getHomeDirectory());//初始界面为桌面

                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//用来选择文件
                FileNameExtensionFilter filter = new FileNameExtensionFilter(//设置选择的文件类型
                        "Excel & 2007", "xlsx");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(chooser);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
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
                textField2.setText(null);

                JFileChooser chooser = new JFileChooser();
                FileSystemView fsv = FileSystemView.getFileSystemView(); //注意了，这里重要的一句
                //System.out.println(fsv.getHomeDirectory()); //得到桌面路径
                chooser.setCurrentDirectory(fsv.getHomeDirectory());//初始界面为桌面

                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//用来选择文件
                FileNameExtensionFilter filter = new FileNameExtensionFilter(//设置选择的文件类型
                        "Excel & 2007", "xlsx");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(chooser);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
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
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    dataProcessor.setFinalFilePath(chooser.getSelectedFile().getPath());
                    textField3.setText(chooser.getSelectedFile().getPath());
                }
            }
        });

        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (dataProcessor.getTaiZhangFilePath() == null || dataProcessor.getTaiZhangFilePath().length() == 0) {
                    JOptionPane.showMessageDialog(null, "请选择台账文件", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (dataProcessor.getZongZhangFilePath() == null || dataProcessor.getZongZhangFilePath().length() == 0) {
                    JOptionPane.showMessageDialog(null, "请选择总账文件", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (textField3.getText() != null && textField3.getText().length() > 0) {
                    dataProcessor.setFinalFilePath(textField3.getText());
                }
                if (dataProcessor.getFinalFilePath() == null || dataProcessor.getFinalFilePath().length() == 0) {
                    JOptionPane.showMessageDialog(null, "请选择生成结果保存路径", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (dataProcessor.getTaiZhangFilePath().equals(dataProcessor.getZongZhangFilePath())) {
                    JOptionPane.showMessageDialog(null, "请勿选择相同文件", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                //处理并生成文件
                dataProcessor.dealDataAndWriteToExcel();
                //打开文件目录
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

        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Excel-Comparator");
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new ExcelComparator().form);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        form = new JPanel();
        form.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1 = new JLabel();
        panel1.setText("选择台账文件");
        form.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button1 = new JButton();
        button1.setText("选择台账文件");
        form.add(button1, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setEditable(false);
        form.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField2 = new JTextField();
        textField2.setEditable(false);
        form.add(textField2, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        button2 = new JButton();
        button2.setText("选择总账文件");
        form.add(button2, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel2 = new JLabel();
        panel2.setText("选择总账文件");
        form.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exit = new JButton();
        exit.setText("退出");
        form.add(exit, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        confirm = new JButton();
        confirm.setText("生成结果");
        form.add(confirm, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        textField3.setEditable(false);
        textField3.setText("D:\\result\\");
        form.add(textField3, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        button3 = new JButton();
        button3.setText("选择文件输出路径");
        form.add(button3, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel3 = new JLabel();
        panel3.setText("默认路径");
        form.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return form;
    }
}
