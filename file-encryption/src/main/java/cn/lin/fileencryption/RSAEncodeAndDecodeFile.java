package cn.lin.fileencryption;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public  class RSAEncodeAndDecodeFile {
    private static final String publicKey="MHwwDQYJKoZIhvcNAQEBBQADawAwaAJhAJ3u1bKMJQaDXuHnxqidQELngXVVTc4PyIzohK1j6I8UpnqaDP/6DZrmLUu+s4VjQ9eCYi8ZGAETmG+yT4upFynuGXbxwgo7EitPEaQSXY62SQGSSlftIhViqguEmnZM4wIDAQAB";
    private static final String privateKey="MIIB5QIBADANBgkqhkiG9w0BAQEFAASCAc8wggHLAgEAAmEAne7VsowlBoNe4efGqJ1AQueBdVVNzg/IjOiErWPojxSmepoM//oNmuYtS76zhWND14JiLxkYAROYb7JPi6kXKe4ZdvHCCjsSK08RpBJdjrZJAZJKV+0iFWKqC4SadkzjAgMBAAECYEtIdgVOWLfqF2iIG2J00xURVdygdR4s1+STUet1HH5X0aPPmzLJ94JHBoB8vwZuckCe6g7PNFmaBGMTFLXSBhPFSxXrm4M8meUTTVF/P4vnEyqBTDPONxkfgLCJT/C8QQIxAM4c5dVRr2uQZZ+46l1PcgnPURUeoSmsN898YkvTQvlVZplyFFZmpxIzppZqskTJ3wIxAMQooH3CZnrHVCOhF30YL+17jiQldMyxfZTyMr9Dv/U76vtUiTi0WLtHc4899Z2lfQIxAKuH1ypRocjF0h072ievrztEwrjt1bgFsCjH3lI2Tj2Meidnjk9dfNskCxaRUyz2RwIxAJ6P+KuvqQc2eV0TqtkD5Doj1hKB9JhCITF1VnAlY9XoSVpAS2v63H8GkvMHMrPsQQIwVumShXwBELMyQ1l/CVv+gPfIofOtiHwGxaWrwuRT+Huy+1DNXdDA4rZPWElvFjnz";

    public static void main(String[] args) throws Exception {

   //getEncodeFileInputStream();
    //  getDecodeInputStream();

    }

    /**
     * @description:加密文件
     * @param
     * @param
     * @return
     * @throws Exception
     */
    public static synchronized  FileInputStream  getEncodeFileInputStream(String path) throws Exception {
        FileInputStream fis= null;
        FileOutputStream fos=null;
        try {
            File file= new File(path);
            String ext = path.substring(path.lastIndexOf("."));
            String destFile =path.substring(0,path.lastIndexOf("\\"));
            File file1 =new File(destFile+"\\密文"+ext);
            if (!file1.exists()) {
                file1.createNewFile();
            }
            fos= FileUtils.openOutputStream(file1);
            fis = FileUtils.openInputStream(file);

            byte[] bytes = new byte[1024];
            byte[] buf= new byte[1024*1024*10];//最大1000万个字节
            int byteread=0;
            int index=0;


            while ((byteread=fis.read(bytes))!=-1){

                for (int i = 0; i <byteread ; i++) {
                   buf[i+index]=bytes[i];
                }
                index+=byteread;
              //  byte[] bytes1 = encode.getBytes();
               // fos.write(bytes, 0, byteread);
                //fos.flush();
            }

           // String encode = Base64Utils.encode(buf);
            byte[] encodedData = RSAUtils.encryptByPublicKey(buf,publicKey);
           // byte[] encodeBytes = encode.getBytes();
            String encode = Base64Utils.encode(encodedData);
            byte[] encodeBytes = encode.getBytes();
            int length = encodeBytes.length;
            for (int i = 0; i < length; i++) {
                 fos.write(encodeBytes[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.close();
            fis.close();
        }
        return fis;
    }
    /**
     * @description:解密文档
     * @param
     * @author:wxl
     * @date: 2017/10/27 10:28
     */
    public static synchronized FileInputStream getDecodeInputStream(String path) throws Exception{
        FileInputStream fis= null;
        FileOutputStream fos=null;
        try {
            File file= new File(path);
           String ext =path.substring(path.lastIndexOf("."));
           String destFile =path.substring(0,path.lastIndexOf("\\"));
            File file1 =new File(destFile+"\\明文"+ext);
            if (!file1.exists()) {
                file1.createNewFile();
            }
            fos= FileUtils.openOutputStream(file1);
            fis = FileUtils.openInputStream(file);

            byte[] bytes = new byte[1024];
            int byteread=0;
            byte [] buf= new byte[1024*1024*102];
            int index=0;
            while ((byteread=fis.read(bytes))!=-1){
                for (int i = 0; i <byteread ; i++) {
                    buf[i+index]=bytes[i];
                }
                index+=byteread;
              /*  byte[] decode = Base64Utils.decode(new String(bytes));
                System.out.println(new String(decode,"utf-8"));

                fos.write(decode, 0, byteread);
                fos.flush();*/
            }
            //String string=new String(buf);
            //byte[] decode = Base64Utils.decode(string);
            //======加密=====
            byte[] decode1 = Base64Utils.decode(new String(buf));
            byte[] decode= RSAUtils.decryptByPrivateKey(decode1,privateKey);
            //==============
            int length = decode.length;
            for (int i = 0; i < length; i++) {
                fos.write(decode[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.close();
            fis.close();
        }
        return fis;
    }
}
