package com.kaobei.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_investRecord")
public class InvestRecordEntity {
    private Long recordId;
    private String openId;
    private Double amount;
    private Date investTime;
}
