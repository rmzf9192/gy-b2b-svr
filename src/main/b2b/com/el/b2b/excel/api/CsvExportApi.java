package com.el.b2b.excel.api;

import com.el.b2b.api.SoQuery;
import com.el.b2b.excel.excel.CsvFileName;
import com.el.b2b.mapper.SoMapper;
import com.el.cfg.security.EdpPrincipalService;
import com.el.core.udc.UdcNameResolver;
import com.el.core.util.CsvUtil;
import com.el.edi.iam.payload.EdpUser;
import com.el.edp.udc.domain.EdpUdc;
import com.el.edp.udc.domain.EdpUdcItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;


/**
 * 文件导出
 *
 * @author kevin.guo
 * @since 2018-05-22
 */
@Slf4j
@RestController
@RequestMapping("api/export/csv/")
@RequiredArgsConstructor
public class CsvExportApi {

    private final UdcNameResolver<EdpUdc, EdpUdcItem> udcNameResolver;

    private final EdpPrincipalService principalService;//当前登录人

    private final SoMapper soMapper;


    private static final Charset CSV_CHARSET = Charset.forName("GB18030");

    private static final int DEF_LIMIT = 2000;

    /**
     * 订单导出
     *
     * @return
     */
    @GetMapping("/soExport2Csv")
    public void soExport2Csv(SoQuery query, HttpServletResponse response) {
        EdpUser user = principalService.user();//获取当前登陆用户
        query.setOuCode(user.getEntCode());
        query.setCustCode(user.getEmpCode());
        val list = soMapper.exportDomain(query);
        this.toCSVExport(
            response,
            CsvFileName.SO_EXPORT.getFileName(),
            list.size() > 0 ? udcNameResolver.resolveUdcNames(list) : list
        );
    }
    /**
     * 已发货订单导出
     *
     */
    @GetMapping("/soDelivered2Csv")
    public void soDelivered2Csv(SoQuery query,HttpServletResponse response){
        EdpUser user = principalService.user();//获取当前登陆用户
        query.setOuCode(user.getEntCode());
        query.setCustCode(user.getEmpCode());
        val list= soMapper.deliveredDomain(query);
        this.toCSVExport(
            response,
            CsvFileName.SO_DELIVERED.getFileName(),
            list.size() > 0 ? udcNameResolver.resolveUdcNames(list) : list
        );
    }


    /**
     * CSV 导出
     *
     * @param response
     * @param fileName
     * @param list
     */
    public void toCSVExport(HttpServletResponse response, String fileName, List<?> list) {
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            ServletOutputStream outputStream = response.getOutputStream();
            try (
                 val output = new BufferedWriter(new OutputStreamWriter(outputStream, CSV_CHARSET))) {
                CsvUtil.outputCsv(list, output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
