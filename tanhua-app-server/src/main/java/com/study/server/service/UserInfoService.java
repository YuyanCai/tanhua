package com.study.server.service;

import com.study.entiy.UserInfo;
import com.study.vo.UserInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: xiaocai
 * @since: 2023/01/15/23:06
 */
public interface UserInfoService {

    public void save(UserInfo userInfo);

    public void updateHead(MultipartFile headPhoto, Long id) throws IOException;

    public UserInfoVo findById(Long id);

    public void update(UserInfo userInfo);

}
