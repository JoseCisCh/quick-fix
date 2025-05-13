package com.joecis.quick_fix;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.joecis.quick_fix.account.AccountController;
import com.joecis.quick_fix.notification.NotificationController;
import com.joecis.quick_fix.user.UserController;

@Disabled
@SpringBootTest
class QuickFixApplicationTests {

    @Autowired
    private AccountController accountController;
    @Autowired
    private NotificationController notificationController;
    @Autowired
    private UserController userController;

	@Test
	void contextLoads() {
        assertNotNull(accountController != null, "AccountController is null");
        assertNotNull(notificationController != null, "NotificationController is null");
        assertNotNull(userController != null, "UserController is null");
	}

}
