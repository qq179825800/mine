package cn.lin.fileencryption;import javax.swing.*;import java.awt.*;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.io.File;import java.util.Date;public  class SystemOS extends JFrame implements ActionListener {    JPanel pnlMain;    JTextField txtfile;    JTextField txtfile1;    JButton btnSelect;    JButton btnSelect1;    JFileChooser fc = new JFileChooser();    public SystemOS() {        pnlMain=new JPanel();        this.getContentPane().add(pnlMain);        txtfile=new JTextField(10);        txtfile1=new JTextField(10);        btnSelect =new JButton("选择加密文件");        btnSelect1 =new JButton("选择解密文件");        btnSelect.addActionListener(this);        btnSelect1.addActionListener(this);        pnlMain.add(txtfile);        pnlMain.add(btnSelect);        pnlMain.add(txtfile1);        pnlMain.add(btnSelect1);        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        this.setBounds(750,350,300,300);    }    @Override    public void actionPerformed(ActionEvent e){        if(e.getSource()==btnSelect){        Dialog dialog = new Dialog(this);        dialog.setTitle("请输入秘钥");/*    这是尤为重要的。因为JFileChooser默认的是选择文件，而需要选目录。    故要将DIRECTORIES_ONLY装入模型另外，若选择文件，则无需此句*/            //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);            int intRetVal = fc.showOpenDialog(this);            if( intRetVal == JFileChooser.APPROVE_OPTION){                txtfile.setText(fc.getSelectedFile().getPath());            }            File selectedFile = fc.getSelectedFile();            //TODO            try {                Date date = new Date();                System.out.println("加密开始");                this.setTitle("加密中...");                DESEncodeAndDecodeFile.getEncodeFileInputStream(selectedFile.getAbsolutePath());               this.setTitle("加密成功 耗时"+(((new Date()).getTime())-date.getTime())+"毫秒");            }catch (Exception e1){                e1.printStackTrace();            }            System.out.println(selectedFile);        }  if(e.getSource()==btnSelect1){/*    这是尤为重要的。因为JFileChooser默认的是选择文件，而需要选目录。    故要将DIRECTORIES_ONLY装入模型另外，若选择文件，则无需此句*/            //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);            int intRetVal = fc.showOpenDialog(this);            if( intRetVal == JFileChooser.APPROVE_OPTION){                txtfile1.setText(fc.getSelectedFile().getPath());            }            File selectedFile1 = fc.getSelectedFile();            try {                System.out.println("解密开始");                Date date = new Date();                this.setTitle("解密中...");               DESEncodeAndDecodeFile.getDecodeInputStream(selectedFile1.getAbsolutePath());               this.setTitle("解密成功 耗时"+(((new Date()).getTime())-date.getTime())+"毫秒");            }catch (Exception e1){                e1.printStackTrace();            }            System.out.println(selectedFile1);        }    }    public static void main(String[] args){        JFrame f = new SystemOS();        f.setSize(300,300);        f.setVisible(true);      /*  System.out.println(2^3);        System.out.println(2^3^3);*/    }}