package org.xun.loan.dao.account;

import org.xun.loan.entity.account.Account;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by liwenlong on 2017/5/18.
 */
@Transactional
public interface AccountRepository extends CrudRepository<Account, String> {
}
