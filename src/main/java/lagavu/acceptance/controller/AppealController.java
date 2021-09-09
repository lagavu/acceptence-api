package lagavu.acceptance.controller;

import lagavu.acceptance.domain.appeal.dto.AppealDto;
import lagavu.acceptance.domain.appeal.dto.request.AppealRequestDto;
import lagavu.acceptance.domain.appeal.entity.Appeal;
import lagavu.acceptance.domain.appeal.mapper.AppealMapper;
import lagavu.acceptance.domain.appeal.service.AppealService;
import lagavu.acceptance.domain.customer.entity.Customer;
import lagavu.acceptance.domain.customer.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppealController {

    private final AppealService appealService;
    private final CustomerRepository customerRepository;

    public AppealController(AppealService appealService, CustomerRepository customerRepository) {
        this.appealService = appealService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/appeals")
    public ResponseEntity<List<AppealDto>> getList() {
        List<Appeal> appeals = appealService.findAll();
        List<AppealDto> appealDtoList = AppealMapper.INSTANCE.mapAppealsToAppealDtoList(appeals);

        return new ResponseEntity<>(appealDtoList, HttpStatus.OK);
    }

    @GetMapping("/appeal/{id}")
    public ResponseEntity<AppealDto> get(@PathVariable Long id) {
        Appeal appeal = appealService.getById(id);
        AppealDto appealDto = AppealDto.create(appeal);

        return new ResponseEntity<>(appealDto, HttpStatus.OK);
    }

    @PostMapping("/appeal/register")
    public ResponseEntity<AppealDto> register(@RequestBody AppealRequestDto appealRequestDto) {
        Customer customer = customerRepository.findByName("Ilon");
        Appeal appeal = appealService.register(appealRequestDto, customer);

        return new ResponseEntity<>(AppealDto.create(appeal), HttpStatus.OK);
    }

    @PutMapping("/appeal/update/{id}")
    public ResponseEntity<AppealDto> update(@PathVariable Long id, @RequestBody AppealRequestDto appealRequestDto) {
        Appeal appeal = appealService.update(id, appealRequestDto);

        return new ResponseEntity<>(AppealDto.create(appeal), HttpStatus.OK);
    }

    @DeleteMapping("/appeal/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        appealService.delete(id);

        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
