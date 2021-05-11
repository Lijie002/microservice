package com.qixinmini.common.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  @Description 分页对象
 *  @author lijie
 *  @Date 2020/11/26 15:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableData {
    private List<?> rows;

    private long total;
}
