package com.louis.rabc.module.user.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.louis.rabc.annotation.AuthAndResponseUnify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import com.louis.rabc.module.user.entity.Resource;
import com.louis.rabc.module.user.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * (Resource)表控制层
 *
 * @author 赖小燚
 * @since 2022-09-18 17:23:02
 */
@Api(tags = "访问内部资源")
@RestController
@RequestMapping("resource")
@AllArgsConstructor
public class ResourceController {
    /**
     * 服务对象
     */
    private final ResourceService resourceService;

    /**
     * 获取java资源
     *
     * @return {@link List}<{@link String}>
     */
    @ApiOperation("获取后端资源")
    @PostMapping("/java")
    @AuthAndResponseUnify
    public List<String> getJava() {
        List<Resource> resourceList = this.resourceService.getBaseMapper().selectList(Wrappers.<Resource>lambdaQuery().select(Resource::getResourceName).eq(Resource::getResourceSpecies, "java"));
        return resourceList.stream().map(Resource::getResourceName).collect(Collectors.toList());
    }

    /**
     * 得到前端资源
     *
     * @return {@link List}<{@link String}>
     */
    @ApiOperation("获取前端资源")
    @PostMapping("/web")
    @AuthAndResponseUnify
    public List<String> getWeb() {
        List<Resource> resourceList = this.resourceService.getBaseMapper().selectList(Wrappers.<Resource>lambdaQuery().select(Resource::getResourceName).eq(Resource::getResourceSpecies, "web"));
        return resourceList.stream().map(Resource::getResourceName).collect(Collectors.toList());
    }

    /**
     * 得到测试资源
     *
     * @return {@link List}<{@link String}>
     */
    @ApiOperation("获取测试资源")
    @PostMapping("/test")
    @AuthAndResponseUnify
    public List<String> getTest() {
        List<Resource> resourceList = this.resourceService.getBaseMapper().selectList(Wrappers.<Resource>lambdaQuery().select(Resource::getResourceName).eq(Resource::getResourceSpecies, "test"));
        return resourceList.stream().map(Resource::getResourceName).collect(Collectors.toList());
    }

    /**
     * 得到全栈资源
     *
     * @return {@link List}<{@link String}>
     */
    @ApiOperation("获取全栈资源")
    @PostMapping("/stock")
    @AuthAndResponseUnify
    public List<String> getStock() {
        List<Resource> resourceList = this.resourceService.getBaseMapper().selectList(Wrappers.<Resource>lambdaQuery().select(Resource::getResourceName).eq(Resource::getResourceSpecies, "stock"));
        return resourceList.stream().map(Resource::getResourceName).collect(Collectors.toList());
    }

    /**
     * 得到管理资源
     *
     * @return {@link List}<{@link String}>
     */
    @ApiOperation("获取项目资源")
    @PostMapping("/manager")
    @AuthAndResponseUnify
    public List<String> getManager() {
        List<Resource> resourceList = this.resourceService.getBaseMapper().selectList(Wrappers.<Resource>lambdaQuery().select(Resource::getResourceName).eq(Resource::getResourceSpecies, "manager"));
        return resourceList.stream().map(Resource::getResourceName).collect(Collectors.toList());
    }

    /**
     * 获得公众资源
     *
     * @return {@link List}<{@link String}>
     */
    @ApiOperation("获取公共资源")
    @PostMapping("/public")
    @AuthAndResponseUnify
    public List<String> getPublic() {
        List<Resource> resourceList = this.resourceService.getBaseMapper().selectList(Wrappers.<Resource>lambdaQuery().select(Resource::getResourceName).eq(Resource::getResourceSpecies, "public"));
        return resourceList.stream().map(Resource::getResourceName).collect(Collectors.toList());
    }

}

