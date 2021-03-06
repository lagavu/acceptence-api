package lagavu.acceptance.controller;

import lagavu.acceptance.domain.appeal.dto.AppealDto;
import lagavu.acceptance.domain.appeal.dto.request.AppealRequestDto;
import lagavu.acceptance.domain.appeal.entity.Appeal;
import lagavu.acceptance.domain.appeal.mapper.IAppealMapper;
import lagavu.acceptance.domain.appeal.service.AppealService;
import lagavu.acceptance.domain.customer.entity.Customer;
import lagavu.acceptance.domain.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppealController {

    private final AppealService appealService;
    private final CustomerService customerService;

    public AppealController(AppealService appealService, CustomerService customerService) {
        this.appealService = appealService;
        this.customerService = customerService;
    }

    @GetMapping("/appeals")
    public ResponseEntity<List<AppealDto>> getList() {
        List<Appeal> appeals = appealService.findAll();
        List<AppealDto> appealDtoList = IAppealMapper.INSTANCE.mapAppealsToAppealDtoList(appeals);
        return new ResponseEntity<>(appealDtoList, HttpStatus.OK);
    }

    @GetMapping("/appeals/{id}")
    public ResponseEntity<AppealDto> get(@PathVariable Long id) {
        Appeal appeal = appealService.getById(id);
        AppealDto appealDto = AppealDto.create(appeal);
        return new ResponseEntity<>(appealDto, HttpStatus.OK);
    }

    @PostMapping("/appeals/register")
    public ResponseEntity<AppealDto> register(@RequestBody AppealRequestDto appealRequestDto) {
        Customer customer = customerService.findByAppealData(appealRequestDto);
        Appeal appeal = appealService.register(appealRequestDto, customer);
        return new ResponseEntity<>(AppealDto.create(appeal), HttpStatus.OK);
    }

    @PutMapping("/appeals/{id}")
    public ResponseEntity<AppealDto> update(@PathVariable Long id, @RequestBody AppealRequestDto appealRequestDto) {
        Appeal appeal = appealService.update(id, appealRequestDto);
        return new ResponseEntity<>(AppealDto.create(appeal), HttpStatus.OK);
    }

    @DeleteMapping("/appeals/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        appealService.delete(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
