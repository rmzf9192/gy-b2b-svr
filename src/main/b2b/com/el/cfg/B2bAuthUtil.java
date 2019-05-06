package com.el.cfg;

import com.el.edi.iam.payload.EdpUser;

/**
 * @author neo.pan
 * @since 2018/04/27
 */
public abstract class B2bAuthUtil {

    /**
     * @return 是否为运营方
     */
    public static boolean isOperator(EdpUser user) {
        return user.getEntType() == B2bEntType.oper;
    }

    /**
     * @return 是否为供应商
     */
    public static boolean isSupplier(EdpUser user) {
        return user.getEntType() == B2bEntType.supp;
    }

}
