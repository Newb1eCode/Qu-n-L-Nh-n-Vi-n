package com.example.demotestxuong.controller;

import com.example.demotestxuong.model.entities.Staff;
import com.example.demotestxuong.model.repository.StaffRepo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/download-template")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        String fileName = "employee_template.xlsx";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Template");

            // Tạo tiêu đề cột
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Mã");
            headerRow.createCell(1).setCellValue("Họ tên");
            headerRow.createCell(2).setCellValue("EmailFPT");
            headerRow.createCell(3).setCellValue("EmailFE");

            // Ghi dữ liệu vào file
            workbook.write(response.getOutputStream());
        }
    }

    @GetMapping("/import-employees")
    public ResponseEntity<String> importEmployees(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String employeeCode = row.getCell(0).getStringCellValue();
                String firstName = row.getCell(1).getStringCellValue();
                String lastName = row.getCell(2).getStringCellValue();
                String email = row.getCell(3).getStringCellValue();

                // Xử lý logic để lưu dữ liệu vào database
                // employeeService.save(new Employee(employeeCode, firstName, lastName, email));
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
        }

        return ResponseEntity.ok("Employees imported successfully");
    }

}

