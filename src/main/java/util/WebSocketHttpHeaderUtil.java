package util;

import org.springframework.http.HttpHeaders;
import pojo.User;

/**
 * util.WebSocketHttpHeaderUtil
 *
 * @author Created by WXG on 2018/5/15 015 13:55.
 * @version V1.0
 */

public class WebSocketHttpHeaderUtil {

    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";

    /**
     * parse User By {@link HttpHeaders}
     *
     * @param headers HttpHeaders
     * @return User
     */
    public static User parseUserByHeaders(HttpHeaders headers) {
        String id = headers.toSingleValueMap().get(KEY_ID);
        String username = headers.toSingleValueMap().get(KEY_USERNAME);

        User user = new User();
        if (!StringUtil.isEmpty(id) && !StringUtil.isEmpty(username)) {
            user.setId(id);
            user.setUsername(username);
        }

        return user;
    }

}
