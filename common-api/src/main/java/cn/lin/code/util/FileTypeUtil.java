package cn.lin.code.util;

import cn.getgrid.common.api.configuration.FileTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileTypeUtil {

	/**
	 * @说明：获取已知文件类型
	 * @author 张春雷
	 * @param src
	 * @return
	 * @return String
	 * @2017年2月15日 下午3:54:32
	 */
	public static String getFileType(byte[] src) {

		String fileType = null;
		String filetypeHex = String.valueOf(bytesToHexString(src));

		for (FileTypeEnum ft : FileTypeEnum.values()) {
			if (filetypeHex.toUpperCase().startsWith(ft.getMessage())) {
				fileType = ft.gettype();
				break;
			}
		}
		return fileType;
	}

	/**
	 * @说明：获取文件头部字符串
	 * @author 张春雷
	 * @param src
	 * @return
	 * @return String
	 * @2017年2月15日 下午3:47:05
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * @说明：指定格式判断：图片类型
	 * @author 张春雷
	 * @param fileType
	 *            图片类型
	 * @return
	 * @return boolean
	 * @2017年2月15日 下午5:41:14
	 */
	public static boolean pictureTypeCheck(String fileType) {
		if (fileType.equals("jpg")|| fileType.equals("jpeg") || fileType.equals("png") || fileType.equals("bmp")) {
			return true;
		}
		return false;
	}

	public static boolean pictureCheck(MultipartFile imgFile) {

		try {
			String extName = imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf(".") + 1);
			if (!pictureTypeCheck(extName.toLowerCase())) {
				return false;
			}

			InputStream is = imgFile.getInputStream();
			InputStream is2 = imgFile.getInputStream();
			byte[] b = new byte[16];
			is.read(b, 0, b.length);
			String fileType = getFileType(b);

			if (fileType == null) {
				return false;
			}

			if (!pictureTypeCheck(fileType)) {
				return false;
			}
			
			BufferedImage img = ImageIO.read(is2);
			//Image img = (Image) bi;
			if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
				return false;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean resizeSave(InputStream is, String filepath, double scale) {

		File fo = new File(filepath);

		if (!fo.exists() && !fo.isDirectory()) {
			fo.mkdirs();
		}

		try {
			BufferedImage img = ImageIO.read(is);
			int width = img.getWidth();
			int height = img.getHeight();

			int nw = (int) (width * scale);
			int nh = (int) (height * scale);

			BufferedImage resizeImage = new BufferedImage(nw, nh, BufferedImage.TYPE_INT_RGB);
			resizeImage.getGraphics().drawImage(img.getScaledInstance(nw, nh, Image.SCALE_SMOOTH), 0, 0, null);
			ImageIO.write(resizeImage, filepath.substring(filepath.lastIndexOf(".") + 1), fo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
