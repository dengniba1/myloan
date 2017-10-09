package org.xun.loan.dao.account;

import org.xun.loan.entity.account.OfficerInformation;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by liwenlong on 2017/5/18.
 */
@Transactional
public interface OfficerInformationRepository extends CrudRepository<OfficerInformation, String> {
}
