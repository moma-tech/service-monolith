package com.moma.service.demo.resource.controller;

import com.moma.zoffy.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ResourceController
 *
 * <p>Api Resource Controller
 *
 * @version 1.0
 * @author Created by ivan on 2:51 PM 12/24/18.
 */
@Api(tags = "Api Resource", description = "Api as Resources in project")
@RestController
@RequestMapping("/open/resource")
public class ResourceController extends BaseController {}
