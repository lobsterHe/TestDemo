package com.test.demo.controller;

import com.test.demo.entity.RentalOrder;
import com.test.demo.response.ApiResponse;
import com.test.demo.rpc.CarDto;
import com.test.demo.rpc.CarVo;
import com.test.demo.service.IRentalCarService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
@Tag(name = "RENTAL SERVICE")
public class RentalController {

    @Autowired
    private IRentalCarService rentalCarService;

    @PostMapping(value="/")
    @Operation(summary = "2.租车接口(rental car)")
    public ApiResponse rentalCar(@RequestBody CarVo carVo) {
        RentalOrder order = rentalCarService.rentalCar(carVo);
        return new ApiResponse<RentalOrder>(order);
    }

    @DeleteMapping(value="/{id}")
    @Operation(summary = "3.还车接口(returnCar)")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "rental order id", required = true, dataTypeClass = Integer.class)})
    public ApiResponse returnCar(@PathVariable Integer id) {
        boolean success = rentalCarService.returnCar(id);
        return new ApiResponse<Boolean>(success);
    }

    @GetMapping(value="/")
    @Operation(summary = "1.获取库存接口(getCarStock)")
    public ApiResponse getCarStock() {
        List<CarDto> dtoList = rentalCarService.getCarStock();
        return new ApiResponse<List<CarDto>>(dtoList);
    }
}
