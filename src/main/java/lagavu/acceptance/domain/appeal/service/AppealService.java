package lagavu.acceptance.domain.appeal.service;

import lagavu.acceptance.component.cbr.cbr_exchange_rate_client.ICbrExchangeRateClient;
import lagavu.acceptance.domain.appeal.dto.request.AppealRequestDto;
import lagavu.acceptance.domain.appeal.entity.Appeal;
import lagavu.acceptance.domain.appeal.entity.CallAppeal;
import lagavu.acceptance.domain.appeal.entity.FeedbackAppeal;
import lagavu.acceptance.domain.appeal.entity.OfflineAppeal;
import lagavu.acceptance.domain.appeal.exception.AppealNotFoundException;
import lagavu.acceptance.domain.appeal.exception.RegistrationAppealException;
import lagavu.acceptance.domain.appeal.factory.AppealFactory;
import lagavu.acceptance.domain.appeal.repository.IAppealRepository;
import lagavu.acceptance.domain.customer.entity.Customer;
import lagavu.acceptance.component.cbr.cbr_exchange_rate_client.util.date_formatter.CbrDateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppealService {

    private final IAppealRepository appealRepository;
    private final ICbrExchangeRateClient cbrExchangeRateClient;

    public AppealService(IAppealRepository appealRepository, ICbrExchangeRateClient cbrExchangeRateClient) {
        this.appealRepository = appealRepository;
        this.cbrExchangeRateClient = cbrExchangeRateClient;
    }

    public List<Appeal> findAll() {
        return appealRepository.findAll();
    }

    public Appeal getById(Long id) {
        return appealRepository
                .findById(id)
                .orElseThrow(() -> new AppealNotFoundException("Unable to find appeal with id: " + id));
    }

    @Transactional
    public Appeal register(AppealRequestDto appealRequestDto, Customer customer) {
        if (appealRequestDto.getSum() > customer.getAvailableAmountOfDollars()) {
            throw new RegistrationAppealException("Not enough money in the account.");
        }

        Float rate = cbrExchangeRateClient.getRateByCurrency(appealRequestDto.getCurrency().getCurrency(),
                CbrDateFormatter.getCurrentDate());
        appealRequestDto.setRate(rate);

        Appeal appeal = AppealFactory.create(appealRequestDto, customer);

        if (!appeal.isVerify(customer)) {
            throw new RegistrationAppealException("Failed to verify appeal.");
        }

        appealRepository.save(appeal);
        return appeal;
    }

    @Transactional
    public Appeal update(Long id, AppealRequestDto appealRequestDto) {
        Appeal appeal = getById(id);

        if (appeal instanceof CallAppeal) {
            ((CallAppeal) appeal).update(appealRequestDto);
        }

        if (appeal instanceof FeedbackAppeal) {
            ((FeedbackAppeal) appeal).update(appealRequestDto);
        }

        if (appeal instanceof OfflineAppeal) {
            ((OfflineAppeal) appeal).update(appealRequestDto);
        }

        appealRepository.flush();
        return appeal;
    }

    public void delete(Long id) {
        appealRepository.delete(getById(id));
    }
}
