package com.bside.afterschool.auth.client;

import com.bside.afterschool.user.domain.User;

/**
 * ClientProxy
 */
public interface ClientProxy {
    User getUserData(String accessToken);
}
