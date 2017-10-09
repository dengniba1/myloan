package org.xun.loan.dao.loan;

import org.springframework.data.repository.CrudRepository;
import org.xun.loan.entity.loan.Loan;

/**
 * Created by liwenlong on 2017/7/16.
 */
public interface LoanRepository  extends CrudRepository<Loan, String> {
}
