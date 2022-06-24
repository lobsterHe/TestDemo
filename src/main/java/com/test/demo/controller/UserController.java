package com.test.demo.controller;

import com.test.demo.entity.User;
import com.test.demo.response.ApiResponse;
import com.test.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Tag(name = "USER SERVICE")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value="/{id}")
    @Operation(summary = "getUsersById")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "user id", required = true, dataTypeClass = Integer.class)})
    public ApiResponse getUsersById(@PathVariable Integer id) {
        User user = userService.getUsersById(id);
        return new ApiResponse(user);
    }

    @PostMapping(value="/")
    @Operation(summary = "addUser")
    @ApiImplicitParams({@ApiImplicitParam(name = "user", value = "user", required = true, dataTypeClass = User.class)})
    public ApiResponse addUser(@RequestBody User user) {
        boolean success = userService.addUser(user);
        return new ApiResponse<Boolean>(success);
    }

    @PutMapping(value="/{id}")
    @Operation(summary = "updateUser")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "user id", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "User", value = "user", required = true, dataTypeClass = User.class)})
    public ApiResponse updateUser(@PathVariable Integer id, @RequestBody User user) {
        boolean success = userService.updateUser(id, user);
        return new ApiResponse<Boolean>(success);
    }

    @DeleteMapping(value="/{id}")
    @Operation(summary = "deleteUser")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "user id", required = true, dataTypeClass = Integer.class)})
    public ApiResponse deleteUser(@PathVariable Integer id) {
        boolean success = userService.deleteUser(id);
        return new ApiResponse<Boolean>(success);
    }
}
