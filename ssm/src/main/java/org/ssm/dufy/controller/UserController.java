package org.ssm.dufy.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.ssm.dufy.dto.FileDto;
import org.ssm.dufy.entity.TUser;
import org.ssm.dufy.exception.FileUploadException;
import org.ssm.dufy.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	
	/**
	 * 根据uid查询用户
	 * 
	 * @param uid
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showname")
	public String showUserName(@RequestParam(value="uid", required = false) String uid,HttpServletRequest request,Model model){
		if(StringUtils.isEmpty(uid)) {
			return "error";
		}
		TUser user = userService.getUserById(uid);
		if(user != null){
			request.setAttribute("name", user.getUserName());
			model.addAttribute("mame", user.getUserName());
			return "showName";
		}
		request.setAttribute("error", "没有找到该用户！");
		return "error";
	}
	
	@RequestMapping(value="/gotoAddUser")
	public String gotoAddUser(HttpServletRequest request,Model model){
		
		return "gotoAddUser";
	}
	
	/**
	 * 跳转到登录页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/goLogin")
	public String goLogin(HttpServletRequest request,Model model){
		
		return "login";
	}
	
	/**
	 * 用户登录
	 * 
	 * @param uid
	 * @param request
	 * @param model
	 * @return
	 * @throws FileUploadException 
	 */
	@RequestMapping(value="/login")
	public String login(String uid,HttpServletRequest request,Model model) throws FileUploadException{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		
		Subject subject = SecurityUtils.getSubject();
		model.addAttribute("userName", userName);
		try {
			// 4、登录，即身份验证
			subject.login(token);
		
		} catch (UnknownAccountException e) {
			throw new FileUploadException("用户名不存在！");
		} catch (IncorrectCredentialsException e) {
			throw new FileUploadException("密码错误！");
		}
		return "index";
	}
	
	/**
	 * 添加用户
	 * 
	 * @param uid
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addUser")
	public String addUser(String uid,HttpServletRequest request,Model model){
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		TUser user = new TUser();
		user.setAge(Integer.valueOf(age));
		user.setUserName(userName);
		user.setPassword(password);
		user.setCreateTime(new Date());
		userService.addUser(user);
		/*List<TUser> userList = userService.selectAllUser();
		model.addAttribute("userList", userList);*/
		return "redirect:userList";
	}
	/**
	 * 用户列表
	 * 
	 */
	//@ResponseBody
	@RequestMapping(value="/userList")
	public String userList(HttpServletRequest request,Model model){
		List<TUser> userList = userService.selectAllUser();
		model.addAttribute("userList", userList);
		return "userList";
	}
	
	@RequestMapping(value="/gotoFileUpload")
	public String gotoFileUpload(HttpServletRequest request,Model model){
		
		return "fileUpload";
	}
	
	/**
	 * 文件上传(支持多文件)
	 * 
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws FileUploadException 
	 * @throws IOException
	 */
	@RequestMapping("/fileUpload")
    public String  fileUpload(HttpServletRequest request) throws IllegalStateException, FileUploadException
    {
		try {
			//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
					request.getSession().getServletContext());
			//检查form中是否有enctype="multipart/form-data"
			if(multipartResolver.isMultipart(request))
			{
				//将request变成多部分request
				MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
				//获取multiRequest 中所有的文件名
				Iterator<String> iter=multiRequest.getFileNames();
				
				while(iter.hasNext())
				{
					//一次遍历所有文件
					MultipartFile file=multiRequest.getFile(iter.next().toString());
					if(file!=null)
					{
						String path="E:/fileUpload/"+file.getOriginalFilename();
						//上传
						file.transferTo(new File(path));
					}
					
				}
				
			}
		} catch(Exception e) {
			throw new FileUploadException("文件上传出错啦啦啦啦啦");
		}
    return "redirect:fileList"; 
    }
	/**
	 * 文件列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/fileList")
	public String fileList(HttpServletRequest request,Model model){
		String dirPath = "E:/fileUpload/";
		List<FileDto> fileList = new ArrayList<FileDto> ();
		File file = new File(dirPath);
		if(!file.exists()) {
			file.mkdir();
		}
		File[] files = file.listFiles();
		for (File f : files) {
			fileList.add(new FileDto(f.getPath(), f.getName()));
        }
		model.addAttribute("fileList", fileList);
		return "/fileList";
	}
	
	/**
	 * 
	 * 文件下载
	 * @param request
	 * @param filename
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/downloadFile")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request,
            @RequestParam("filename") String filename,
            Model model)throws Exception {
       //下载文件路径
       //String path = request.getServletContext().getRealPath("/images/");
    	
       //File file = new File(path + File.separator + filename);
    	File file = new File (filename);
       HttpHeaders headers = new HttpHeaders();  
       //下载显示的文件名，解决中文名称乱码问题  
       String downloadFielName = new String(file.getName().getBytes("UTF-8"),"iso-8859-1");
       //通知浏览器以attachment（下载方式）打开图片
       headers.setContentDispositionFormData("attachment", downloadFielName); 
       //application/octet-stream ： 二进制流数据（最常见的文件下载）。
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
               headers, HttpStatus.CREATED);  
    }

}
