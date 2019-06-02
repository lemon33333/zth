package com.ddc.server.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.config.web.http.ResponsePageHelper;
import com.ddc.server.config.web.http.ResponsePageModel;
import com.ddc.server.entity.DDCAdmin;
//import com.ddc.server.entity.DdcSuggestings;
import com.ddc.server.service.IDDCAdminService;
//import com.ddc.server.service.IDDCSuggestingsService;
import com.ddc.server.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import com.ddc.server.config.web.http.ResponseHelper;
import com.ddc.server.config.web.http.ResponseModel;
import com.ddc.server.entity.DDCInformation;
import com.ddc.server.mapper.DDCInformationMapper;
import com.ddc.server.service.IDDCInformationService;
import com.ddc.server.service.impl.DDCInformationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.util.resources.ga.LocaleNames_ga;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/information")
@Controller
@Slf4j
public class InformationController {
    @Resource
    private IDDCInformationService iddcInformationService;

    @RequestMapping("/add")
    public String jumpAdd(Model model) throws Exception {
        return "article-add";
    }

    @Resource
    private DDCInformationMapper informationMapper;

    @RequestMapping("/AddArticle")
    @ResponseBody
    public void insertAdd(@RequestParam(value = "articletitle", required = true) String articleTitle,
                          @RequestParam(value = "articletitle2", required = true) String simpleTitle,
                          @RequestParam(value = "articlecolumn", required = true) long classifyId,
                          @RequestParam(value = "articletype", required = true) String titleType,
                          @RequestParam(value = "articlesort", required = true) Integer sort,
                          @RequestParam(value = "keywords", required = true) String keyword,
                          @RequestParam(value = "abstract", required = true) String abs,
                          @RequestParam(value = "author", required = true) String autor,
                          @RequestParam(value = "sources", required = true) String source,
                          Model model) throws Exception {
        DDCInformation ddcInformation = new DDCInformation(articleTitle, simpleTitle, classifyId, titleType, sort, keyword);
        informationMapper.insert(ddcInformation);
        //PasswordUtils.entryptPassword(member);
    }

    @Resource
    private DDCInformationServiceImpl informationService;

    @RequestMapping("/ListArticle")
    @ResponseBody
    public ResponseModel<List<DDCInformation>> listArticle(Model model) throws Exception {
        List<DDCInformation> informationList = informationService.selectMemberList();
        return ResponseHelper.buildResponseModel(informationList);
    }



    @RequestMapping("/deleteArticle")
    @ResponseBody
    public boolean delecteArticle(@RequestParam(value = "id") Long id,Model model) throws Exception{
        boolean result=informationService.deleteById(id);
        return result;
    }

