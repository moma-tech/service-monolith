package com.moma.service.demo.authinfo.controller;

import com.moma.zoffy.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OpenAuthInfoController
 *
 * <p>Open Auth Info Controller
 *
 * @version 1.0
 * @author Created by ivan on 2:21 PM 12/24/18.
 */
@Api(tags = "Open Auth Info", description = "Open Auth Info Apis")
@RestController
@RequestMapping("/open/AuthInfo")
public class OpenAuthInfoController extends BaseController {}
