package org.featx.templet.definition;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.templet.model.*;
import org.springframework.web.bind.annotation.*;

/**
 * Defined the standard api entries in restful style. Including
 * Creation(Post)
 * RetrieveOne(Get)
 * Update (Put)
 * Delete (Delete)
 * RetrievePage(Get)
 *
 * @author Excepts
 * @since 2019/12/31 9:56
 */
@RequestMapping("/service")
public interface ServiceCategoryTypeEnd {
    @PostMapping("/category-type")
    BaseResponse<Void> create(@RequestBody ServiceCategoryTypeCreateRequest componentCategoryTypeCreateRequest);

    @PutMapping("/category-type")
    BaseResponse<Void> update(@RequestBody ServiceCategoryTypeModifyRequest componentCategoryTypeCreateRequest);

    @DeleteMapping("/category-type")
    BaseResponse<Void> drop(@RequestParam("code") String code);

    @DeleteMapping("/category-type/{code}")
    BaseResponse<Void> delete(@PathVariable("code") String code);

    @GetMapping("/category-type")
    BaseResponse<ServiceCategoryTypeInfo> retrieve(@RequestParam("code") String code);

    @GetMapping("/category-type/{code}")
    BaseResponse<ServiceCategoryTypeInfo> get(@PathVariable("code") String code);

    @GetMapping("/category-type-page")
    PageResponse<ServiceCategoryTypeItem> retrieveByPage(@RequestBody ServiceCategoryTypePageRequest componentCategoryTypePageRequest);
}
