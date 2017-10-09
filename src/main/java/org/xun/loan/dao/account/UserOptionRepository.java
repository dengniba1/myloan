package org.xun.loan.dao.account;


import org.xun.loan.entity.account.UserOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liwenlong on 2017/5/18.
 */
@Transactional
public interface UserOptionRepository extends CrudRepository<UserOption, Long> {
}
