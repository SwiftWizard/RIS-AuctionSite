package com.ris.ris.project.controller;

import com.ris.ris.project.model.Auction;
import com.ris.ris.project.model.AuctionState;
import com.ris.ris.project.model.User;
import com.ris.ris.project.model.modifiedModelForReport.ModifiedAuction;
import com.ris.ris.project.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("user/auctions/reports")
public class ReportsController {
    @Autowired
    AuctionRepository ar;

    @Autowired
    HttpSession session;

    @RequestMapping("/activeAuctionsReport")
    public String activeAuctionsReport(HttpServletResponse response)throws Exception{
        User user = (User) session.getAttribute("user");
        List<Auction> auctions = ar.findAllBySellerAndAuctionState(user, AuctionState.ACTIVE);
        List<ModifiedAuction> modAuctions = auctions.stream()
                .map(auction -> new ModifiedAuction(auction.getTitle(),
                        auction.getCategory()+"",
                        auction.getDescription(),
                        (auction.getBidders().isEmpty())?Double.NaN:auction.getBidders().first().getAmount()))
                .collect(Collectors.toList());

        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(modAuctions);
        String path = "src/main/resources/reports/Report.jrxml";
        InputStream inputStream = new FileInputStream(new File(path));
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> params = new HashMap<>();
        params.put("param","param");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,params,dataSource);
        inputStream.close();

        response.setContentType("application/x-download");
        response.addHeader("Content-disposition", "attachment; filename=ActiveAuctionsReport.pdf");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        return "/users/userProfile";
    }
}
