package com.example.demotestxuong.controller;

import com.example.demotestxuong.model.entities.Staff;
import com.example.demotestxuong.model.repository.StaffRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/xuong/nhanvien/")
public class StaffController {

    @Autowired
    private StaffRepo repoStaff;

    @GetMapping("index")
    public String home(Model mol){
        List<Staff>lst = repoStaff.findAll();
        mol.addAttribute("staff",lst);
        return "user/nhanvien/index";
    }

    @GetMapping("trangthai/{id}")
    public String trangThai(@PathVariable UUID id, Model mol){
        Staff staff = repoStaff.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        staff.setStatus((byte) 0); // Cập nhật trạng thái
        repoStaff.save(staff);
        return "redirect:/xuong/nhanvien/index";
    }

    @GetMapping("hoatdong/{id}")
    public String hoatdong(@PathVariable UUID id, Model mol){
        Staff staff = repoStaff.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        staff.setStatus((byte) 1); // Cập nhật trạng thái
        repoStaff.save(staff);
        return "redirect:/xuong/nhanvien/index";
    }

    @GetMapping("add")
    public String showAddForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "user/nhanvien/dangky";
    }

    @PostMapping("save")
    public String addStaff(@Valid Staff staff, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/nhanvien/dangky";
        }
        staff.setStatus((byte) 1);
        repoStaff.save(staff);
        return "redirect:/xuong/nhanvien/index";
    }
}

