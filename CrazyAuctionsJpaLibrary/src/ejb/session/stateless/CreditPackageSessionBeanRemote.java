/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CreditPackage;
import entity.Customer;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Remote;
import util.exception.CreditTransactionHistoryNotFoundException;

/**
 *
 * @author chiaangyong
 */
@Remote
public interface CreditPackageSessionBeanRemote {
    public Long createNewCreditPackage(BigDecimal creditPrice, String creditPackageType, BigDecimal creditPackageQuantity);
    public Long createNewCreditPackage(CreditPackage creditPackage);
    public List<CreditPackage> retrieveCreditTransactionHistory(Customer customer) throws CreditTransactionHistoryNotFoundException;
}