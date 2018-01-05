package org.spring.cloud.img.server.controller;

import org.spring.cloud.img.server.conf.SaveImgToServerConfig;
import org.spring.cloud.img.server.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * 单张图片上传控制层
 * 
 * @author Administrator 2017年12月21日
 */
@RestController
public class ImgUploadController {

	@Autowired
	private SaveImgToServerConfig saveImgToServerConfig;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		System.out.println(saveImgToServerConfig.getVistaPath()+"===*****==="+saveImgToServerConfig.getSavePath());
		return null;
	}

	
    @GetMapping(value="/UploadImg")
    public String UploadImg(){
        return "UploadImg";
    }
    @PostMapping(value="/UploadImg")
    public Result UploadImg(@RequestParam(value="file")MultipartFile file,//返回string BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                         Model model) {
    	Result  rs=new Result();
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(saveImgToServerConfig.getSavePath()+UUID.randomUUID() +".jpg")));//保存图片到目录下D:\tomcat-8.0.46\webapps\img
                out.write(file.getBytes());
                out.flush();
                out.close();
                String filename=saveImgToServerConfig.getSavePath()+UUID.randomUUID() +".jpg";
                rs.setObj(saveImgToServerConfig.getVistaPath()+filename.substring(29).toString());
                rs.setState(1);
                rs.setMessage("上传成功");
                System.out.println(rs.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                rs.setState(0);
                rs.setMessage("上传失败");
                return rs ;//"上传失败," + e.getMessage(); 
            } catch (IOException e) {
                e.printStackTrace();
                rs.setState(0);
                rs.setMessage("上传失败");
                return rs;//"上传失败," + e.getMessage();
            }
     
            return rs;
        } else {
        	rs.setState(0);
        	rs.setMessage("上传失败，因为文件是空的.");
            return rs;// return "上传失败，因为文件是空的.";
        }
      
    }
    
    
	/**
	 * 图片上传
	 * 
	 * @author brucehu
	 * @param request
	 * @return Result 2017年12月21日
	 */
//	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
//	@ResponseBody
//	public Result uploadImg(HttpServletRequest request) {
//		Result rs = new Result();
//		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//		MultipartFile file = null;
//		String str = "";
//		BufferedOutputStream stream = null;
//		for (int i = 0; i < files.size(); ++i) {
//			file = files.get(i);
//			if (!file.isEmpty()) {
//				try {
//					byte[] bytes = file.getBytes();
//					stream = new BufferedOutputStream(new FileOutputStream(
//							new File(saveImgToServerConfig.getSavePath() + file.getOriginalFilename())));
//					stream.write(bytes);
//					stream.close();
//					str += saveImgToServerConfig.getVistaPath() + file.getOriginalFilename() + ";";
//				} catch (Exception e) {
//					stream = null;
//					rs.setState(0);
//					rs.setMessage("上传失败. ");
//					rs.setObj("You failed to upload " + i + " => " + e.getMessage());
//					return rs;
//				}
//			} else {
//				rs.setState(0);
//				rs.setMessage("上传失败. ");
//				rs.setObj("You failed to upload ");
//				return rs;
//			}
//		}
//		str = str.substring(0, str.length() - 1);
//		rs.setState(1);
//		rs.setMessage("上传成功.");
//		rs.setObj(str);
//		return rs;
//	}
}
