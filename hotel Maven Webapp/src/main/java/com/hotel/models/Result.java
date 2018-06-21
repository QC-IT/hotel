package com.hotel.models;

public class Result{
    //成功状态码
    private static final Integer SUCCESS = 200;
    //失败状态码
    private static final Integer FAIL = 500;
    @SuppressWarnings("unused")
	private static final Integer NO_ACCESS_TO_RESOURCES=400;
    @SuppressWarnings("unused")
    private static final Integer ACCESS_DENIED=401;
    @SuppressWarnings("unused")
    private static final Integer NO_AUTHORITY=402;
    @SuppressWarnings("unused")
    private static final Integer SERVER_EXCEPTION=502;


    // 响应业务状态
    private Integer code;

    // 响应消息
    private String msg;

    private Object date;

    public Result(){

    }


    public Result(Integer code,String msg,Object date){
        this.code = code;
        this.msg = msg;
        this.date = date;
    }

    /**
     * 返回所有结果
     * @param code 状态码
     * @param msg   相应信息
     * @param date  数据
     * @return
     */
    public static Result build(Integer code, String msg,Object date) {
        return new Result(code, msg,date);
    }

    /**
     * 成功，携带实体信息
     * @param msg   信息
     * @param date  实体数据
     * @return
     */
    public static Result success(String msg,Object date){
        return new Result(Result.SUCCESS,msg,date);
    }

    /**
     * 失败，携带失败信息
     * @param msg 信息
     * @return
     */
    public static Result fail(String msg){
        return new Result(Result.FAIL,msg,null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
