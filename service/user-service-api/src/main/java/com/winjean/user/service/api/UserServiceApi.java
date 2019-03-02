package com.winjean.user.service.api;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * 类描述：
 * 创建人：winjean
 * 创建时间：2018/9/21 14:40
 *
 * @version V 1.0
 */

public interface UserServiceApi {

//    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
//    User getUser(@RequestParam("id") int id);


    String getUserName();

}