    @RequestMapping("/OneArticle")
    @ResponseBody
    public ResponseModel<List<DDCInformation>>oneArticle(@RequestParam(value = "search") String search,Model model) throws Exception {
        List<DDCInformation> ddcInformation = informationService.selectByKeyword(search);
        System.out.println(ddcInformation);
        return ResponseHelper.buildResponseModel(ddcInformation);
    }



/**
 * 前端控制器
 *
 * @author dingpengfei
 * @since 2019-05-09
 */

//  @Resource
//  IAdminService adminService;
//
//  /**
//   * 获取当前登录用户信息
//   *
//   * @param admin
//   * @return
//   * @throws Exception
//   */
//  @GetMapping("/currentUser")
//  public ResponseModel<Admin> getUser(@CurrentUser Admin admin) throws Exception {
//    return ResponseHelper.buildResponseModel(admin);
//  }
//
//  @PostMapping("/resetMobile")
//  public ResponseModel<String> resetMobile(
//      @CurrentUser Admin currentAdmin,
//      @ValidationParam("newMobile,captcha") @RequestBody JSONObject requestJson)
//      throws Exception {
//    adminService.resetMobile(currentAdmin, requestJson);
//    return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
//  }
//
//  /**
//   * 修改密码
//   *
//   * @return
//   * @throws Exception
//   */
//  @PostMapping("/resetPassWord")
//  public ResponseModel<String> resetPassWord(
//      @ValidationParam("userNo,password,rePassword") @RequestBody JSONObject requestJson)
//      throws Exception {
//    adminService.resetPassWord(
//        adminService.selectById(requestJson.getString("userNo")), requestJson);
//    return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
//  }
//
//  //  @GetMapping(value = "/pageList")
//  @RequiresPermissions(value = {"user:list"})
//  // 拥有超级管理员或管理员角色的用户可以访问这个接口,换成角色控制权限,改变请看MyRealm.class
//  // @RequiresRoles(value = {Constant.RoleType.SYS_ASMIN_ROLE,Constant.RoleType.ADMIN},logical =
//  // Logical.OR)
//  @AccessLimit(perSecond = 1, timeOut = 500) // 5秒钟生成一个令牌
//  public ResponseModel<Page<Admin>> findList(
//      @RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
//      @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
//      @RequestParam(value = "username", defaultValue = "", required = false) String username) {
//    return ResponseHelper.buildResponseModel(
//        adminService.selectPage(
//            new Page<>(pageIndex, pageSize),
//            ComUtil.isEmpty(username)
//                ? new EntityWrapper<>()
//                : new EntityWrapper<Admin>().like("user_name", username)));
//  }
//
//  @Pass
//  @GetMapping("/pageList")
//  @ApiOperation(value = "获取用户列表", notes = "需要header里加入Authorization")
//  @ApiImplicitParams({
//    @ApiImplicitParam(name = "pageIndex", value = "第几页", dataType = "String", paramType = "query"),
//    @ApiImplicitParam(name = "pageSize", value = "每页多少条", dataType = "String", paramType = "query"),
//    @ApiImplicitParam(name = "info", value = "会员名称或者电话", dataType = "String", paramType = "query"),
//    @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "Long", paramType = "query"),
//    @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "Long", paramType = "query")
//  })
//  public ResponsePageModel<Admin> findInfoList(
//      @RequestParam(name = "page", defaultValue = "1", required = false) Integer pageIndex,
//      @RequestParam(name = "limit", defaultValue = "10", required = false) Integer pageSize,
//      // info-->用户名或者电话号码
//      @RequestParam(name = "info", defaultValue = "", required = false) String info,
//      @RequestParam(name = "startTime", defaultValue = "", required = false) String startTime,
//      @RequestParam(name = "endTime", defaultValue = "", required = false) String endTime) {
//    // 启用或禁用的用户
//    Integer[] status = {1, 2, 3};
//    // 自定义分页关联查询列表
//    Page<Admin> userPage =
//        adminService.selectPageByConditionUser(
//            new Page<>(pageIndex, pageSize), info, status, startTime, endTime);
//    return ResponsePageHelper.buildResponseModel(userPage);
//  }
//
//  @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
//  @ApiImplicitParam(
//      name = "userNo",
//      value = "用户ID",
//      required = true,
//      dataType = "String",
//      paramType = "path")
//  @GetMapping(value = "/{userNo}")
//  // 暂时换成了角色控制权限,改变请看MyRealm.class
//  @RequiresPermissions(value = {"user:list"})
//  // 拥有超级管理员或管理员角色的用户可以访问这个接口,换成角色控制权限,改变请看MyRealm.class
//  // @RequiresRoles(value = {Constant.RoleType.SYS_ASMIN_ROLE,Constant.RoleType.ADMIN},logical =
//  // Logical.OR)
//  public ResponseModel<Admin> findOneUser(@PathVariable("userNo") String userNo) {
//    Admin admin = adminService.selectById(userNo);
//    return ResponseHelper.buildResponseModel(admin);
//  }
//
//  @ApiOperation(value = "删除用户", notes = "根据url的id来删除用户")
//  @ApiImplicitParam(
//      name = "userNo",
//      value = "用户ID",
//      required = true,
//      dataType = "String",
//      paramType = "path")
//  @DeleteMapping(value = "/{userNo}")
//  @RequiresPermissions(value = {"user:delete"})
//  public ResponseModel deleteUser(@PathVariable("userNo") String userNo) throws Exception {
//
//    adminService.deleteByUserNo(userNo);
//    return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
//  }
//
//  @GetMapping(value = "/checkToken")
//  public ResponseModel<String> checkToken() {
//    return ResponseHelper.buildResponseModel("ok");
//  }
}