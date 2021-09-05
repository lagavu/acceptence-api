package lagavu.acceptance.domain.appeal.service;

import lagavu.acceptance.domain.appeal.entity.Appeal;
import lagavu.acceptance.domain.appeal.exception.RegistrationAppealException;
import lagavu.acceptance.domain.appeal.repository.AppealRepository;
import lagavu.acceptance.domain.customer.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class RegistrationAppealService {

    private final AppealRepository appealRepository;

    public RegistrationAppealService(AppealRepository appealRepository) {
        this.appealRepository = appealRepository;
    }

    public void register(Appeal appeal, Customer customer) {

        if (!appeal.isVerify(customer)) {
            throw new RegistrationAppealException("Failed to verify appeal.");
        }

        if (appeal.getSum() > customer.getAvailableAmountOfDollars()) {
            throw new RegistrationAppealException("Not enough money in the account.");
        }

        appealRepository.save(appeal);
    }
}
