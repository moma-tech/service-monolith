package com.demo.api.demo.access.authinfo.controller;

import com.demo.framework.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OpenAuthInfoApi
 *
 * <p>Open Auth Info Controller
 *
 * @version 1.0
 * @author Created by ivan on 2:21 PM 12/24/18.
 */
@Api(tags = "Open Auth Info", description = "Open Auth Info Apis")
@RestController
@RequestMapping("/open/AuthInfo")
public class OpenAuthInfoApi extends BaseController {}
