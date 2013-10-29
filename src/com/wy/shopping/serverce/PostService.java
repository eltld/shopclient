package com.wy.shopping.serverce;

import org.json.JSONObject;

import com.wy.shopping.http.ServiceResponse;

public interface PostService {
    
    /**
     * 	
     *  @desc:发帖子
     *  @author WY  
     *  创建时间 2013-8-9 下午3:19:17
     *  @param postDetail
     *  @return
     */
    ServiceResponse post(JSONObject postDetail);
    
    /**
     *  
     *  @desc:根据用户id，获取用户发的帖子
     *  @author WY  
     *  创建时间 2013-8-14 下午2:14:30
     *  @param userId
     *  @return
     */
    ServiceResponse getPostItem(Integer userId,String lastTime);
    
    /**
     *  
     *  @desc:根据帖子的id，查询出该帖子所有的跟帖
     *  @author WY  
     *  创建时间 2013-8-15 下午1:38:13
     *  @param postId
     *  @return
     */
    ServiceResponse getPostTips(Integer postId);
    
    /**
     * 	
     *  @desc:保存对于帖子的跟帖
     *  @author WY  
     *  创建时间 2013-8-15 下午1:52:53
     *  @param tips
     *  @return
     */
    ServiceResponse savePostTips(JSONObject tips);
    
    
    /**
     *  
     *  @desc:顶操作
     *  @author WY  
     *  创建时间 2013-8-15 下午3:49:52
     *  @param tip
     *  @return
     */
    ServiceResponse upPostTips(Integer postId);
    
    /**
     *  
     *  @desc:踩操作
     *  @author WY  
     *  创建时间 2013-8-15 下午3:49:52
     *  @param tip
     *  @return
     */
    ServiceResponse downPostTips(Integer postId);
    
    /**
     *  
     *  @desc:分页获取网上用户所发的帖子
     *  @author WY  
     *  创建时间 2013-8-16 上午9:35:05
     *  @param pageNum
     *  @return
     */
    ServiceResponse getOnlinePost(Long pageNum);
    
    /**
     * 	
     *  @desc:获取最新的帖子
     *  @author WY  
     *  创建时间 2013-8-16 上午9:48:08
     *  @param creteTime
     *  @return
     */
    ServiceResponse getNewPost(String lastTime);
    
    /**
     *  
     *  @desc:根据用户id，删除用户所有发过的帖子
     *  @author WY  
     *  创建时间 2013-8-21 下午1:38:49
     *  @param uid
     *  @return
     */
    ServiceResponse deletePostByUserId(Integer uid);
    
    /**
     *  
     *  @desc:根据用户id,删除用户所有的跟帖
     *  @author WY  
     *  创建时间 2013-8-21 下午1:40:05
     *  @param uid
     *  @return
     */
    ServiceResponse deletePostTipsByUserId(Integer uid);
    
    /**
     *  
     *  @desc:根据用户id，查询用户所有的跟帖
     *  @author WY  
     *  创建时间 2013-8-22 下午1:33:42
     *  @param uid
     *  @return
     */
    ServiceResponse getTipsByUserId(Integer uid);
    
    /**
     *  
     *  @desc:根据跟帖id，查询出相应的帖子和用户信息
     *  @author WY  
     *  创建时间 2013-8-22 下午2:54:30
     *  @param uid
     *  @return
     */
    ServiceResponse getSinglePostByTipsId(Integer tipsId,Integer postId);
    
    ServiceResponse testConnectServer(Integer tipsId);
}
