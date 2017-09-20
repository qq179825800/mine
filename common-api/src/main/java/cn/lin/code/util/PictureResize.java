package cn.lin.code.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PictureResize {

	public static boolean resize(String fileUrl,String size, String mode){
		size="200X200;400X400";
		boolean flag =false;
		String[] sizec =size.split(";");
		for(String resize :sizec){
			Integer width = Integer.valueOf(resize.split("X")[0]);
			Integer height = Integer.valueOf(resize.split("X")[1]);
			flag = MakeThumbnailImage(fileUrl,fileUrl.substring(0,fileUrl.lastIndexOf("."))+"_"+resize+fileUrl.substring(fileUrl.lastIndexOf(".")),width,height,mode,null);
			if(!flag){
				return flag;
			}
		}
		return flag;
	}
	/**
	 * @说明：
	 * @author 张春雷
	 * @param fileUrl ：原图地址
	 * @param filepathNew ：压缩图目标地址
	 * @param width 要压缩的目标宽度
	 * @param height 要压缩的目标高度
	 * @param mode ：压缩模式 
	 * @param background ：背景有无
	 * @return
	 * @return boolean
	 * @2017年3月13日 下午3:28:00
	 */
	public static boolean MakeThumbnailImage(String fileUrl, String filepathNew,int width, int height, String mode,String background){
		 
		 File fi = new File(fileUrl);
		 File fo  = new File(filepathNew);
		 
		 if (!fo.exists() && !fo.isDirectory()) {
				fo.mkdirs();
			}
		 BufferedImage img;
		
		 int Imgwidth = width;
		 int Imgheight = height;

		 int towidth = width;
		 int toheight = height;
		 
		 int tox = 0;
		 int toy = 0;
	  
		 int ox = 0;
		 int oy = 0;
		try {
			 img = ImageIO.read(fi);
			 int ow = img.getWidth();
			 int oh = img.getHeight();
			 int nw = img.getWidth();
			 int nh = img.getHeight();
			 switch (mode.toUpperCase()) {
			
			 	//生成制定宽高的图片,原图等比压缩到指定宽高内，缺省部分填充白色
				 case "HW"://等高宽缩放（不变形）
					 if ((double)ow / (double)oh > (double)towidth / (double)toheight)
					 {
						 towidth = width;
						 
						 toheight = (int)((double)oh * (double)towidth / (double)ow);
						 toy = (height - toheight) / 2;

						 Imgwidth = width;
						 Imgheight = height;
						 
						 ow=towidth;
						 oh=toheight;
						 
						 nw=towidth;
						 nh=toheight;
						 
						 toheight = toheight+toy;
					 }
					 else 
					 {
						 toheight = height;
						 towidth = (int)((double)ow * (double)toheight / (double)oh);
						 tox = (width - towidth) / 2;

						 ow=towidth;
						 oh=toheight;
						 
						 nw=towidth;
						 nh=toheight;
						 
						 towidth = towidth+tox;
					 }
					 break;

					 //指定宽的压缩图，高度不限
				 case "W"://指定宽，高按比例		   
					 towidth = ow > width ? width : ow;
					 toheight = oh * towidth / oh;

					 Imgwidth = towidth;
					 Imgheight = toheight;
					 
					 ow=towidth;
					 oh=toheight;
					 
					 nw=towidth;
					 nh=toheight;
					 break;
					 
					 //指定高的压缩图，宽度不限
				 case "H"://指定高，宽按比例
					 toheight = oh > height ? height : oh;
					 towidth = ow * toheight / oh;

					 Imgwidth = towidth;
					 Imgheight = toheight;
					 
					 ow=towidth;
					 oh=toheight;
					 
					 nw=towidth;
					 nh=toheight;
					 break;
				 case "CUT"://指定高宽裁减（不变形）
					 if(ow>towidth){
						 ox = (ow - towidth) / 2;
						 ow = towidth+ox;
					 }
					 if(oh>toheight){
						 oy = (oh - toheight) / 2;
						 oh = toheight+oy;
					 }

					 Imgwidth = width;
					 Imgheight = height;
					 break;
				 default:
					 break;
			 }
			if (background == null) {
				BufferedImage resizeImage = new BufferedImage(nw, nh, BufferedImage.TYPE_INT_RGB);
				resizeImage.getGraphics().drawImage(img.getScaledInstance(nw, nh, Image.SCALE_SMOOTH), 0, 0, null);
				ImageIO.write(resizeImage, filepathNew.substring(filepathNew.lastIndexOf(".") + 1), fo);
			} else {
				BufferedImage resizeImage = new BufferedImage(Imgwidth, Imgheight, BufferedImage.TYPE_INT_RGB);
				resizeImage.getGraphics().drawImage(img.getScaledInstance(nw, nh, Image.SCALE_SMOOTH), tox, toy, towidth, toheight, ox, oy,
						ow, oh, Color.WHITE, null);
				ImageIO.write(resizeImage, filepathNew.substring(filepathNew.lastIndexOf(".") + 1), fo);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(fileUrl);
			return false;
		}
		
		//String ret = Imgwidth + "," + Imgheight;
	   return true;
	   
	 }
}
