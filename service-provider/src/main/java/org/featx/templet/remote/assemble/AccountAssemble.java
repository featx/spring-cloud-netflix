package org.featx.templet.remote.assemble;

import org.featx.templet.remote.model.AccountInfo;

/**
 * @author Excepts
 * @since 2020/4/4 17:10
 */
public interface AccountAssemble {
    AccountInfo retrieveOne(String code);
}
