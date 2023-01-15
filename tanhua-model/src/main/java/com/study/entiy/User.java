package com.study.entiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: xiaocai
 * @since: 2023/01/15/16:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BasePojo {

    private Long id;
    private String mobile;// 手机号。
    private String password;
}
