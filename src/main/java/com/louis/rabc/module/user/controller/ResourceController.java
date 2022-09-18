package com.louis.rabc.module.user.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.louis.rabc.annotation.AuthAndResponseUnify;
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
    @PostMapping("/public")
    @AuthAndResponseUnify
    public List<String> getPublic() {
        List<Resource> resourceList = this.resourceService.getBaseMapper().selectList(Wrappers.<Resource>lambdaQuery().select(Resource::getResourceName).eq(Resource::getResourceSpecies, "public"));
        return resourceList.stream().map(Resource::getResourceName).collect(Collectors.toList());
    }

}

