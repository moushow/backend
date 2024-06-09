package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Friend;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.FriendMapper;
import com.example.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private UserMapper userMapper;

    //保存好友申请
    @PostMapping
    public Result application(@RequestBody Friend friend){
        String username = friend.getUsername();
        String friendName = friend.getFriendName();
        if(StrUtil.isBlank(friendName)){
            return Result.error(Constants.CODE_400, "未输入对方用户名");
        }
        if(friendMapper.findFriend(friendName, username) != null){
            return Result.error(Constants.CODE_401, "请勿重复发送申请");
        }
        if(friendMapper.application(friend) == 1){
            return Result.success();
        }else{
            return Result.error(Constants.CODE_401, "未发送好友申请");
        }
    }

    //查询有无好友申请
    @GetMapping("/{username}")
    public Friend findApplication(@PathVariable String username){
        return friendMapper.findApplication(username);
    }

    //同意好友申请
    @PostMapping("/agree/{username}/{friendName}")
    public Result agree(@PathVariable String username, @PathVariable String friendName){
        User user = userMapper.findByUserName(friendName);
        String nickname = user.getNickname();
        String telephone = user.getTelephone();
        String email = user.getEmail();
        int b = friendMapper.agree(username, friendName, nickname, telephone, email) & friendMapper.insert(username, friendName, nickname, telephone, email);
        if(b == 1){
            return Result.success();
        }else{
            return Result.error(Constants.CODE_401, "系统错误");
        }
    }

    //拒绝好友申请
    @PostMapping("/refuse/{username}/{friendName}")
    public Result refuse(@PathVariable String username, @PathVariable String friendName){
        int result = friendMapper.refuse(username, friendName);
        if (result == 1) {
            return Result.success();
        } else {
            return Result.error(Constants.CODE_401, "系统错误");
        }
    }

    //按照friendName和username查询好友关系
    @GetMapping("/{friendName}/{username}")
    public Friend findFriend(@PathVariable String friendName, @PathVariable String username){
        return friendMapper.findFriend(friendName, username);
    }

    //按照friendName删除数据
    @DeleteMapping("/delete/{friendName}/{username}")
    public Integer delete(@PathVariable String friendName, @PathVariable String username){
        return friendMapper.deleteByFriendName(friendName, username);
    }

    //分页查询
    @GetMapping("/page")       //接口路径为/friend/page
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username,
                                        @RequestParam String friendName){
        pageNum = (pageNum - 1) * pageSize;
        friendName = "%" + friendName + "%";
        List<Friend> data = friendMapper.selectPage(friendName, pageNum, pageSize, username);
        Integer total = friendMapper.selectTotal(friendName, username);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}
