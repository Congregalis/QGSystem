package com.xjtu.qgsystem.controller;
import com.xjtu.qgsystem.entity.Context;
import com.xjtu.qgsystem.repository.ContextRepository;
import com.xjtu.qgsystem.service.ContextService;
import com.xjtu.qgsystem.service.MultistepService;
import com.xjtu.qgsystem.util.result.Result;
import com.xjtu.qgsystem.util.result.ResultUtil;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/multistep")
public class MultistepController {
    private static final String basePath ="tempFile";
    @Autowired
    MultistepService multistepService;
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result uploadFileToLocal(@RequestParam("multipartFile") MultipartFile multipartFile) throws Exception {
        boolean res=true;
        if (multipartFile == null) {
            res=false;
        }
        File file = null;
        try {
            File dir = new File(basePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            file = new File(basePath + File.separator + multipartFile.getOriginalFilename());
            if (!file.exists()) {
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res=multistepService.getContextFromPPT(multipartFile.getOriginalFilename(),res);
        return res ? ResultUtil.success("上传成功") : ResultUtil.fail("上传失败");
    }
    @RequestMapping(value = "/getContext")
    public Result uploadFileToLocal(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageLimit") Integer pageLimit){
        return ResultUtil.success(multistepService.queryContext((pageNum-1)*pageLimit+1,pageLimit));
    }
    @RequestMapping(value = "/deleteContext")
    public Result deleteContext(@RequestParam("cId") String cId){
        boolean res = multistepService.deleteContext(Long.parseLong(cId));
        return res ? ResultUtil.success("删除成功") : ResultUtil.fail("删除失败");
    }
    @PostMapping(value = "/updateContext")
    public Result updateContext(@RequestBody Map<String,String> map){
        boolean res = multistepService.updateContext(Long.parseLong(map.get("cId")), map.get("cText"));
        return res ? ResultUtil.success("更新成功") : ResultUtil.fail("更新失败");
    }
    @RequestMapping(value = "/selectAlgo")
    public Result generateQuestion(@RequestParam("algorithm") Integer algorithm){
        boolean res = multistepService.generateQuestion(algorithm);
        return res ? ResultUtil.success("生成成功") : ResultUtil.fail("生成失败");
    }
    @RequestMapping(value = "/getQuestions")
    public Result getQuestions(@RequestParam("pageNum") Integer pageNum,@RequestParam("pageLimit") Integer pageLimit){
        return ResultUtil.success(multistepService.queryQuestions((pageNum-1)*pageLimit+1,pageLimit));
    }
    @RequestMapping(value = "/deleteQuestion")
    public Result deleteQuestion(@RequestParam("qId") String qId){
        boolean res = multistepService.deleteQuestion(Long.parseLong(qId));
        return res ? ResultUtil.success("删除成功") : ResultUtil.fail("删除失败");
    }
    @PostMapping(value = "/updateQuestion")
    public Result updateQuestion(@RequestBody Map<String,Object> map){
        boolean res = multistepService.updateQuestion(Long.parseLong((String) map.get("qId")),(String) map.get("qText"),(String) map.get("qAnswer"),(boolean) map.get("qIsChecked"),(Integer) map.get("qFluency"),(Integer) map.get("qRelevance"),(Integer) map.get("qReasonability"),(Integer) map.get("qDifficulty"));

        return res ? ResultUtil.success("更新成功") : ResultUtil.fail("更新失败");
    }
}
