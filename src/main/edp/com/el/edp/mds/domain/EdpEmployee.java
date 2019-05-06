package com.el.edp.mds.domain;

import com.el.core.udc.UdcName;
import com.el.core.util.TimeUtil;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Simon.Hu
 * @since 2018/04/18
 */
@Data
public class EdpEmployee {
    /**
     *  ID - PB_EMP.ID
     */
    private Long id;

    /**
     *  公司ID - PB_EMP.OU_ID
     */
    private Long ouId;

    /**
     *  公司名称 - PB_OU.OU_NAME
     */
    private String ouName;

    /**
     *  组织ID - PB_EMP.ORG_ID
     */
    private Long orgId;

    /**
     *  组织名称 - PB_ORG.ORG_NAME
     */
    private String orgName;

    /**
     *  编号 - PB_EMP.EMP_CODE
     */
    private String empCode;

    /**
     *  姓名 - PB_EMP.EMP_NAME
     */
    private String empName;

    /**
     *  员工类型 - PB_EMP.EMP_TYPE
     */
    private String empType;

    @UdcName(udcName = "EDP.EMP_TYPE", codePropName = "empType")
    private String empTypeName;

    /**
     *  员工状态 - PB_EMP.EMP_STATUS
     */
    private String empStatus;

    @UdcName(udcName = "EDP.ON_OFF_FLAG", codePropName = "empStatus")
    private String empStatusName;

    /**
     *  英文名 - PB_EMP.ENGLISH_NAME
     */
    private String englishName;

    /**
     *  地址号 - PB_EMP.ADDR_NO
     */
    private Long addrNo;

    /**
     *  上级ID - PB_EMP.PID
     */
    private Long pid;

    /**
     *  性别 - PB_EMP.EMP_GENDER
     */
    private String empGender;

    /**
     *  生日 - PB_EMP.BIRTH_DATE
     */
    @TimeUtil.TIME
    private LocalDate birthDate;

    /**
     *  电子邮件 - PB_EMP.EMAIL
     */
    private String email;

    /**
     *  手机 - PB_EMP.MOBILE
     */
    private String mobile;

    /**
     *  证件类型 - PB_EMP.ID_TYPE
     */
    private String idType;

    /**
     *  证件号码 - PB_EMP.ID_NO
     */
    private String idNo;

    /**
     *  职位 - PB_EMP.POSITION
     */
    private String position;

    /**
     *  职级 - PB_EMP.EMP_LEVEL
     */
    private String empLevel;

    /**
     *  业余爱好 - PB_EMP.HOBBY
     */
    private String hobby;

    /**
     *  籍贯 - PB_EMP.NATIVE_PLACE
     */
    private String nativePlace;

    /**
     *  民族 - PB_EMP.RACE
     */
    private String race;

    /**
     *  政治面貌 - PB_EMP.POLITY
     */
    private String polity;

    /**
     *  学历 - PB_EMP.EDUCATION
     */
    private String education;

    /**
     *  专业 - PB_EMP.SPECIALTY
     */
    private String specialty;

    /**
     *  毕业院校 - PB_EMP.GRADUATED_FROM
     */
    private String graduatedFrom;

    /**
     *  学位 - PB_EMP.DEGREE
     */
    private String degree;

    /**
     *  工作经历 - PB_EMP.WORKEXP
     */
    private String workexp;

    /**
     *  培训记录 - PB_EMP.TRAINING
     */
    private String training;

    /**
     *  职称 - PB_EMP.TITLE
     */
    private String title;

    /**
     *  获奖情况 - PB_EMP.AWARD
     */
    private String award;

    /**
     *  绩效 - PB_EMP.PERFORMANCE
     */
    private String performance;

    /**
     *  获得认证 - PB_EMP.ATTESTATION
     */
    private String attestation;

    /**
     *  入职日期 - PB_EMP.JOININ_DATE
     */
    @TimeUtil.DATE
    private LocalDate joininDate;

    /**
     *  离职日期 - PB_EMP.LEAVE_DATE
     */
    @TimeUtil.DATE
    private LocalDate leaveDate;

    /**
     *  兵役 - PB_EMP.ESCUAGE
     */
    private String escuage;

    /**
     *  技能描述 - PB_EMP.SKILLS
     */
    private String skills;

    /**
     *  健康情况 - PB_EMP.HEALTHY_CONDITION
     */
    private String healthyCondition;

    /**
     *  犯罪记录 - PB_EMP.CRIMINAL_RECORD
     */
    private String criminalRecord;

    /**
     *  备注 - PB_EMP.REMARK
     */
    private String remark;

    /**
     *  外部编码 - PB_EMP.OUTER_CODE
     */
    private String outerCode;

    /**
     *  是否删除 - PB_EMP.DELETE_FLAG
     */
    private String deleteFlag;
}
