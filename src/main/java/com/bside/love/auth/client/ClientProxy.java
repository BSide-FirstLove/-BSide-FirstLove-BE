package com.bside.love.auth.client;

import com.bside.love.user.domain.User;

/**
 * ClientProxy
 */
public interface ClientProxy {
    User getUserData(String accessToken);
}
