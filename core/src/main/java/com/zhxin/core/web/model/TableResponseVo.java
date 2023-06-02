package com.zhxin.core.web.model;

import com.zhxin.common.page.TableDataInfo;
import com.zhxin.common.utils.StringUtil;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @ClassName TableResponseVo
 * @Description //LayUI Table 数据返回类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/30 0030 上午 11:44
 **/
public class TableResponseVo extends HashMap<String,Object> implements Serializable {

    private static final long serialVersionUID = -2992759386721797918L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /** total count */
    public static final String COUNT_TAG = "count";
    /** page count */
    public static final String PAGE_COUNT_TAG = "page_count";

    public TableResponseVo(){}

    public TableResponseVo(int code,String msg){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
    }

    public TableResponseVo(int code,String msg,Object data,int count,int pageCount){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        super.put(COUNT_TAG, count);
        super.put(DATA_TAG, data);
        super.put(PAGE_COUNT_TAG,pageCount);
    }

    /**
     * 返回数据
     */
    public static TableResponseVo setReturnResult(Object data,int count,int pageSize)
    {
        int pageCount = 1;
        if(pageSize > 0){
            if (count % pageSize == 0) {
                pageCount = count / pageSize;
            } else {
                pageCount = (count / pageSize) + 1;
            }
        }
        return new TableResponseVo(0, "获取成功", data,count,pageCount);
    }



}
