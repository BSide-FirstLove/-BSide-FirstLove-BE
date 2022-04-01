package com.bside.afterschool.auth.client;

import com.bside.afterschool.auth.dto.AuthRequest;
import com.bside.afterschool.user.domain.User;

/**
 * ClientProxy
 */
public interface ClientProxy {
    User getUserData(AuthRequest authRequest);
}
