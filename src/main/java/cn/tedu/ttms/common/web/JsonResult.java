package cn.tedu.ttms.common.web;

import java.util.logging.Logger;

import cn.tedu.ttms.product.controller.ProjectController;

/**借助此对象封装Controller方法上有
 * @ResponseBody注解的方法的返回值,
 * 目的:统一返回值类型,便于在页面上进
 * 行统一处理
 * */
public class JsonResult {
	private static final int SUCCESS=1;
	private static final int ERROR=0;
	private Logger log = Logger.getLogger(ProjectController.class.getName());
	/**状态*/
	private int state;
	/**对应状态的消息*/
	private String message;
	/**具体业务数据*/
	private Object data;
	/**此构造方法应用于data为null的场景*/
	public JsonResult(){
		this.state=SUCCESS;//1
		this.message="OK";
	}
	/**有具体业务数据返回时,使用此构造方法*/
	public JsonResult(Object data){
		this();
		this.data = data;
		log.info("data:=" + this.data);
	}
	/**传入message时，修改message值*/
	public JsonResult(String message){
		this.state=SUCCESS;	
		this.message = message;
		log.info("state=" + this.state);
		log.info("message=" + this.message);
	}
	/**出现异常以后要调用此方法封装异常信息*/
	public JsonResult(Throwable t){
		this.state = ERROR;
		this.message = t.getMessage();
	}
	public Object getData() {
		return data;
	}
	public int getState() {
		return state;
	}
	public String getMessage() {
		return message;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
