package com.team.house.page.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.*;
import com.team.house.service.DistrictService;
import com.team.house.service.HouseService;
import com.team.house.service.StreetService;
import com.team.house.service.TypeService;
import com.team.house.utils.HouseConnection;
import com.team.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private DistrictService districtService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private HouseService houseService;
    @RequestMapping("addHouse")
    public String addHouse(Model model){
        List<District> districts = districtService.getDistrictList();
        List<Type> types = typeService.getTypeList();
        model.addAttribute("districts",districts);
        model.addAttribute("types",types);
        return "fabu";
    }
    @RequestMapping("getStreets")
    @ResponseBody
    public List<Street> getStreets(Integer id){
        List<Street> list = streetService.getStreetByDistrictId(id);
        return list;
    }
    @RequestMapping("addhouse")
    public String addhouse(@RequestParam(name = "pfile",required = false) CommonsMultipartFile pfile, House house, HttpSession session) throws Exception{
        String originalFilename = pfile.getOriginalFilename();
        String name=originalFilename.substring(originalFilename.lastIndexOf("."));
        String s=System.currentTimeMillis()+name;
        String a="d:/images/"+s;
        File file=new File(a);
        pfile.transferTo(file);
        house.setPath(s);
        house.setId(System.currentTimeMillis()+"");
        Users user=(Users) session.getAttribute("loginInfo");
        house.setUserId(user.getId());
        house.setIsdel(0);
        house.setIspass(0);

        int i = houseService.addHouse(house);
        if (i==1){
            return "guanli";
        }else {
            file.delete();
        }
        return "redirect:fabu";
    }

    @RequestMapping("getHouse")
    public String getHouse(HttpSession session, Page page,Model model){
        Users user = (Users)session.getAttribute("loginInfo");
        Integer id = user.getId();
        PageInfo<House> info = houseService.getHouseById(id, page);
        model.addAttribute("info",info);
        return "guanli";
    }
    @RequestMapping("getHouseById")
    public String getHouseById(String id,Model model){
        House house = houseService.getHouseById(id);
        model.addAttribute("house",house);
        return "upfabu";
    }
    @RequestMapping("getPath")
    @ResponseBody
    public String getPath(@RequestParam(name = "pfile",required = false)CommonsMultipartFile pfile,String path) throws Exception{
        String originalFilename = pfile.getOriginalFilename();
        String a=originalFilename.substring(originalFilename.lastIndexOf("."));
        String c=System.currentTimeMillis()+a;
        String b="d:/images/"+c;
        File file=new File(b);
        pfile.transferTo(file);
        String d="d:/images/"+path;
        File file1=new File(d);
        file1.delete();
        return  c;
    }
    @RequestMapping("updateHouse")
    public String updateHouse(House house){
        house.setIspass(0);
        houseService.updateHouse(house);
        return "redirect:getHouse";
    }
    @RequestMapping("updateisdel")
    public String updateisdel(String id){
        houseService.updateisdel(id,1);
        return "redirect:getHouse";
    }

    @RequestMapping("getHouses")
    public String getHouses(HouseConnection houseConnection,Model model) throws Exception{
        System.out.println(houseConnection);
        PageInfo<House> info = houseService.getHouse(houseConnection);
        model.addAttribute("info",info);
        model.addAttribute("connection",houseConnection);
        return "list";
    }
    @RequestMapping("house")
    public String house(String id,Model model){
        House house = houseService.getHouseById(id);
        model.addAttribute("house",house);
        return "details";
    }
}
